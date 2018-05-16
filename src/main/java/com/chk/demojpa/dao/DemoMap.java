package com.chk.demojpa.dao;

import org.springframework.beans.factory.annotation.Value;

public interface DemoMap {
    @Value("#{target.name+' '+target.age}")
    String getNameAndAge();
    String getName();
    String getAge();
}
