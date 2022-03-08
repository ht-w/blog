package io.hongting.entity.vo;

import lombok.Data;

/**
 * @author hongting
 * @create 2022 02 09 4:45 PM
 */


@Data
public class Mail {

    private String title;

    private Long articleId;

    private String nickname;

    private String content;

    private String blogName;

    private String replyNickname;

    private String replyEmail;

    private String replyContent;

    private Boolean replyFlag;

}
