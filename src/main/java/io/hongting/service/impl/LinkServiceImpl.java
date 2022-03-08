package io.hongting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.common.exception.Assert;
import io.hongting.entity.Link;
import io.hongting.entity.vo.CommentVO;
import io.hongting.mapper.LinkMapper;
import io.hongting.service.LinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongting
 * @since 2022-02-20
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {


    @Override
    public List<Link> getPublishedlink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<Link>().eq(Link::getStatus, 1);
        return this.getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<Link>().eq(Link::getId, id);
        Link link = this.getBaseMapper().selectOne(queryWrapper);
        link.setStatus(status);
        return this.updateById(link);
    }
}
