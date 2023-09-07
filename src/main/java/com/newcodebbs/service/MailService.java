package com.newcodebbs.service;

public interface MailService {
    /**
     * 发送HTML格式的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    int sendHtmlMail(String to, String subject, String content);
}
