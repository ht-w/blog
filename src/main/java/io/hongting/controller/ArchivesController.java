package io.hongting.controller;

import io.hongting.common.Result;
import io.hongting.common.ResultType;
import io.hongting.common.exception.Assert;
import io.hongting.entity.Archive;
import io.hongting.entity.vo.ArchivesVO;
import io.hongting.service.ArchiveService;
import io.hongting.utils.annotation.AuthorizationNotRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author hongting
 * @create 2021 12 16 3:14 PM
 */

@RestController
@RequestMapping("/archives")
public class ArchivesController {

    @Autowired
    private ArchiveService archiveService;


    @GetMapping()
    public Result list (){
        List<Archive> archives = archiveService.getArchives();
        Assert.notNull(archives, ResultType.ARCHIVE_IS_NULL);
        return Result.ok().data("archivesList", archives);
    }

}
