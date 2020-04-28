package com.ggh.service;


import com.ggh.common.json.Body;

/**
 * @author chaihu
 * @function
 * @date 2020-04-17 19:30
 */
public interface IndexService {
    Body queryBanner();

    Body queryTypeList();

    Body queryRecommendList();

    Body querySearchGoods(String keywords);
}
