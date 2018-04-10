package com.nice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by nice on 2018/3/30.
 */
@Getter
@Setter
@ToString
public class Person implements Serializable{
    private String name;
    private String age;
}
