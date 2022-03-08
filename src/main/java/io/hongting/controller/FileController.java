package io.hongting.controller;

import com.baomidou.mybatisplus.extension.api.R;
import io.hongting.common.Result;
import io.hongting.common.ResultType;
import io.hongting.common.exception.BlogException;
import io.hongting.service.FileService;
import io.hongting.utils.annotation.AuthorizationNotRequired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author hongting
 * @create 2021 11 11 2:35 PM
 */


@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;


    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String module = "article";
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);
            return Result.ok().message("Successfully uploaded file").data("url", uploadUrl);
        } catch (IOException e) {
            throw new BlogException(ResultType.UPLOAD_ERROR, e);
        }

    }


    @DeleteMapping
    public Result remove(@RequestParam ("url") String url){
        fileService.removeFile(url);
        return Result.ok().message("Successfully removed file");
    }
}
