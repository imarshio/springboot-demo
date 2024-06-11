package com.marshio.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.marshio.demo.domain.enums.Gender;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * mybatis 不支持@Table注解
 */
@Data
@TableName(value = "user", autoResultMap = true)
public class User {

    @TableId
    private Integer id;

    private String username;

    private String email;

    private String password;

    private Date birthday;

    private String phoneNumber;

    private String address;

    private Date registrationTime;

    private Boolean isActive;

    private Gender gender;

    /**
     * 必须开启映射注解
     *
     * <p> @TableName(autoResultMap = true) <p>
     * 选择对应的 JSON 处理器，并确保存在对应的 JSON 解析依赖包
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> role;

    private String intro;

    // public Integer getId() {
    //     return id;
    // }
    //
    // public void setId(Integer id) {
    //     this.id = id;
    // }
    //
    // public String getUsername() {
    //     return username;
    // }
    //
    // public void setUsername(String username) {
    //     this.username = username;
    // }
    //
    // public String getEmail() {
    //     return email;
    // }
    //
    // public void setEmail(String email) {
    //     this.email = email;
    // }
    //
    // public String getPassword() {
    //     return password;
    // }
    //
    // public void setPassword(String password) {
    //     this.password = password;
    // }
    //
    // public Date getBirthday() {
    //     return birthday;
    // }
    //
    // public void setBirthday(Date birthday) {
    //     this.birthday = birthday;
    // }
    //
    // public String getPhoneNumber() {
    //     return phoneNumber;
    // }
    //
    // public void setPhoneNumber(String phoneNumber) {
    //     this.phoneNumber = phoneNumber;
    // }
    //
    // public String getAddress() {
    //     return address;
    // }
    //
    // public void setAddress(String address) {
    //     this.address = address;
    // }
    //
    // public Date getRegistrationTime() {
    //     return registrationTime;
    // }
    //
    // public void setRegistrationTime(Date registrationTime) {
    //     this.registrationTime = registrationTime;
    // }
    //
    // public Boolean getIsActive() {
    //     return isActive;
    // }
    //
    // public void setIsActive(Boolean isActive) {
    //     this.isActive = isActive;
    // }
    //
    // public String getGender() {
    //     return gender;
    // }
    //
    // public void setGender(String gender) {
    //     this.gender = gender;
    // }
    //
    // public List getRole() {
    //     return role;
    // }
    //
    // public void setRole(List role) {
    //     this.role = role;
    // }
    //
    // public String getIntro() {
    //     return intro;
    // }
    //
    // public void setIntro(String intro) {
    //     this.intro = intro;
    // }
}