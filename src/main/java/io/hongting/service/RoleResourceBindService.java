package io.hongting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.entity.RoleResourceBind;
import com.baomidou.mybatisplus.extension.service.IService;
import io.hongting.entity.vo.RoleResourceBindVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongting
 * @since 2022-01-24
 */
public interface RoleResourceBindService extends IService<RoleResourceBind> {

    Page<RoleResourceBindVO> getRoleResourceBindList(Long page, Long limit);

    boolean save(RoleResourceBindVO roleResourceBindVO);

    boolean remove(Long resourceId);

    RoleResourceBindVO getRoleResourceBindVOById(Long id);

    boolean update(RoleResourceBindVO roleResourceBindVO);
}
