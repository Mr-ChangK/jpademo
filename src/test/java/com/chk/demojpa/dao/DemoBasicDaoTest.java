package com.chk.demojpa.dao;

import com.chk.demojpa.entity.Demo;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoBasicDaoTest {
    @Resource
    private DemoBasicDao demoDao;

    @Test
    public void findByNameAndAge() {
        //insert into demo (name,age) values('ccc',14);
        Demo demo=demoDao.findByNameAndAge("ccc",14);
        Assert.assertEquals(14,demo.getAge());
        Assert.assertEquals("ccc",demo.getName());
    }

    @Test
    public void queryByIdIn() {
//        insert into demo (name,age) values('chk123',18);
//        insert into demo (name,age) values('ccc',14);
//        insert into demo (name,age) values('rrr',17);
//        insert into demo (name,age) values('ttt',12);
        List<Demo> demos=demoDao.queryByIdIn(Lists.newArrayList(1,2,3,4));
        Assert.assertTrue(demos.size()==4);
        Assert.assertTrue(demos.stream()
                .allMatch(name->"chk123,ccc,rrr,ttt".contains(name.getName())));
    }

    @Test
    public void getByIdBetween() {
//        insert into demo (name,age) values('chk123',18);
//        insert into demo (name,age) values('ccc',14);
//        insert into demo (name,age) values('rrr',17);
        List<Demo> demos=demoDao.getByIdBetween(1,3);
        Assert.assertTrue(demos.size()==3);
        Assert.assertEquals("ccc",demos.get(1).getName());
    }

    @Test
    public void queryByIdLessThan() {
        List<Demo> demos=demoDao.queryByIdLessThan(3);
        Assert.assertTrue(demos.size()==2);
    }

    @Test
    public void queryByIdGreaterThan() {
        List<Demo> demos=demoDao.queryByIdGreaterThan(3);
        Assert.assertTrue(demos.size()==9);
    }

    @Test
    public void queryByIdLessThanEqual() {
        List<Demo> demos=demoDao.queryByIdLessThanEqual(3);
        Assert.assertTrue(demos.size()==3);
    }

    @Test
    public void queryByIdGreaterThanEqual() {
        List<Demo> demos=demoDao.queryByIdGreaterThanEqual(3);
        Assert.assertTrue(demos.size()==10);
    }

    @Test
    public void queryByNameLike() {
//        insert into demo (name,age) values('ccc',14);
//        insert into demo (name,age) values('xxcc',52);
        List<Demo> demos=demoDao.queryByNameLike("%cc%");
        Assert.assertEquals(2,demos.size());
    }

    @Test
    public void queryByNameStartingWith() {
        List<Demo> demos=demoDao.queryByNameStartingWith("cc");
        Assert.assertEquals(1,demos.size());
    }

    @Test
    public void queryByNameEndingWith() {
        List<Demo> demos=demoDao.queryByNameEndingWith("cc");
        Assert.assertEquals(2,demos.size());
    }

    @Test
    public void queryByIdInOrderByIdDesc() {
        List<Demo> demos=demoDao.queryByIdInOrderByIdDesc(Lists.newArrayList(1,2,3,4));
        Assert.assertEquals(4,demos.size());
        Assert.assertEquals(4,demos.get(0).getId());
    }


}