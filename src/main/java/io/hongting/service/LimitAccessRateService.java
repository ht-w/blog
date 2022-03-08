package io.hongting.service;

import io.hongting.utils.annotation.LimitAccessRate;

/**
 * @author hongting
 * @create 2021 12 13 10:39 PM
 */


public interface LimitAccessRateService {

    Boolean limit (String ipAddr, String uri, LimitAccessRate limitAccessRate);
}
