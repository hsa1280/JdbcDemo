package org.shian;

import org.shian.dao.HibernateDaoImpl;
import org.shian.dao.SimpleJdbcDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main( String[] args ) {
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("JdbcSpring.xml");
		
//		JdbcDaoImpl jdbcDaoImpl = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
//		jdbcDaoImpl.insert(new Circle(5, "Five Circle"));
//		List<Circle> circleList = jdbcDaoImpl.getAllCircles();
//		for( Circle circle : circleList ) {
//			System.out.println( circle.getId() + ", " + circle.getName() );
//		}
		
		HibernateDaoImpl dao = ctx.getBean("hibernateDaoImpl", HibernateDaoImpl.class);
		System.out.println(dao.getCircleCount());


	}

}
