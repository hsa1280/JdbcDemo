package org.shian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.shian.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {

	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public Circle getCircle( int id ) {
		
		Connection conn = null;
		
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?");
			ps.setInt(1, id );
			
			Circle circle = null;
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				circle = new Circle( id, rs.getString("name"));
			}
			rs.close();
			ps.close();
			
			return circle;
		}
		catch( Exception e) {
			throw new RuntimeException(e);
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
	}
	
	public int getCircleCount() {
		
		String sql = "SELECT COUNT(*) FROM circle";
		jdbcTemplate.setDataSource(getDataSource());
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return count;
	}
	
	public String getCircleName( int circleId ) {
		
		String sql = "SELECT NAME FROM CIRCLE WHERE ID = ?";
		return jdbcTemplate.queryForObject( sql, new Object[] { circleId }, String.class );
	}
	
	public Circle getCircleForId( int circleId ) {
		
		String sql = "SELECT * FROM CIRCLE WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { circleId }, new CircleMapper());
	}
	
	public List<Circle> getAllCircles() {
		
		String sql = "SELECT * FROM CIRCLE";
		return jdbcTemplate.query(sql, new CircleMapper());
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final class CircleMapper implements RowMapper<Circle> {

		@Override
		public Circle mapRow( ResultSet resultSet, int rowNum ) throws SQLException {
			
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("ID"));
			circle.setName(resultSet.getString("NAME"));
			return circle;
		}
		
		
	}
	
}
