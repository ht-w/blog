package io.hongting.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.hongting.entity.vo.ArchivesVO;
import io.hongting.entity.vo.PostVO;
import io.hongting.search.entity.vo.SearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */

@Mapper
@Repository
public interface PostMapper extends BaseMapper<Post> {


    List<PostVO> pagePostVO (Page<PostVO> page);

    List<PostVO> pagePostVOByCategory (Page<PostVO> page , @Param("categoryId") Long categoryId);

    List<Long> getAllArticleId ();

    List<String> getYears ();

    List<ArchivesVO> getArchives ();

}
