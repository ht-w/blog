package io.hongting.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.common.Result;

import io.hongting.entity.Category;

import io.hongting.service.CategoryService;
import io.hongting.utils.annotation.AuthorizationNotRequired;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping("/{id}")
    public Result getCategoryById (@PathVariable Long id){
        Category category = categoryService.getById(id);
        if (category!=null){
            return Result.ok().message("Successfully retrieve the data").data("category", category);
        }else{
            return Result.error().message("Fail to retrieve the data");
        }
    }

    @PostMapping
    public Result save(@RequestBody Category category){
        boolean saved = categoryService.saveCategory(category);
        System.out.println(saved);
        if (saved){
            return Result.ok().message ("Successfully create new category");
        }else{
            return Result.error().message("Fail to create new category");
        }
    }



    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id){
        boolean deleted = categoryService.removeById(id);
        if (deleted){
            return Result.ok().message("Successfully removed the category");
        }else{
            return Result.error().message("Fail to remove the category");
        }
    }


    @GetMapping("/{page}/{limit}")
    public Result listByPage(@PathVariable Long page, @PathVariable Long limit){
        Page<Category> pages = new Page<>(page, limit);
        categoryService.page(pages, null);
        List<Category> categoryList = pages.getRecords();
        long total = pages.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("categories", categoryList);
        map.put("total", total);
        return Result.ok().data(map);
    }


    @PutMapping
    public Result update (@RequestBody Category category){
        boolean updated = categoryService.updateCategory(category);
        if (updated){
            return Result.ok().message("Successfully updated the category");
        }else{
            return Result.error().message("Fail to update the category");
        }
    }


    @GetMapping
    public Result listAll(){
        return Result.ok().data("list", categoryService.list());
    }

}

