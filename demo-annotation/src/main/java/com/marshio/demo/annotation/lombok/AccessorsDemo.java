package com.marshio.demo.annotation.lombok;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author marshio
 * @desc 注解Accessors演示
 * @create 2023-06-09 10:14
 */
@Data
@Accessors(chain = true, fluent = true)
public class AccessorsDemo {

    private Long id;
    private String name;
    private String desc;

    // Accessors -- chain表示链式生成，类似builder，如上参数的set方法就会自动生成为下面这样

    // public DemoEntity setId(final Long id) {
    //     this.id = id;
    //     return this;
    // }
    //
    // public DemoEntity setName(final String name) {
    //     this.name = name;
    //     return this;
    // }
    //
    // public DemoEntity setDesc(final String desc) {
    //     this.desc = desc;
    //     return this;
    // }

    // Accessors -- fluent表示流式生成，更接近builder，如上参数的set方法就会自动生成为下面这样，get/set方法，不在以get/set开头，而是直接已参数名称为方法名称

    // public Long id() {
    //     return this.id;
    // }
    //
    // public String name() {
    //     return this.name;
    // }
    //
    // public String desc() {
    //     return this.desc;
    // }
    //
    // public DemoEntity id(final Long id) {
    //     this.id = id;
    //     return this;
    // }
    //
    // public DemoEntity name(final String name) {
    //     this.name = name;
    //     return this;
    // }
    //
    // public DemoEntity desc(final String desc) {
    //     this.desc = desc;
    //     return this;
    // }


}
