package com.example.daily.mybatis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(value = {"menu"})
@Data
public class Menu implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String oid;

    private String pid;

    private String roleOid;

    private String menuName;

    private String menuUrl;

    private String menuSort;

    private Integer isMenu;

    private Integer menuCode;

    private Integer delFlag;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;

    private List<Menu> childs;
}