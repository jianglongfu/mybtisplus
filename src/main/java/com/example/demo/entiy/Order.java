package com.example.demo.entiy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;


@TableName(value = "order_info")
@Data
public class Order {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;


    private String orderName;

    private BigDecimal orderPrice;

}
