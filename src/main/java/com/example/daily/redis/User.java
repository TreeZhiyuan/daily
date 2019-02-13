package com.example.daily.redis;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author: zhiyuan
 * @date: 2018-04-03
 * @project: daily
 * @description:
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 带参数构造函数
     *
     * @param id       用户id
     * @param email    用户邮箱地址
     * @param username 用户昵称
     * @param age      年龄
     */
    public User(String id, String email, String username, int age) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.age = age;
    }

    public User() {
        super();
    }

    private String id;
    private String email;
    private String username;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email + id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.getEmail());
    }
}
