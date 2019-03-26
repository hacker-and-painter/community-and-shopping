package com.beautifulsoup.chengfeng.service.dto;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ShippingDto {

    @Length(max=150,message="收货人名过长")
    @NotBlank(message="收货人不能为空")
    @ApiModelProperty(value="收货人",name="receiverName",example="寒泉子",required=true)
    private String receiverName;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = ChengfengConstant.Validation.PHONE_REGEX,message = "手机号格式不正确")
    @ApiModelProperty(value="手机号",name="receiverPhone",example="17864195301",required=true)
    private String receiverPhone;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    @NotBlank(message = "收货地址不能为空")
    @ApiModelProperty(value="收货地址",name="receiverAddress",example="济南市长清区山东师范大学",required=true)
    private String receiverAddress;

    @NotBlank(message = "邮编不能为空")
    @Length(max=10,message="邮编过长")
    @ApiModelProperty(value="邮编",name="receiverZip",example="250358",required=true)
    private String receiverZip;
}
