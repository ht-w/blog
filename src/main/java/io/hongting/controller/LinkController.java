package io.hongting.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.common.Result;
import io.hongting.entity.Comment;
import io.hongting.entity.Link;
import io.hongting.entity.User;
import io.hongting.entity.vo.CommentVO;
import io.hongting.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hongting
 * @since 2022-02-20
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;


    @GetMapping("/{page}/{limit}")
    public Result listByPage(@PathVariable Long page, @PathVariable Long limit){
        Page<Link> pages = new Page<>(page, limit);
        linkService.page(pages, null);
        List<Link> list = pages.getRecords();
        long total = pages.getTotal();
        Map <String, Object> map = new HashMap<>();
        map.put("linkList", list);
        map.put("total", total);
        return Result.ok().data(map);
    }


    @GetMapping("/published")
    public Result list(){
        List<Link> list = linkService.getPublishedlink();
        return Result.ok().data("linkList", list);
    }


    @GetMapping("/{id}")
    public Result getLinkById(@PathVariable Long id){
        Link link = linkService.getById(id);
        if (link !=null){
            return Result.ok().message("Successfully retrieve the data").data("link" , link);
        }else{
            return Result.error().message("Fail to retrieve the data");
        }
    }


    @PostMapping
    public Result save (@RequestBody Link link){
        boolean saved = linkService.save(link);
        if (saved){
            return Result.ok().message("Successfully saved");
        }else{
            return Result.error().message("Fail to save");
        }
    }

    @PostMapping("/admin")
    public Result adminSave (@RequestBody Link link){
        boolean saved = linkService.save(link);
        if (saved){
            return Result.ok().message("Successfully saved");
        }else{
            return Result.error().message("Fail to save");
        }
    }

    @PutMapping
    public Result update (@RequestBody Link link){
        if (linkService.updateById(link)){
            return Result.ok().message("Successfully updated");
        }else{
            return Result.error().message("Fail to update");
        }
    }


    @PutMapping ("/{id}")
    public Result updateStatus(@PathVariable Long id, @RequestBody Map <String, Object> json ){
        Integer  status = (Integer) json.get("status");
        if( linkService.updateStatus(id, status )){
        return Result.ok().message("Successfully updated the status");
       }else{
           return Result.error().message("Fail to update");
       }
    }


    @DeleteMapping("/{id}")
    public Result remove (@PathVariable Long id){
        if(linkService.removeById(id)){
            return Result.ok().message("Successfully deleted the comment");
        }else{
            return Result.error().message("Fail to delete the comment");
        }
    }



}
