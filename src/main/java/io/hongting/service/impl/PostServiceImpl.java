package io.hongting.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.common.exception.BlogException;
import io.hongting.constant.RedisConstant;
import io.hongting.entity.Post;
import io.hongting.entity.vo.PostVO;
import io.hongting.mapper.PostMapper;
import io.hongting.service.CategoryService;
import io.hongting.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.hongting.utils.annotation.ArticleView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */

@Slf4j
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    PostMapper postMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @Override
    public Page<PostVO> getPostVOList( Long page, Long limit) {
        Page<PostVO>  pages = new Page<>(page,limit);
        pages.setRecords(postMapper.pagePostVO(pages));
        return pages;
    }

    @Override
    public Page<PostVO> getPostVOListByCategory(Long page, Long limit, Long categoryId) {
        Page<PostVO>  pages = new Page<>(page,limit);
        pages.setRecords(postMapper.pagePostVOByCategory(pages, categoryId));
        return pages;
    }

    @Override
    public List<Long> getAllArticleId() {
        return postMapper.getAllArticleId();
    }

    @Override
    public Post getNextTitleById(Long id) {
        long nextId = id - 1;
        while (nextId > 0 && this.getBaseMapper().selectById(nextId) == null){
            nextId -=1;
        }
        if (nextId <= 0){
            return null;
        }
        return this.getBaseMapper().selectById(nextId);
    }

    @Override
    public Post getPrevTitleById(Long id) {
        long prevId = id + 1;
        while (prevId <= this.getAllArticleId().size() && this.getBaseMapper().selectById(prevId) == null){
            prevId +=1;
        }
        if (prevId > this.getAllArticleId().size()){
            return null;
        }

        return this.getBaseMapper().selectById(prevId);
    }


}
