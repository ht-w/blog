package io.hongting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.hongting.entity.Comment;
import io.hongting.entity.vo.CommentVO;

import javax.mail.MessagingException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongting
 * @since 2021-11-19
 */
public interface CommentService extends IService<Comment> {

    List<Comment> getCommentsByPost(Long id);

    void saveComment (Comment comment) throws MessagingException;

    Page<CommentVO> getCommentVOList(Long page, Long limit);

    Page<CommentVO> getCommentVOListByPost(Long page, Long limit, Long postId);
}


