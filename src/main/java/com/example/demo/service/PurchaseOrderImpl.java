package com.example.demo.service;

import java.util.Arrays;
import java.util.Date;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service("purchaseOrder")
public class PurchaseOrderImpl implements IpurchaseOrder {
	@Autowired
	private JavaMailSender sender;
              
	@Value("${spring.mail.username}")
	private String fromEmail;	

	@Override
	public String purchase(String[] items, double[] prices, String[] emails) throws Exception {
		// TODO Auto-generated method stub
		double billAmt=0.0;
		for(double p:prices)
			 billAmt=billAmt+p;
		String msg=Arrays.toString(items)+"with prices"+Arrays.toString(prices)+"are purchased with BillAmount"+billAmt;
		// send mail
		String status=sendMail(msg,emails);
		return msg+"---->"+status;
	}
	private String sendMail(String msg, String[]toEmails)throws Exception{
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setFrom(fromEmail);
		helper.setCc(toEmails);
		helper.setSubject("open it to know it");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("shirt.jpg",new ClassPathResource("shirt.jpg"));
		sender.send(message);
		return"mail sent";
	}
}
		
	

