package com.beautifulsoup.chengfeng.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  OrderStatus {
    CANCEL(0,"订单已取消"),
    UNPAID(1,"订单未支付"),
    PAID(2,"订单已支付"),
    UNRECEIVED(3,"未收货"),
    RECEIVED(4,"已收货")
    ;
    private Integer code;
    private String status;


}
