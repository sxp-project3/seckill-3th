package com.suixingpay.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jalr
 * @program: seckill-3th
 * @description: 测试用的 book 实体
 * <p>
 * Created by Jalr4ever on 2019/12/9.
 */
public class Book implements Serializable {

    private Integer id;
    private String name;
    private Date printDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }
}
