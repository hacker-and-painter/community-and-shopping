package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.PurchaseCartVo;
import com.beautifulsoup.chengfeng.service.PurchaseCartService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value="购物车",tags= {"购物车Controller"},description = "购物车",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private PurchaseCartService purchaseCartService;

    @PutMapping(value = "/add/{productId}/{count}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<PurchaseCartVo> addNewProduct(@PathVariable("productId")Integer productId,@PathVariable("count")Integer count){
        PurchaseCartVo purchaseCartVo = purchaseCartService.addNewPurchaseProduct(productId, count);
        if (purchaseCartVo!=null){
            ResponseResult.createBySuccess("商品添加购物车成功",purchaseCartVo);
        }
        return ResponseResult.createByErrorMessage("商品添加购物车失败");
    }
}
