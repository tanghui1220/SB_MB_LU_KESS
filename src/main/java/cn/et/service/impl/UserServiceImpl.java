package cn.et.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cn.et.mapper.UserMapper;
import cn.et.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	  private UserMapper userMapper;
	@Autowired
	private StringRedisTemplate template;
	 
	@Override
	public List<Map> selectAllUser(Integer page,Integer limit,Map map){
		  Object userName=map.get("userName");
		  if(userName==null) {
			  userName="";
			  map.put("userName", userName);
		  }
		  int startIndex=(page-1)*limit;
		  return userMapper.selectAllUser(startIndex, limit,map);
	  }
	  
	@SuppressWarnings("unchecked")
	@Override
	public int selectAllUserCount(Map map) {
		Object userName=map.get("userName");
		  if(userName==null) {
			  userName="";
			  map.put("userName", userName);
		  }
		  return userMapper.selectAllUserCount(map);
	  }
	@Override
	public void deleteUser(String id) {
		userMapper.deleteUser(id);
	}
	@Override
	public void saveUser(String userName, String age, String gender) {
		userMapper.saveUser(userName,age,gender);
	}

	@Override
	public Map detailUser(String id) {
		// 判断redis是否存在该id对应的用户数据
		String userId = "t_user:"+id;
		Set<String> keys = template.keys(userId);
		//缓存存在数据
		if(keys.size()>0) {
			return template.boundHashOps(userId).entries();
			// 不存咋数据就到数据库查询
		}else {
			//查询数据
			Map map = userMapper.selectUserById(id);
			// 放入缓存
			template.boundHashOps(userId).putAll(map);
			return map;
		}
	}
}
