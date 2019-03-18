package com.beautifulsoup.chengfeng.service.dto;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.security.UserGrantedAuthority;
import com.beautifulsoup.chengfeng.serializable.Long2DateDeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto {
    @Length(max = 50,message = "用户名过长")
    private String username;

    @NotBlank(message = "昵称不能为空")
    @Length(max = 100,message = "昵称过长")
    private String nickname;

    @NotBlank(message = "密码不能为空")
    @Length(min = 5,max = 20,message = "密码长度在5-20之间")
    private String password;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = ChengfengConstant.Validation.PHONE_REGEX,message = "手机号格式不正确")
    private String phone;

    @NotNull(message = "社区id不能为空")
    private Integer communityId;

    private String idcard;

    private String email;

    private Integer integral;

    private String gender;

    @JsonDeserialize(using = Long2DateDeSerializer.class)
    private Date birthday;

    private String avatar;

    private String motto;


    private List<UserGrantedAuthority> grantedAuthorities;

    private Date signUp;
}
