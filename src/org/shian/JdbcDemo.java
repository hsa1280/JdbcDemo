package org.shian;

import org.shian.dao.JdbcDaoImpl;
import org.shian.model.Circle;

public class JdbcDemo {

	public static void main( String[] args ) {
		
		Circle circle = new JdbcDaoImpl().getCircle(1);
		System.out.println( "Name is " + circle.getName() );

	}

}
