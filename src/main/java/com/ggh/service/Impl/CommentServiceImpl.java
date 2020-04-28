package com.ggh.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ggh.common.json.Body;
import com.ggh.entity.Comment;
import com.ggh.entity.JkUser;
import com.ggh.mapper.CommentMapper;
import com.ggh.mapper.JkUserMapper;
import com.ggh.service.CommentService;
import com.ggh.vo.CommentUserVO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chaihu
 * @function 评论service
 * @date 2020-04-20 13:38
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private JkUserMapper jkUserMapper;
    /**
     * 根据商品id查询所有评论信息
     * @param goodsId
     * @return
     */
    @Override
    public Body queryCommentByGoodsId(Integer goodsId) {
        // 查询评论列表
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>()
                .eq(Comment::getGoodsId,goodsId)
                .orderByDesc(Comment::getCreateDate)
                .eq(Comment::getDelFlag,"0")
        );
        // 根据评论查询用户的信息
        List<CommentUserVO> commentUserVOS = new ArrayList<>();
        for (Comment comment : comments) {
            JkUser jkUser = jkUserMapper.selectById(comment.getUserId());
            CommentUserVO commentUserVO = new CommentUserVO();
            commentUserVO.setJkUser(jkUser);
            commentUserVO.setComment(comment);
            commentUserVOS.add(commentUserVO);
        }
        return Body.newInstance(commentUserVOS);
    }
}
