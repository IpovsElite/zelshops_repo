package com.ipovselite;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class UserDAOImpl implements UserDAO {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	public void setDataSource(DataSource src) {
		dataSource = src;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User isValid(String username,String password) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM db.user WHERE username='"+username+"' AND password='"+password+"';";
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {

			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				User u=new User();
				if (rs.next()) {
					u.setId(rs.getInt("id"));
					u.setUsername(rs.getString("username"));					
					u.setPassword(rs.getString("password"));
					Access a=null;
					switch (rs.getInt("access")) {
					case 0:a=Access.Admin;break;
					case 1:a=Access.Moderator;break;
					}
					u.setAccess(a);
					return u;
				}
				return null;
			}
			
		});
	}

	public boolean changePassword(String op, String np) {
		// TODO Auto-generated method stub
		return false;
	}

}
