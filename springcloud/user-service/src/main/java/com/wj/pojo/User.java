package com.wj.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
@Table(name = "user")
@Data
public class User implements Serializable {
    @Id//主键
    @KeySql(useGeneratedKeys = true) //自增
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private Date birth;
    @Transient //数据SQL忽略属性
    private String address;
    private  Integer site;
}