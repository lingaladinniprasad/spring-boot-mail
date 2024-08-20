package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.service.IpurchaseOrder;

//import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMail1Application {

	public static void main(String[] args) {
		//get IOC container
		
				ApplicationContext ctx=SpringApplication.run(SpringBootMail1Application.class, args);
				//get Service class object ref
				IpurchaseOrder order=ctx.getBean("purchaseOrder",IpurchaseOrder.class);
				//invoke method
				try {
					String msg=order.purchase(new String[] {"shirt","trouser","watch"},
							   new double[] {9000,6000,7000},
							   new String[] {"lingaladinni.prasad1997@gmail.com","nagasekharreddy66@gmail.com","bharshad123@gmail.com",
									   "maheshveeranki123@gmail.com" , "nannasanigurukrishna@gmail.com"}
							   
							);
					System.out.println(msg);
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				//close container
				((ConfigurableApplicationContext)ctx).close();
	}

}
