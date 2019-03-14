package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.UserVo;
import com.beautifulsoup.chengfeng.service.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserVo findUserByNickname(String nickname);
    UserVo registryUserInfo(UserDto userDto, MultipartFile[] files);
    UserVo updateUserInfo(UserVo userVo, MultipartFile[] files);
    String resetPassword(String nickname,String rawPassword,String newPassword,String phone,String email);
}
