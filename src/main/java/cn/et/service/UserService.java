package cn.et.service;

import java.util.List;
import java.util.Map;

public interface UserService {

	List<Map> selectAllUser(Integer page,Integer limit,Map map);
	
	int selectAllUserCount(Map map);
	
	void deleteUser(String id);
	
	void saveUser(String userName,String age,String gender);
	
	Map detailUser(String id);
}
