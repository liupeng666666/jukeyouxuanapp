package com.ggh.vo;

import com.ggh.entity.Comment;
import com.ggh.entity.JkUser;
import lombok.Data;

/**
 * @author chaihu
 * @function
 * @date 2020-04-22 12:02
 */
@Data
public class CommentUserVO {

    private JkUser jkUser;

    private Comment comment;

}
