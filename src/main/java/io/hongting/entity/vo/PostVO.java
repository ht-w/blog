package io.hongting.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hongting
 * @create 2021 11 13 10:47 PM
 */


@Data
public class PostVO implements Serializable {

    private Long id;

    private Long categoryId;

    private String title;

    private String categoryName;


    private String description;

    private String image;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;
}
