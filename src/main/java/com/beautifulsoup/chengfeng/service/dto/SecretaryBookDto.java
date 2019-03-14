package com.beautifulsoup.chengfeng.service.dto;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SecretaryBookDto {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = ChengfengConstant.Validation.PHONE_REGEX,message = "手机号格式不正确")
    private String phone;

    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = ChengfengConstant.Validation.EMAIL_REGEX,message = "邮箱格式不正确")
    private String email;

    @Length(max = 500,message = "描述内容过多")
    private String description;
}
