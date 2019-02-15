package com.example.daily.mybatis;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhiyuan
 * @date: 2018-02-24
 * @project: spring-boot-demo
 * @description:
 */
@Data
public class Menu1st implements Serializable {
    private String id;
    private String name;
    private String code;
    private String required;
    private String display;
    private String parentMenu;
    private String description;
    private String status;
}
