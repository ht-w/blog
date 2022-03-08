package io.hongting.search.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author hongting
 * @create 2021 12 22 3:36 PM
 */


@Data
public class SearchVO {

    private Long id;

    private String title;

    private String description;

    private Date updateTime;
}
