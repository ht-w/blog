package io.hongting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import io.hongting.entity.vo.PostVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */
public interface PostService extends IService<Post> {

    Page<PostVO> getPostVOList(Long page, Long limit);

    Page<PostVO> getPostVOListByCategory(Long page, Long limit, Long categoryId);

    List<Long> getAllArticleId ();

    Post getNextTitleById(Long id);

    Post getPrevTitleById(Long id);

    ;
}
