package com.learn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    /**
     * 商品
     */
    private Integer id;
    private String name;        //名称
    private Integer number;     //数量
}
