package cn.softfocus.xkzy.module.product.api.sku;

import cn.softfocus.xkzy.framework.common.util.object.BeanUtils;
import cn.softfocus.xkzy.module.product.api.sku.dto.ProductSkuRespDTO;
import cn.softfocus.xkzy.module.product.api.sku.dto.ProductSkuUpdateStockReqDTO;
import cn.softfocus.xkzy.module.product.dal.dataobject.sku.ProductSkuDO;
import cn.softfocus.xkzy.module.product.service.sku.ProductSkuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

/**
 * 商品 SKU API 实现类
 *
 * 三
 * @since 2022-09-06
 */
@Service
@Validated
public class ProductSkuApiImpl implements ProductSkuApi {

    @Resource
    private ProductSkuService productSkuService;

    @Override
    public ProductSkuRespDTO getSku(Long id) {
        ProductSkuDO sku = productSkuService.getSku(id);
        return BeanUtils.toBean(sku, ProductSkuRespDTO.class);
    }

    @Override
    public List<ProductSkuRespDTO> getSkuList(Collection<Long> ids) {
        List<ProductSkuDO> skus = productSkuService.getSkuList(ids);
        return BeanUtils.toBean(skus, ProductSkuRespDTO.class);
    }

    @Override
    public List<ProductSkuRespDTO> getSkuListBySpuId(Collection<Long> spuIds) {
        List<ProductSkuDO> skus = productSkuService.getSkuListBySpuId(spuIds);
        return BeanUtils.toBean(skus, ProductSkuRespDTO.class);
    }

    @Override
    public void updateSkuStock(ProductSkuUpdateStockReqDTO updateStockReqDTO) {
        productSkuService.updateSkuStock(updateStockReqDTO);
    }

}
