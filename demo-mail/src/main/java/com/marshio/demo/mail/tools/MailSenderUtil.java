package com.marshio.demo.mail.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author marshio
 * @desc 邮件发送工具
 * @create 2023-03-28 16:03
 */
@Slf4j
@Component
public class MailSenderUtil {

    private final JavaMailSender sender;

    private final ThreadPoolExecutor threadPool;

    @Value("${spring.mail.properties.mail.from}")
    private static String form;

    public MailSenderUtil(JavaMailSender sender, ThreadPoolUtil threadPool) {
        this.sender = sender;
        this.threadPool = ThreadPoolUtil.getInstance().getThreadPool();
    }

    // TODO public --> private
    private void doSend(String[] to, String subject, String content, Boolean html, String filePath) {
        threadPool.execute(()-> {
            try {
                MimeMessage mimeMessage = sender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                // 发送邮箱，需要配置校验，尽量跟application中的邮箱配置一致
                helper.setFrom(form);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(content, html);
                sender.send(mimeMessage);
            } catch (Exception e) {
                log.error("发送邮件异常，{}." ,e.getMessage(), e);
            }
        });
    }

    private void doSend(String[] to, String subject, String plainText, String htmlText, String filePath) {
        threadPool.execute(()-> {
            try {
                MimeMessage mimeMessage = sender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                // 发送邮箱，需要配置校验，尽量跟application中的邮箱配置一致
                helper.setFrom(form);
                helper.setTo(to);
                helper.setSubject(subject);
                // 设置两种内容
                helper.setText(plainText, htmlText);
                sender.send(mimeMessage);
            } catch (Exception e) {
                log.error("发送邮件异常，{}." ,e.getMessage(), e);
            }
        });
    }

    /**
     * send email to single address。
     *
     * @param to       Email address of accept user
     * @param subject  Email subject
     * @param content  Email content
     * @param html     Email format
     * @param filepath Email filepath
     */
    public void sendEmailWithSingleAddressS(String to, String subject, String content, Boolean html, String filepath) {
        doSend(new String[]{to}, subject, content, html, filepath);
    }

    /**
     * send email to multiple address and the email content is plaintext or html
     *
     * @param to       Email addresses of accept user
     * @param subject  Email subject
     * @param content  Email content
     * @param html     Email format
     * @param filepath Email filepath,attachment
     */
    public void sendEmailWithMultipleAddress(String[] to, String subject, String content, Boolean html, String filepath) {
        doSend(to, subject, content, html, filepath);
    }

    /**
     * send email to single address and the email content is plaintext and html,
     * so the email-receiver can choose which format of the content to read.
     *
     * @param to        Email addresses of accept user
     * @param subject   Email subject
     * @param plainText Email plainText
     * @param htmlText  Email htmlText
     * @param filepath  Email filepath,attachment
     */
    public void sendEmailInPlainTextAndHtmlWithSingleAddress(String to, String subject, String plainText, String htmlText, String filepath) {
        doSend(new String[]{to}, subject, plainText, htmlText, filepath);
    }

    /**
     * send email to multiple address and the email content is plaintext or html
     *
     * @param to        Email addresses of accept user
     * @param subject   Email subject
     * @param plainText Email plainText
     * @param htmlText  Email htmlText
     * @param filepath  Email filepath,attachment
     */
    public void sendEmailInPlainTextAndHtmlWithMultipleAddress(String[] to, String subject, String plainText, String htmlText, String filepath) {
        doSend(to, subject, plainText, htmlText, filepath);
    }

    // TODO 补充可鉴权的formMail 发送方式
}
