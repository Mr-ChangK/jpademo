package com.chk.demojpa.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * create table info(
 *   id int not null auto_increment comment '主键',
 *   name varchar(20) not null comment '姓名',
 *   age int not null comment '年龄',
 *   status char(1) not null default '1' comment '状态',
 *   modify_time timestamp default current_timestamp on update current_timestamp comment '修改时间',
 *   create_time timestamp default current_timestamp comment '创建时间'
 * )
 */
@Data
@Entity
@ToString
public class Demo {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="age")
    private int age;
    @Column(name="create_time")
    private Date createTime;
    @Column(name="modify_time")
    private Date modifyTime;
    @Column(name="status")
    private char status;
}
