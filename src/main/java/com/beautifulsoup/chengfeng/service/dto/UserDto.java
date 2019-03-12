package com.beautifulsoup.chengfeng.service.dto;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.serializable.Long2DateDeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

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

    @Pattern(regexp = ChengfengConstant.PHONE_REGEX,message = "手机号格式不正确")
    private String phone;

    private String idcard;

    private Integer communityId;

    private String email;

    private Integer integral;

    private String gender;

    @JsonDeserialize(using = Long2DateDeSerializer.class)
    private Date birthday;

    private String avatar;

    private String motto;

    private Date signUp;
}
