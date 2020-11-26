package com.learn.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data   //使用lombok快速构造了一个有参方法
@Getter
@Setter
@ToString
public class Teacher {
    private int id;
    private String name;

    public Teacher() {
    }
}
