package com.beautifulsoup.chengfeng.controller.community;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value="普通帖子",tags= {"普通帖子Controller"},description = "普通帖子",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/news")
public class PostNewsController {



}
