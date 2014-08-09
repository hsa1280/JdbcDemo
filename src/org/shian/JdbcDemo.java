package org.shian;

import java.util.List;

import org.shian.dao.JdbcDaoImpl;
import org.shian.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main( String[] args ) {
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("JdbcSpring.xml");
		
		JdbcDaoImpl jdbcDaoImpl = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		
//		Circle circle = jdbcDaoImpl.getCircle(1);
//		System.out.println( "Name is " + circle.getName() );
		
		//jdbcDaoImpl.insert(new Circle(2, "Second Circle"));
//		List<Circle> circleList = jdbcDaoImpl.getAllCircles();
//		for( Circle circle : circleList ) {
//			System.out.println( circle.getId() + ", " + circle.getName() );
//		}
		jdbcDaoImpl.createTriangleTable();

	}

}
