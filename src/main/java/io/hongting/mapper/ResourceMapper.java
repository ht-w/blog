package io.hongting.mapper;

import io.hongting.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
public interface ResourceMapper extends BaseMapper<Resource> {


    @ResultType(String.class)
    @Select(value = {"SELECT CONCAT(LOWER (res.uri), \"===\", LOWER(res.method),\"===[\",IFNULL(GROUP_CONCAT(DISTINCT r.code),\"\"),\"]\") FROM t_resource res LEFT JOIN t_role_resource_bind bind on res.id = bind.resource_id LEFT JOIN t_role r on r.id = bind.role_id where res.status = 1 and res.is_deleted = 0 group by res.id"})
    List<String> getEnableResourcePathRoleData();

    List<String> getDisableResourcePathData();


    Set <String> getPermissionsByUserId(@Param("userId")Long userId);
}
