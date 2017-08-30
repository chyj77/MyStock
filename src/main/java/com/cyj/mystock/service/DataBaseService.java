package com.cyj.mystock.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cyj.mystock.dao.DataBaseDao;

@Component
public class DataBaseService {

	private static DataBaseDao dao;

	private static DataBaseService service = null;

	public static DataBaseService getgetInstance() {
		synchronized (DataBaseService.class) {
			if (service == null) {
				service = new DataBaseService();
				dao = DataBaseDao.getInstance();
			}
		}
		return service;
	}

	public static List<Map<String, Object>> queryForList(String sql, Object... objects) {
		return dao.getJdbcTemplate().queryForList(sql, objects);
	}

	public static List<Map<String, Object>> queryForList(String sql) {
		return dao.getJdbcTemplate().queryForList(sql);
	}

	public static void execute(String sql) {
		dao.getJdbcTemplate().execute(sql);
	}

	public static int update(String sql) {
		return dao.getJdbcTemplate().update(sql);
	}

	public static int update(String sql, Object... objects) {
		return dao.getJdbcTemplate().update(sql, objects);
	}

	public DataBaseDao getDao() {
		return dao;
	}

	public void setDao(DataBaseDao dao) {
		this.dao = dao;
	}

	public static JdbcTemplate getJdbcTemplate() {
		return dao.getJdbcTemplate();
	}

	public static void main(String[] args) {

		/*
		 * String sql = "select * from v_cdsp2r"; ApplicationContext
		 * applicationContext = new ClassPathXmlApplicationContext(
		 * "classpath:context/applicationContext.xml"); DataBaseService sqlQuery
		 * = (DataBaseService) applicationContext.getBean("dataBaseService");
		 * List<Map<String, Object>> list =
		 * sqlQuery.getJdbcTemplate().queryForList(sql); if(list!=null &&
		 * list.size()>0){ System.out.println("成功"); }else{
		 * System.out.println("失败"); }
		 */
	}

}
