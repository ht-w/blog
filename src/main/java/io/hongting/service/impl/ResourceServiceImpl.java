package io.hongting.service.impl;

import io.hongting.entity.Resource;
import io.hongting.mapper.ResourceMapper;
import io.hongting.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongting
 * @since 2022-01-24
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {


    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public Set<String> getAllEnableResourcePath() {
        List<String> list = resourceMapper.getEnableResourcePathRoleData();
        HashSet<String> hashSet = new HashSet<>();
        list.forEach(value -> { hashSet.add(value); });
        return hashSet;

    }

    @Override
    public Set<String> getAllDisableResourcePath() {
        List<String> list = resourceMapper.getDisableResourcePathData();
        HashSet<String> hashSet = new HashSet<>();
        list.forEach(value -> { hashSet.add(value); });
        return hashSet;
    }

}
