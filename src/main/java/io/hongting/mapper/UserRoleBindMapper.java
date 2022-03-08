package io.hongting.mapper;

import io.hongting.entity.UserRoleBind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hongting
 * @since 2022-01-24
 */

@Mapper
@Repository
public interface UserRoleBindMapper extends BaseMapper<UserRoleBind> {

}
