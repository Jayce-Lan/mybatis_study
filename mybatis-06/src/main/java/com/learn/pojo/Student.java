package com.learn.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Getter
@Setter
public class Student {
    private int id;
    private String name;
    //学生表需要关联老师
    private Teacher teacher;
}
