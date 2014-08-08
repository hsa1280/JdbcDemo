package org.shian;

import org.shian.dao.JdbcDaoImpl;
import org.shian.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main( String[] args ) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("JdbcSpring.xml");
		
		JdbcDaoImpl jdbcDaoImpl = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		
		Circle circle = jdbcDaoImpl.getCircle(1);
		System.out.println( "Name is " + circle.getName() );

	}

}
