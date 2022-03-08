package io.hongting.service;

import io.hongting.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */
public interface CategoryService extends IService<Category> {

    boolean saveCategory(Category category);

    boolean updateCategory (Category category);
}
