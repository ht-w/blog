package io.hongting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongting
 * @since 2022-02-20
 */
public interface LinkService extends IService<Link> {


    List<Link> getPublishedlink();

    Boolean updateStatus(Long id, Integer status);
}
