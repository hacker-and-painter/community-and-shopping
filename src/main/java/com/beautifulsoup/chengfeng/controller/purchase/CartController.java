package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.PurchaseCartVo;
import com.beautifulsoup.chengfeng.service.PurchaseCartService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value="购物车",tags= {"购物车Controller"},description = "购物车",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private PurchaseCartService purchaseCartService;

    @PostMapping(value = "/add/{skuId}/{count}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<PurchaseCartVo> addNewProduct(@PathVariable("skuId")Integer skuId,@PathVariable("count")Integer count){
        PurchaseCartVo purchaseCartVo = purchaseCartService.addNewPurchaseProduct(skuId, count);
        if (purchaseCartVo!=null){
            return ResponseResult.createBySuccess("商品添加购物车成功",purchaseCartVo);
        }
        return ResponseResult.createByErrorMessage("商品添加购物车失败");
    }

    @GetMapping(value = "/listall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<PurchaseCartVo> getAllCartProducts(){
        PurchaseCartVo purchaseCartVo = purchaseCartService.listAllCartProducts();
        return ResponseResult.createBySuccess(purchaseCartVo);
    }

//    @PutMapping(value = "/update/{skuId}/{count}")

}
