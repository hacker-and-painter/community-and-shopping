package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.ShippingVo;
import com.beautifulsoup.chengfeng.service.PurchaseShippingService;
import com.beautifulsoup.chengfeng.service.dto.ShippingDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value="收货地址",tags= {"收货地址Controller"},description = "投票帖子",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/shipping")
public class ShippingController {

    @Autowired
    private PurchaseShippingService shippingService;

    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<ShippingVo> createNewShipping(@Valid @RequestBody ShippingDto shippingDto, BindingResult result){
        ShippingVo shippingVo=shippingService.createNewShipping(shippingDto,result);
        if (null!=shippingVo){
            return ResponseResult.createBySuccess("订单创建成功",shippingVo);
        }
        return ResponseResult.createByErrorMessage("收货地址创建失败");
    }

    @GetMapping(value = "/listall",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<ShippingVo>> listAllShippings(){
        List<ShippingVo> shippingVos=shippingService.listAllShippings();
        return ResponseResult.createBySuccess(shippingVos);
    }

    @DeleteMapping(value = "/delete/{shippingId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<ShippingVo> deleteShippingById(@PathVariable("shippingId")Integer shippingId){
        ShippingVo shippingVo=shippingService.deleteShippingById(shippingId);
        return ResponseResult.createBySuccess("删除成功",shippingVo);
    }

}
