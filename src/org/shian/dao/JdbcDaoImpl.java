package org.shian.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.shian.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {


	private DataSource dataSource;
	
	private int i;
	
	private int j;
	
	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
/*	public Circle getCircle( int id ) {
		
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
		
	}*/
	
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
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
//	public void insert( Circle circle ) {
//		
//		String sql = "INSERT INTO CIRCLE (ID, NAME) VALUES (?, ?)";
//		jdbcTemplate.update(sql, new Object[] {circle.getId(), circle.getName() });
//	}
	
	public void insert( Circle circle ) {
		
		String sql = "INSERT INTO CIRCLE (ID, NAME) VALUES(:circleId, :name)";
//		SqlParameterSource namedParameter = new MapSqlParameterSource("id", circle.getId())
//												.addValue("name", circle.getName());
		Map namedParameter = new HashMap();
		/*The key name in the map needs to match with the parameter in the INSERT query*/
		namedParameter.put("circleId", circle.getId());
		namedParameter.put("name", circle.getName());
		namedParameterJdbcTemplate.update(sql, namedParameter);
		
	}
	
	public void createTriangleTable() {
		
		String sql = "CREATE TABLE TRIANGLE (ID INTEGER, NAME VARCHAR(50))";
		jdbcTemplate.execute(sql);
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
