package io.hongting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.entity.Resource;
import io.hongting.entity.RoleResourceBind;
import io.hongting.entity.vo.CommentVO;
import io.hongting.entity.vo.RoleResourceBindVO;
import io.hongting.mapper.RoleResourceBindMapper;
import io.hongting.service.ResourceService;
import io.hongting.service.RoleResourceBindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongting
 * @since 2022-01-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleResourceBindServiceImpl extends ServiceImpl<RoleResourceBindMapper, RoleResourceBind> implements RoleResourceBindService {


    @Autowired
    private RoleResourceBindMapper roleResourceBindMapper;

    @Autowired
    private ResourceService resourceService;


    @Override
    public Page<RoleResourceBindVO> getRoleResourceBindList(Long page, Long limit) {
        Page<RoleResourceBindVO>  pages = new Page<>(page,limit);
        pages.setRecords(roleResourceBindMapper.pageRoleResourceBindVO(pages));
        return pages;
    }

    @Override
    public boolean save(RoleResourceBindVO roleResourceBindVO) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(roleResourceBindVO, resource);
        Long roleId = roleResourceBindVO.getRoleId();
        boolean saved = resourceService.save(resource);
        if (roleResourceBindVO.getStatus() == 9){
            return true;
        }
        if (saved && roleResourceBindVO.getStatus() != 9 ){
            LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<Resource>().eq(Resource::getUri, resource.getUri());
            Resource savedResource = resourceService.getOne(queryWrapper);
            RoleResourceBind roleResourceBind = new RoleResourceBind();
            roleResourceBind.setRoleId(roleId);
            roleResourceBind.setResourceId(savedResource.getId());
            return this.save(roleResourceBind);
        }else{
            return false;
        }
    }

    @Override
    public boolean remove(Long resourceId) {
        return resourceService.removeById(resourceId);
    }

    @Override
    public RoleResourceBindVO getRoleResourceBindVOById(Long id) {
        return roleResourceBindMapper.getById(id);
    }

    @Override
    public boolean update(RoleResourceBindVO roleResourceBindVO) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(roleResourceBindVO, resource);
        Long id = roleResourceBindVO.getId();
        LambdaQueryWrapper<RoleResourceBind> queryWrapper = new LambdaQueryWrapper<RoleResourceBind>().eq(RoleResourceBind::getResourceId, id);
        RoleResourceBind roleResourceBind = this.getBaseMapper().selectOne(queryWrapper);
        if (roleResourceBindVO.getStatus() == 1){
            roleResourceBind.setRoleId(roleResourceBindVO.getRoleId());
        }else{
            roleResourceBind.setRoleId(0L);
        }
        return resourceService.updateById(resource) && this.updateById(roleResourceBind);

    }


}
