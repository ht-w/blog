package io.hongting.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hongting
 * @create 2022 01 17 3:26 PM
 */

@Data
public class CommentVO implements Serializable {

    private Long id;

    private Long foreignId;

    private String nickname;

    private String email;

    private String content;

    private String postTitle;

    private LocalDateTime createTime;


}
