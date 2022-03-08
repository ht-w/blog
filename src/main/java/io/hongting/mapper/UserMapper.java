package io.hongting.mapper;

import io.hongting.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface UserMapper extends BaseMapper<User> {

   String selectRoleByUsername (@Param("username") String username);

}
