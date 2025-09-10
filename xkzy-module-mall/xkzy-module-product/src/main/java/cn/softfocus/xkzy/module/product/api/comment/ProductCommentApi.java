package cn.softfocus.xkzy.module.product.api.comment;

import cn.softfocus.xkzy.module.product.api.comment.dto.ProductCommentCreateReqDTO;

/**
 * 产品评论 API 接口
 *
 * 三
 */
public interface ProductCommentApi {

    /**
     * 创建评论
     *
     * @param createReqDTO 评论参数
     * @return 返回评论创建后的 id
     */
    Long createComment(ProductCommentCreateReqDTO createReqDTO);

}
