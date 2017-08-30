package com.cyj.mystock.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cyj.mystock.BeanUtils;

@Component
public class DataBaseDao {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private static DataBaseDao instance = null;

	private DataBaseDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
		this.dataSource = dataSource;
		this.jdbcTemplate = jdbcTemplate;
	}

	public static DataBaseDao getInstance() {
		synchronized (DataBaseDao.class) {
			if (instance == null) {
				instance = (DataBaseDao) BeanUtils.getBeanByName("dataBaseDao", DataBaseDao.class);
			}
		}
		return instance;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
