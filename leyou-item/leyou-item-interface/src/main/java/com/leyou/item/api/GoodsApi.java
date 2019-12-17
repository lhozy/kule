package com.leyou.item.api;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 商品API
 */
public interface GoodsApi {

    /**
     * 根据条件分页查询spu
     * @param key 查询条件
     * @param saleable 默认为false
     * @param page 页码
     * @param rows 行数
     * @return spuBO
     */
    @GetMapping("spu/page")
    PageResult<SpuBo> querySpuByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    );

      /**
     * 根据spuId查询spu详情
     * @param spuId spu的ID
     * @return spu详情
     */
    @GetMapping("spu/detail/{spuId}")
    ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable Long spuId);

    /**
     * 根据spuId查询sku集合
     * @param spuId spu的id
     * @return sku集合
     */
    @GetMapping("sku/list")
    ResponseEntity<List<Sku>> querySkusBySpuId(@RequestParam("id") Long spuId);
}
