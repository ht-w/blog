package io.hongting.mapper;

import io.hongting.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */

@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {


}
