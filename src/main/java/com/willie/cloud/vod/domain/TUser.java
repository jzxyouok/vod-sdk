package com.willie.cloud.vod.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/23 14:56</p>
 */
@Entity
@Table(name = "t_user")
public class TUser {
    private long id;
    private Integer age;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUser tUser = (TUser) o;
        return id == tUser.id &&
                Objects.equals(age, tUser.age) &&
                Objects.equals(name, tUser.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, age, name);
    }
}
