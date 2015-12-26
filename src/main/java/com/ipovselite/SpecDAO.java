package com.ipovselite;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class SpecDAO implements ISpecService {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	public void setDataSource(DataSource src) {
		dataSource = src;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<String> getSpecList() {
		// TODO Auto-generated method stub
		return null;
	}

}
