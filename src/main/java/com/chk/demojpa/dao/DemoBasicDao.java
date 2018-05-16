package com.chk.demojpa.dao;

import com.chk.demojpa.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DemoBasicDao extends JpaRepository<Demo, Integer> {
    /**
     * where name=${name} and age=${age}
     *
     * @param name
     * @param age
     * @return
     */
    Demo findByNameAndAge(String name, int age);

    /**
     * where id in(${ids})
     *
     * @param ids
     * @return
     */
    List<Demo> queryByIdIn(List<Integer> ids);

    /**
     * where id between ${var1} and ${var2}
     *
     * @param var1
     * @param var2
     * @return
     */
    List<Demo> getByIdBetween(int var1, int var2);

    /**
     * where id<${id}
     *
     * @param id
     * @return
     */
    List<Demo> queryByIdLessThan(int id);

    /**
     * where id>${id}
     *
     * @param id
     * @return
     */
    List<Demo> queryByIdGreaterThan(int id);

    /**
     * where id<=${id}
     *
     * @param id
     * @return
     */
    List<Demo> queryByIdLessThanEqual(int id);

    /**
     * where id>=${id}
     *
     * @param id
     * @return
     */
    List<Demo> queryByIdGreaterThanEqual(int id);

    /**
     * where name like ${name}
     *
     * @param name
     * @return
     */
    List<Demo> queryByNameLike(String name);

    /**
     * where name like %name
     *
     * @param name
     * @return
     */
    List<Demo> queryByNameStartingWith(String name);

    /**
     * where name like name%
     *
     * @param name
     * @return
     */
    List<Demo> queryByNameEndingWith(String name);

    /**
     * order by id desc
     *
     * @param ids
     * @return
     */
    List<Demo> queryByIdInOrderByIdDesc(List<Integer> ids);

}
