package io.hongting.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hongting
 * @create 2021 12 16 2:56 PM
 */

@Data
public class ArchivesVO implements Serializable {

    private Long id;

    private String title;

    private String image;

    private LocalDateTime updateTime;
}
