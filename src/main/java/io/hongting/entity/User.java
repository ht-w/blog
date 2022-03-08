package io.hongting.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message="username cannot be null" )
    @Length(min =4 , max=12, message = "Username length must between 6 and 20")
    private String username;

    @NotBlank(message="nickname cannot be null")
    private String nickname;

    @NotBlank(message="password cannot be null")
    @Length(min =6 , max=20, message = "Password length must between 6 and 20")
    private String password;

    private String salt;

    @Email(message ="Invalid email format")
    private String email;

    private String avatar;

    private String description;

    @TableField(exist = false)
    private Long role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
