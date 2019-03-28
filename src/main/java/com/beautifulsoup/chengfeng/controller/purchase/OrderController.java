package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.AssembleDetailVo;
import com.beautifulsoup.chengfeng.controller.vo.AssembleSimpleVo;
import com.beautifulsoup.chengfeng.controller.vo.PurchaseOrderVo;
import com.beautifulsoup.chengfeng.pojo.PurchaseOrder;
import com.beautifulsoup.chengfeng.service.PurchaseOrderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="商品订单",tags= {"商品订单Controller"},description = "商品订单",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private PurchaseOrderService orderService;

    @GetMapping(value = "/assemble/list/{productId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<AssembleSimpleVo>> getAllSimpleAssembleLists(@PathVariable("productId")Integer productId){
        List<AssembleSimpleVo> assembleSimpleVos=orderService.listAllSimpleAssembleLists(productId);
        return ResponseResult.createBySuccess("拼单列表获取成功",assembleSimpleVos);
    }

    @GetMapping(value = "/assemble/get/{assembleId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<AssembleDetailVo> getAssembleDetailInfo(@PathVariable("assembleId")Integer assembleId){
        AssembleDetailVo assembleDetailInfo=orderService.getAssembleDetailInfoById(assembleId);
        if (assembleDetailInfo != null) {
            return ResponseResult.createBySuccess("拼单详情获取成功",assembleDetailInfo);
        }
        return ResponseResult.createByErrorMessage("拼单详情获取失败");
    }


    @PostMapping(value = "/assemble/create/{skuId}/{count}/{shippingId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<AssembleDetailVo> createNewAssemble(@PathVariable("skuId")Integer productId,
                                                              @PathVariable("count") Integer count,@PathVariable("shippingId") Integer shippingId){
        AssembleDetailVo assembleDetailVo=orderService.createNewAssemble(productId,count,shippingId);
        if (assembleDetailVo != null) {
            return ResponseResult.createBySuccess("拼单创建成功",assembleDetailVo);
        }
        return ResponseResult.createByErrorMessage("拼单创建失败");
    }

    @PutMapping(value = "/assemble/join/{assembleId}/{skuId}/{count}/{shippingId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<AssembleDetailVo> joinExistsAssemble(@PathVariable("assembleId")Integer assembleId,
                                                               @PathVariable("skuId") Integer skuId,@PathVariable("count") Integer count,@PathVariable("shippingId") Integer shippingId){
        AssembleDetailVo assembleDetailInfo=orderService.joinExistsAssemble(assembleId,skuId,count,shippingId);
        return ResponseResult.createBySuccess("加入拼单成功",assembleDetailInfo);
    }

    @PostMapping("/place/separate/{skuId}/{count}/{shippingId}")
    @ResponseBody
    public ResponseResult<PurchaseOrderVo> placeSeparateOrder(@PathVariable("skuId")Integer skuId, @PathVariable("count")Integer count,
                                                              @PathVariable("shippingId")Integer shippingId){
        PurchaseOrderVo order=orderService.placeSeparateOrder(skuId,count,shippingId);
        if (order != null) {
            return ResponseResult.createBySuccess("下单成功",order);
        }
        return ResponseResult.createByErrorMessage("订单创建失败");
    }
}
