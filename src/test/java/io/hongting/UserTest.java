package io.hongting;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.usthe.sureness.util.Md5Util;
import io.hongting.entity.Comment;
import io.hongting.entity.Post;
import io.hongting.entity.User;

import io.hongting.mapper.PostMapper;
import io.hongting.mapper.ResourceMapper;
import io.hongting.search.entity.BlogSearchIndex;
import io.hongting.service.*;

import io.hongting.utils.UserThreadLocal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.util.List;


/**
 * @author hongting
 * @create 2021 11 09 6:07 PM
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {


    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;


    @Autowired
    AuthService authService;

    @Autowired
    PostMapper postMapper;

    @Autowired
    CommentService commentService;

    @Autowired
    MailService mailService;
    @Test
    public void saveUser(){
        User user = new User();
        user.setPassword("Rachel666@");
        user.setUsername("rachel");
        user.setNickname("rachel");
        user.setEmail("hongtingwong828gmail.com");
        userService.saveUser(user);
    }

    @Test
    public void testauthenticate(){
        User user = UserThreadLocal.getUser();
        System.out.println(user.getUsername());
    }


    @Test
    public void testMail() throws MessagingException {
        mailService.sendHtmlMail("hongtingwong828@gmail.com", "test mail", "test mail");
    }

    @Test
    public void savePost(){
        Post post = postService.getById(1);
        post.setDescription("testing");
        post.setContent("testing");
    }

    @Test
    public void getArticlesId (){
        postMapper.getAllArticleId();
    }

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Test
    public void es (){
        elasticsearchRestTemplate.indexOps(BlogSearchIndex.class);
    }



    @Test
    public void test123123(){
        LambdaQueryWrapper<Comment> eq = new LambdaQueryWrapper<Comment>().eq(Comment::getId, 83L);
        Comment comment = commentService.getBaseMapper().selectOne(eq);
        Boolean isAdmin = comment.getIsAdmin();
        System.out.println(isAdmin);
    }

    @Test
    public void comment () throws MessagingException {
        Comment comment = new Comment();
        comment.setParentId(88L);
        comment.setForeignId(9L);
        comment.setNickname("test");
        comment.setEmail("hongtingwong828@gmail.com");
        comment.setContent("test");
        commentService.saveComment(comment);
    }



}
