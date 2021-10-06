package org.apache.ibatis.mybatistest.mapper;

import org.apache.ibatis.mybatistest.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

	List<Map> executeAnySelectSql(String sql);

}