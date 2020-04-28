package com.ggh.service;


import com.ggh.common.json.Body;

/**
 * @author chaihu
 * @function
 * @date 2020-04-20 13:38
 */
public interface CommentService {
    Body queryCommentByGoodsId(Integer goodsId);
}
