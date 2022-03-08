package io.hongting.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author hongting
 * @since 2021-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickname;

    private String email;   

    private String avatar;

    private String content;

    private LocalDateTime createTime;


    private Long parentId;

    private Long foreignId;

    @TableField(exist = false)
    private Comment parentComment;


    @TableField(exist = false)
    private String parentNickname;


    private Boolean isAdmin;



    @TableField(exist = false)
    private List<Comment> childComment = new ArrayList<>();

    @TableLogic
    private Integer isDeleted;


}
