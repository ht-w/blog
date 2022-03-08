package io.hongting.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.entity.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hongting
 * @since 2022-02-20
 */

@Component
public interface LinkMapper extends BaseMapper<Link> {

    List<Link> pageLinkList(Page<Link> pages);
}
