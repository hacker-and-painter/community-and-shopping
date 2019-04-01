package com.beautifulsoup.chengfeng.controller.manage;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.PurchaseInfoVo;
import com.beautifulsoup.chengfeng.service.PurchaseInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value="售卖信息",tags= {"售卖信息Controller"},description = "售卖信息",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/purchase")
public class PurchaseInfoController {

    @Autowired
    private PurchaseInfoService purchaseInfoService;

    @GetMapping("/get")
    @ResponseBody
    public ResponseResult<List<PurchaseInfoVo>> getAllPurchaseInfoVo(){
        List<PurchaseInfoVo> purchaseInfo = purchaseInfoService.getPurchaseInfo();
        return ResponseResult.createBySuccess("分析数据获取成功",purchaseInfo);
    }
}
