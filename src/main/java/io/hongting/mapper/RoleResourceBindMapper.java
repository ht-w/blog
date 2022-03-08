package io.hongting.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.entity.RoleResourceBind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.hongting.entity.vo.RoleResourceBindVO;
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
 * @since 2022-01-24
 */

@Mapper
@Repository
public interface RoleResourceBindMapper extends BaseMapper<RoleResourceBind> {

    List<RoleResourceBindVO> pageRoleResourceBindVO(Page<RoleResourceBindVO> pages);

    RoleResourceBindVO getById (@Param("id") Long id);
}
