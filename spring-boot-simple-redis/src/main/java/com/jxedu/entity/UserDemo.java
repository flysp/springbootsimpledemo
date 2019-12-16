package com.jxedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author libin
 * @version 1.0
 * @description
 * @data 2019/12/16 10:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDemo implements Serializable {
    private static final long serialVersionUID = 1L;
     private Integer id;
     private String name;
}
