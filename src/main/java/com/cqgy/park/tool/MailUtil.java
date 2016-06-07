/**
 * @作者 石永强
 * @时间 2016年6月7日 上午9:56:50
 * @类名 MailUtil.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年6月7日 上午9:56:50
 *   修改描述
 */
package com.cqgy.park.tool;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {

	/**
	 * @param args
	 * @throws MessagingException 
	 */
	public static void main(String[] args) throws MessagingException {
		mailFile();
	}

	public static void mailSimple(){
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设定mail server
		senderImpl.setHost("smtp.163.com");

		// 建立邮件消息
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 设置收件人，寄件人 用数组发送多个邮件
		// String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
		// mailMessage.setTo(array);
		mailMessage.setTo("flysyq@163.com");
		mailMessage.setFrom("flysyq@163.com ");
		mailMessage.setSubject(" 测试简单文本邮件发送！ ");
		mailMessage.setText(" 测试我的简单邮件发送机制！！ ");

		senderImpl.setUsername("flysyq@163.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("stone123"); // 根据自己的情况, 设置password

		Properties prop = new Properties();
		prop.put(" mail.smtp.auth ", " true "); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put(" mail.smtp.timeout ", " 25000 ");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);

		System.out.println(" 邮件发送成功.. ");
	}
	
	public static void mailHtml() throws MessagingException{
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost("smtp.163.com");

		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

		// 设置收件人，寄件人
		messageHelper.setTo("flysyq@163.com");
		messageHelper.setFrom("flysyq@163.com");
		messageHelper.setSubject("测试HTML邮件！");
		// true 表示启动HTML格式的邮件
		messageHelper
				.setText(
						"<html><head></head><body><h1>hello!!spring html Mail</h1></body></html>",
						true);

		senderImpl.setUsername("flysyq@163.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("stone123"); // 根据自己的情况, 设置password
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);

		System.out.println("邮件发送成功..");
	}
	
	public static void mailImage() throws MessagingException{
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost("smtp.163.com");

		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
		// multipart模式
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true);

		// 设置收件人，寄件人
		messageHelper.setTo("flysyq@163.com");
		messageHelper.setFrom("flysyq@163.com");
		messageHelper.setSubject("测试邮件中嵌套图片!！");
		// true 表示启动HTML格式的邮件
		messageHelper.setText(
				"<html><head></head><body><h1>hello!!spring image html mail</h1>"
						+ "<img src=\"cid:aaa\"/></body></html>", true);

		FileSystemResource img = new FileSystemResource(new File("D:/upload/2016-05-17/80e87ede-48c4-453e-a289-9ae58106d8b7.jpg"));

		messageHelper.addInline("aaa", img);

		senderImpl.setUsername("flysyq@163.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("stone123"); // 根据自己的情况, 设置password
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		senderImpl.setJavaMailProperties(prop);

		// 发送邮件
		senderImpl.send(mailMessage);

		System.out.println("邮件发送成功..");
	}
	
	public static void mailFile() throws MessagingException{
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost("smtp.163.com");
		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
		// multipart模式 为true时发送附件 可以设置html格式
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true, "utf-8");

		// 设置收件人，寄件人
		messageHelper.setTo("flysyq@163.com");
		messageHelper.setFrom("flysyq@163.com");
		messageHelper.setSubject("测试邮件中上传附件!！");
		// true 表示启动HTML格式的邮件
		messageHelper.setText(
				"<html><head></head><body><h1>你好：附件中有学习资料！</h1></body></html>",
				true);

		FileSystemResource file = new FileSystemResource(
				new File("F:/temp/duty_shift/0001_10003_2016-05-01_8b7a0ef9-ee64-49fc-ae83-52f15d2a6e80.pdf"));
		FileSystemResource file2 = new FileSystemResource(
				new File("F:/temp/duty_shift/0002_10002_2016-05-01_4802012c-f4d2-4be5-abd6-175c053740a1.pdf"));
		// 这里的方法调用和插入图片是不同的。
		messageHelper.addAttachment("交接班报表1.pdf", file);
		messageHelper.addAttachment("交接班报表2.pdf", file2);
		senderImpl.setUsername("flysyq@163.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("stone123"); // 根据自己的情况, 设置password
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);

		System.out.println("邮件发送成功..");
	}

	public static void mailHtmlTo(String userMail, String title,String html) throws MessagingException {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost("smtp.163.com");

		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
		// multipart模式
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true,"utf-8");

		// 设置收件人，寄件人
		messageHelper.setTo(userMail);
		messageHelper.setFrom("flysyq@163.com");
		messageHelper.setSubject(title);
		// true 表示启动HTML格式的邮件
		messageHelper.setText(html, true);

		
		senderImpl.setUsername("flysyq@163.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("stone123"); // 根据自己的情况, 设置password
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		senderImpl.setJavaMailProperties(prop);

		// 发送邮件
		senderImpl.send(mailMessage);

		System.out.println("邮件发送成功..");
		
	}
}
