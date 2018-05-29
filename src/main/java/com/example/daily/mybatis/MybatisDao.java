package com.example.daily.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @author: zhiyuan
 * @date: 2018-02-24
 * @project: spring-boot-demo
 * @description:
 */
public interface MybatisDao {
    /**
     * if only one base parameter passed then it's does matter whatever you call
     * this parameter in mybatis map xml
     * 
     * @param i
     * @return
     */
    List getMenus(int i);

    /**
     * also you can use @Param to locate parameter name in mybatis map xml
     * 
     * @param requied
     * @param parmentMenu
     * @return
     */
    List<Map> getMenus4ManyParams(int requied, String parmentMenu);

    List<Map> getMenus4MapParams(Map paramsMap);

    List<Map> getMenus4POJO(Menu1st menu);

    List<Menu> getMapMenuSeprate(String roleOid);

    List<Menu> getMenuDiffParamType(@Param("menu") Menu menu, @Param("page") int page,
            @Param("rows") int rows);
}
