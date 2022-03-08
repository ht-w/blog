package io.hongting.sureness.provider;

import com.usthe.sureness.matcher.PathTreeProvider;
import com.usthe.sureness.util.SurenessCommonUtil;
import io.hongting.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author hongting
 * @create 2022 01 24 2:42 PM
 */
@Component
public class DatabasePathTreeProvider implements PathTreeProvider {

    @Autowired
    private ResourceService resourceService;

    @Override
    public Set<String> providePathData() {

        return SurenessCommonUtil.attachContextPath(getContextPath(), resourceService.getAllEnableResourcePath());

    }

    @Override
    public Set<String> provideExcludedResource() {
        return SurenessCommonUtil.attachContextPath(getContextPath(), resourceService.getAllDisableResourcePath());
    }

}