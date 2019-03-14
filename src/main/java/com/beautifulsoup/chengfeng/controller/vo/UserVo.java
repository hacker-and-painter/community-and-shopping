package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.security.UserGrantedAuthority;
import com.beautifulsoup.chengfeng.serializable.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo implements Serializable {
    @NotNull(message = "id字段不能为空")
    @Pattern(regexp = ChengfengConstant.Validation.NUMBER_REGEX,message = "id不合法")
    private Integer id;

    private String username;

    private String nickname;

    private String idcard;

    private Integer integral;

    private String gender;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date birthday;

    private String avatar;

    private String motto;

    private String email;

    private String phone;

    private Integer communityId;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date signUp;

    private List<UserGrantedAuthority> grantedAuthorities;
}
