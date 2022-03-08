package io.hongting.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.common.Result;
import io.hongting.entity.vo.RoleResourceBindVO;
import io.hongting.service.RoleResourceBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hongting
 * @since 2022-01-24
 */
@RestController
@RequestMapping("/roleResourceBind")
public class RoleResourceBindController {


    @Autowired
    private RoleResourceBindService roleResourceBindService;



    @GetMapping("/{page}/{limit}")
    public Result listByPage(@PathVariable Long page, @PathVariable Long limit){
        Page<RoleResourceBindVO> pages =  roleResourceBindService.getRoleResourceBindList(page, limit);
        Map<String, Object> map = new HashMap<>();
        map.put("resourceList", pages.getRecords());
        map.put("total", pages.getTotal());
        return Result.ok().data(map);
    }


    @GetMapping("/{id}")
    public Result getRoleResourceBindVOById(@PathVariable Long id){
        RoleResourceBindVO roleResourceBindVO = roleResourceBindService.getRoleResourceBindVOById(id);
        return Result.ok().data("data", roleResourceBindVO);
    }

    @PostMapping
    public Result save (@RequestBody RoleResourceBindVO roleResourceBindVO){
        boolean saved = roleResourceBindService.save(roleResourceBindVO);
        if (saved){
            return Result.ok().message("Successfully created");
        }else{
            return Result.error().message("Failed to create");
        }
    }

    @DeleteMapping("/{id}")
    public Result remove (@PathVariable("id") Long resourceId){
        if(roleResourceBindService.remove(resourceId)){
            return Result.ok().message("Successfully deleted");
        }else{
            return Result.error().message("Fail to delete");
        }
    }

    @PutMapping
    public Result update(@RequestBody  RoleResourceBindVO roleResourceBindVO){
        boolean updated = roleResourceBindService.update(roleResourceBindVO);
        if (updated){
            return Result.ok().message("Successfully updated");
        }else{
            return Result.error().message("Failed to update");
        }
    }



}

