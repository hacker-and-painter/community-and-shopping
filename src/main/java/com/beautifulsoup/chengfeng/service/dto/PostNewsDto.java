package com.beautifulsoup.chengfeng.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostNewsDto {

    @Length(max=100,message="标题长度过长")
    @NotBlank(message="标题不能为空")
    @ApiModelProperty(value="标题",name="title",example="我最近都在做什么呢?",required=true)
    private String title;

    @Length(max=1000,message="描述信息过多")
    @ApiModelProperty(value="描述信息",name="description",example="这里是帖子的描述信息",required=false)
    private String description;

    @ApiModelProperty(value="帖子内容",name="newsDetail",example="这里是帖子的内容详情",required=true)
    private String newsDetail;

    @ApiModelProperty(value="帖子类型",name="type",example="这里是帖子的类型",required=true)
    private Byte type;

    @ApiModelProperty(value="图片地址",name="imgUrl",example="这里是图片的地址,逗号分隔,不传的话就上传文件",required=false)
    private String imgUrl;
}
