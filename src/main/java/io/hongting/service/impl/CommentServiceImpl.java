package io.hongting.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usthe.sureness.util.JsonWebTokenUtil;
import io.hongting.entity.Comment;
import io.hongting.entity.User;
import io.hongting.entity.vo.CommentVO;
import io.hongting.entity.vo.PostVO;
import io.hongting.mapper.CommentMapper;
import io.hongting.mapper.PostMapper;
import io.hongting.mapper.UserMapper;
import io.hongting.service.CommentService;
import io.hongting.service.MailService;
import io.hongting.service.RoleService;
import io.hongting.service.UserService;
import io.hongting.utils.IpAddressUtils;
import io.hongting.utils.UserThreadLocal;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongting
 * @since 2021-11-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Value("${blog.comment.avatar}")
    private String avatar;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    private List <Comment> tempReplies = new ArrayList<>();


    @Autowired
    private RoleService roleService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;



    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        List<Comment> comments = getTopComments(postId);
        for(Comment comment : comments){
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = getCommentsWithParent(postId,id);
            combineChildren(postId, childComments, parentNickname1);
            comment.setChildComment(tempReplies);
            tempReplies = new ArrayList<>();
        }
        return comments;
    }

    private List<Comment> getCommentsWithParent(Long postId, Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<Comment>().eq(Comment::getForeignId, postId).eq(Comment::getParentId, id).orderByDesc(Comment::getCreateTime);
        return this.getBaseMapper().selectList(queryWrapper);
    }

    private List<Comment> getTopComments (Long postId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<Comment>().eq(Comment::getForeignId, postId).orderByDesc(Comment::getId);
        List<Comment> comments = this.getBaseMapper().selectList(queryWrapper);
        return  comments.stream().filter( comment -> comment.getParentId() == null).collect(Collectors.toList());
    }

    private void combineChildren(Long postId, List<Comment> childComments, String parentNickname1) {

        if(childComments.size() > 0){
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplies.add(childComment);
                Long childCommentId = childComment.getId();

                recursively(postId, childCommentId, parentNickname);
            }
        }
    }

    private void recursively(Long postId, Long childCommentId, String parentNickname1) {
        List<Comment> childComments = getCommentsWithParent(postId,childCommentId);
        if(childComments.size() > 0){
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                Long childId = childComment.getId();
                tempReplies.add(childComment);
                recursively(postId,childId,parentNickname);
            }
        }
    }



    @Transactional
    @Override
    public void saveComment(Comment comment) throws MessagingException {
        String nickname = comment.getNickname();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getNickname, nickname);
        User user = userMapper.selectOne(queryWrapper);
        if (user!=null){
            String token = (String)redisTemplate.opsForValue().get(Objects.requireNonNull(IpAddressUtils.getIpAddr()));
            if (!StringUtils.isEmpty(token)) {
                Claims claims = JsonWebTokenUtil.parseJwt(token);
                String subject = claims.getSubject();
                comment.setIsAdmin(subject.equals(user.getUsername()));
            }else{
                comment.setIsAdmin(false);
            }
        }
        if(comment.getIsAdmin()!=null && comment.getIsAdmin()){
            comment.setAvatar(avatar);
        }else {
            Random random = new Random();
            int randomNum = random.nextInt(10) + 1;
            String avatarImg = "https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar" + randomNum + ".jpg";
            comment.setAvatar(avatarImg);
        }
        this.getBaseMapper().insert(comment);
        mailService.sendTemplateMail(comment);
    }

    @Override
    public Page<CommentVO> getCommentVOList(Long page, Long limit) {
        Page<CommentVO>  pages = new Page<>(page,limit);
        pages.setRecords(commentMapper.pageCommentVO(pages));
        return pages;
    }

    @Override
    public Page<CommentVO> getCommentVOListByPost(Long page, Long limit, Long postId) {
        Page<CommentVO>  pages = new Page<>(page,limit);
        pages.setRecords(commentMapper.pageCommentVOByPost(pages, postId));
        return pages;
    }


}
