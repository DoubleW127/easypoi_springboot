package com.cjh.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: huahua
 * @Date: 2020-09-15 10:05
 */
@Data
@ExcelTarget("users")
public class User implements Serializable {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "生日",format = "yyyy年MM月dd日",width = 30.0)
    private Date bir;
    @Excel(name = "身份证号",width = 30.0)
    private String no;
    @Excel(name = "籍贯")
    private String address;
}
