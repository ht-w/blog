package io.hongting.service;

import io.hongting.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongting
 * @since 2022-01-24
 */
public interface ResourceService extends IService<Resource> {

    Set<String> getAllEnableResourcePath();

    Set<String> getAllDisableResourcePath();


}
