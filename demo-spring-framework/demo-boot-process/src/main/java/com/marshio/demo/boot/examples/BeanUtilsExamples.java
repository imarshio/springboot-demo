package com.marshio.demo.boot.examples;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

import static org.springframework.boot.SpringApplication.DEFAULT_SERVLET_WEB_CONTEXT_CLASS;

/**
 * @author marshio
 * @desc ...
 * @create 2024/2/5 18:04
 */
public class BeanUtilsExamples {

    public static void main(String[] args) {
        System.out.println(BeanUtils.instantiateClass(String.class).isEmpty());
        System.out.println(BeanUtils.instantiateClass(ArrayList.class).isEmpty());
        try {
            System.out.println(BeanUtils.instantiateClass(Class.forName(DEFAULT_SERVLET_WEB_CONTEXT_CLASS)).getClass());
        } catch (ClassNotFoundException e) {
            System.out.println(" class not found");;
        }
    }
}
