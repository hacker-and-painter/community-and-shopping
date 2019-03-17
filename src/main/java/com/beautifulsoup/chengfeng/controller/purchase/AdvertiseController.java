package com.beautifulsoup.chengfeng.controller.purchase;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.PurchaseProductVo;
import com.beautifulsoup.chengfeng.service.AdvertiseService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value="广告栏目",tags= {"广告栏目Controller"},description = "广告栏目",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/ad")
public class AdvertiseController {

    @Autowired
    private AdvertiseService advertiseService;

    @GetMapping(value = "/banner/{info}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<PurchaseProductVo>> getAllPurchaseProduct(@PathVariable("info") String info){
        List<PurchaseProductVo> productVoList = advertiseService.getAllProductsByInfo(info);
        return ResponseResult.createBySuccess(productVoList);
    }


}
