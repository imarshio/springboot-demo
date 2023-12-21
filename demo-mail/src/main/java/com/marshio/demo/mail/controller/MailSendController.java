package com.marshio.demo.mail.controller;

import com.marshio.demo.mail.tools.MailSenderUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marshio
 * @desc
 * @create 2023-03-28 16:10
 */
@RestController
@RequestMapping("/demo")
public class MailSendController {

    private final MailSenderUtil mailSenderUtil;

    public MailSendController(MailSenderUtil mailSenderUtil) {
        this.mailSenderUtil = mailSenderUtil;
    }

    @GetMapping("/send")
    public String sendMail() {
        mailSenderUtil.sendEmailWithSingleAddressS("wanzhaokun@shenqingtech.com","测试", "content测试" , true, "");
        return "send success";
    }
}
