package io.hongting.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hongting
 * @create 2022 02 16 8:00 PM
 */

@Data
public class RoleResourceBindVO {

    private Long id;


    private String uri;

    private String method;

    private Integer status;

    private Long roleId;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;

}
