package io.hongting.service.impl;

import io.hongting.common.Result;
import io.hongting.common.ResultType;
import io.hongting.common.exception.BlogException;
import io.hongting.common.exception.LoginAcctExistedException;
import io.hongting.entity.Category;
import io.hongting.entity.User;
import io.hongting.mapper.CategoryMapper;
import io.hongting.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Override
    public boolean saveCategory(Category category) {
        try {
            this.save(category);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
                throw new BlogException(ResultType.CATEGORY_NOT_UNIQUE);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCategory(Category category) {
        try{
            this.updateById(category);
        }catch (Exception e){
            if (e instanceof  DuplicateKeyException){
                throw  new BlogException(ResultType.CATEGORY_NOT_UNIQUE);
            }
            return  false;
        }
        return true;
    }


}
