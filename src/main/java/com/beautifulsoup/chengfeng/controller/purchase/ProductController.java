package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.ProductSimpleVo;
import com.beautifulsoup.chengfeng.controller.vo.PurchaseProductVo;
import com.beautifulsoup.chengfeng.service.PurchaseProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value="商品信息",tags= {"商品信息Controller"},description = "商品信息",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private PurchaseProductService purchaseProductService;

    @ApiOperation(value="分页列出商品简单信息",notes="分页列出商品简单信息")
    @GetMapping(value="/list/simple/{categoryId}/{pageSize}/{pageNum}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<ProductSimpleVo>> getSimpleProductsByCategoryId(
            @PathVariable("categoryId")Integer categoryId,@PathVariable("pageNum")Integer pageNum, @PathVariable("pageSize")Integer pageSize) {
        List<ProductSimpleVo> productSimpleVos = purchaseProductService.findSimpleProductsByCategoryId(categoryId,pageNum,pageSize);
        return ResponseResult.createBySuccess(productSimpleVos);
    }


    @ApiOperation(value="查询商品详细信息",notes="查询商品详细信息")
    @GetMapping(value="/{productId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<PurchaseProductVo> getProductDetailInfo(@PathVariable("productId")Integer productId) {
        PurchaseProductVo product = purchaseProductService.findProductDetailInfoById(productId);
        return ResponseResult.createBySuccess(product);
    }

}
