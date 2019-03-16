package com.beautifulsoup.chengfeng.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class PostReplyDto {

    @NotBlank(message = "回帖内容不能为空")
    @Length(max=1000,message="回帖内容过多")
    @ApiModelProperty(value="回帖内容",name="content",example="针对某个帖子的回帖",required=true)
    private String content;

    @NotNull(message = "帖子id不能为空")
    @ApiModelProperty(value="帖子id",name="postId",example="1",required=true)
    private Integer postId;

    @NotBlank(message = "回帖人不能为空")
    @Length(max=100,message="回帖人昵称过长")
    @ApiModelProperty(value="回帖人昵称",name="nickname",example="王小灏",required=true)
    private String nickname;

    @Length(max=1000,message="头像地址过长")
    private String avatar;


    private Integer parentId;

    private Integer isParent;

    @ApiModelProperty(value="图片地址",name="imgUrl",example="这里是图片的地址,逗号分隔,不传的话就上传文件",required=false)
    private String imgUrl;
}
