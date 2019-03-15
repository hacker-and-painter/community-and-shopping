package com.beautifulsoup.chengfeng.controller.community;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value="投票帖子",tags= {"投票帖子Controller"},description = "投票帖子",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/vote")
public class PostVoteController {
}
