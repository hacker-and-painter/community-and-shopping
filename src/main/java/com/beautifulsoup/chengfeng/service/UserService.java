package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.UserVo;

public interface UserService {
    UserVo findUserByNickname(String nickname);
}
