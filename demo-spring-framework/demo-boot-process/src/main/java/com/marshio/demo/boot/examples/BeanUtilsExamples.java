package com.marshio.demo.boot.examples;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/2/5 18:04
 */
public class BeanUtilsExamples {

    public static void main(String[] args) {
        System.out.println(BeanUtils.instantiateClass(String.class).isEmpty());
        System.out.println(BeanUtils.instantiateClass(ArrayList.class).isEmpty());
    }
}
