package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.pojo.PurchaseOrderItem;
import com.beautifulsoup.chengfeng.serializable.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseOrderVo {
    private Long orderNo;

    private String nickname;

    private String avatar;

    private ShippingVo shippingVo;

    private BigDecimal payment;

    private Integer status;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date paymentTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date sendTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date endTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date closeTime;

    private List<PurchaseOrderItemVo> orderItems;
}
