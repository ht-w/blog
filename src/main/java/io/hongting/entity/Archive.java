package io.hongting.entity;

import io.hongting.entity.vo.ArchivesVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hongting
 * @create 2021 12 20 1:50 PM
 */


@Data
public class Archive {

    private String year;

    private LocalDateTime date;

    private Integer count;

    private List<ArchivesVO> archives;
}
