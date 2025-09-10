package cn.softfocus.xkzy.module.system.api.dept;

import cn.softfocus.xkzy.framework.common.util.object.BeanUtils;
import cn.softfocus.xkzy.module.system.api.dept.dto.PostRespDTO;
import cn.softfocus.xkzy.module.system.dal.dataobject.dept.PostDO;
import cn.softfocus.xkzy.module.system.service.dept.PostService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 岗位 API 实现类
 *
 *
 */
@Service
public class PostApiImpl implements PostApi {

    @Resource
    private PostService postService;

    @Override
    public void validPostList(Collection<Long> ids) {
        postService.validatePostList(ids);
    }

    @Override
    public List<PostRespDTO> getPostList(Collection<Long> ids) {
        List<PostDO> list = postService.getPostList(ids);
        return BeanUtils.toBean(list, PostRespDTO.class);
    }

}
