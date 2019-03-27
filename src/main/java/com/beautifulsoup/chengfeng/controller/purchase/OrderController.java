package com.beautifulsoup.chengfeng.controller.purchase;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value="商品订单",tags= {"商品订单Controller"},description = "商品订单",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/order")
public class OrderController {



}
