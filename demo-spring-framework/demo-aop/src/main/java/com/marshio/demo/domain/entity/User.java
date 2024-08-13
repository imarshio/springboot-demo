package com.marshio.demo.domain.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * mybatis 不支持@Table注解
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String email;

    private String password;

    private Date birthday;

    private String phoneNumber;

    private String address;

    private Date registrationTime;

    private Boolean isActive;

    private String gender;

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