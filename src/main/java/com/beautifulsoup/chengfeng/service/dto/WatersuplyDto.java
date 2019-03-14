package com.beautifulsoup.chengfeng.service.dto;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.pojo.WatersuplyDetails;
import com.beautifulsoup.chengfeng.serializable.Long2DateDeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class WatersuplyDto {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = ChengfengConstant.Validation.PHONE_REGEX,message = "手机号格式不正确")
    private String phone;

    @JsonDeserialize(using = Long2DateDeSerializer.class)
    private Date hopeTime;


    @Length(max = 150,message = "地址过长")
    @NotBlank(message = "地址不能为空")
    private String address;

    @Length(max = 500,message = "描述内容过多")
    private String description;

    @Size(min = 1,message = "预约水的数量不能少于1")
    @NotNull(message = "预约水的数量不能少于1")
    private List<WatersuplyDetails> detailsList;

}
