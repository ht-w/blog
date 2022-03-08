package io.hongting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.entity.Comment;
import io.hongting.entity.vo.CommentVO;
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
 * @since 2021-11-19
 */

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> getChildCommentsById(@Param("postId") Long postId, @Param("commentId") Long commentId);

    List<CommentVO> pageCommentVO(Page<CommentVO> pages);

    List<CommentVO> pageCommentVOByPost(Page<CommentVO> pages, @Param("postId") Long postId);
}
