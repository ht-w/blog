package io.hongting.service.impl;

import io.hongting.entity.Comment;
import io.hongting.entity.Post;
import io.hongting.entity.vo.Mail;
import io.hongting.service.CommentService;
import io.hongting.service.MailService;
import io.hongting.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author hongting
 * @create 2022 02 09 4:03 PM
 */

@Service
public class MailServiceImpl implements MailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Value("${spring.mail.username}")
    private String emailFrom;


    @Async("taskExecutor")
    @Override
    public void sendTemplateMail(Comment comment) throws MessagingException {
        Mail mail = new Mail();
        Long foreignId = comment.getForeignId();
        Post post = postService.getById(foreignId);
        String title = post.getTitle();
        mail.setArticleId(foreignId);
        mail.setTitle(title);
        mail.setNickname(comment.getNickname());
        mail.setContent(comment.getContent());
        mail.setBlogName("康庭的博客");
        Long parentId = comment.getParentId();
        mail.setReplyFlag(true);
        if ( parentId!= null){
            Comment parentComment = commentService.getById(parentId);
            mail.setReplyNickname(parentComment.getNickname());
            mail.setReplyContent(parentComment.getContent());
            mail.setReplyEmail(parentComment.getEmail());
            Context context = new Context();
            context.setVariable("mail", mail);
            String emailContent = templateEngine.process("emailTemplate", context);
            this.sendHtmlMail(parentComment.getEmail(), title, emailContent);
        }else{
            mail.setReplyFlag(false);
            Context context = new Context();
            context.setVariable("mail",mail);
            String emailContent = templateEngine.process("emailTemplate", context);
            sendHtmlMail(emailFrom, title, emailContent);


        }



    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailFrom);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(mimeMessage);

    }
}
