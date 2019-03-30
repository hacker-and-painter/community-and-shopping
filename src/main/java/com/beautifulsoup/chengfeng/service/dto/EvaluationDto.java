package com.beautifulsoup.chengfeng.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EvaluationDto {
    @Length(max=10000,message="评论内容过多")
    @ApiModelProperty(value="评价内容",name="content",example="针对某个商品的评价",required=true)
    private String content;
    @Length(max=1000,message="图片地址过长")
    @ApiModelProperty(value="图片地址",name="imageUrl",example="评价图片,多张图片逗号分隔",required=true)
    private String imageUrl;
    @NotNull(message = "商品sku信息id不能为空")
    @ApiModelProperty(value="商品sku id",name="skuId",example="1",required=true)
    private Integer skuId;
    @NotNull(message = "评价类型不能为空")
    @ApiModelProperty(value="评价类型",name="type",example="1",required=true)
    private Integer type;
}
