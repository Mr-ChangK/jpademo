package com.chk.demojpa.dao;

import com.chk.demojpa.entity.Demo;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoHighDaoTest {
    @Resource
    private DemoHighDao demoDao;
    @Test
    public void aaaaa() {
        Demo demo=demoDao.aaaaa(1);
//        insert into demo (name,age) values('chk123',18);
        Assert.assertEquals(1,demo.getId());
        Assert.assertEquals("chk123",demo.getName());
    }

    @Test
    public void queryByIdIn() {
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Sort sort1=new Sort(Sort.Order.desc("id"));
        List<Demo> demos=demoDao.queryByIdIn(Lists.newArrayList(1,2,3,4,5),sort);
        Assert.assertEquals(5,demos.get(0).getId());
    }

    @Test
    public void queryByIdIn1() {
        Pageable pageable=PageRequest.of(1,3);
        List<Demo> demos=demoDao.queryByIdIn(Lists.newArrayList(1,2,3,4,5,6,7,8,9),pageable);
        Assert.assertEquals(3,demos.size());
        Assert.assertEquals(4,demos.get(0).getId());
    }

    @Test
    public void queryById() {
//        insert into demo (name,age) values('chk123',18);
        DemoMap demoMap=demoDao.queryById(1);
        Assert.assertEquals("chk123",demoMap.getName());
        Assert.assertEquals("18",demoMap.getAge());
        Assert.assertEquals("chk123 18",demoMap.getNameAndAge());
    }
}