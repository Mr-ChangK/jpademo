package com.chk.demojpa.dao;

import com.chk.demojpa.entity.Demo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemoHighDao extends JpaRepository<Demo,Integer> {
    @Query("select demo from Demo demo where demo.id=:id")
    Demo aaaaa(@Param("id") int id);

    List<Demo> queryByIdIn(List<Integer> id, Sort sort);

    List<Demo> queryByIdIn(List<Integer> id, Pageable pageable);

    DemoMap queryById(int id);
}
