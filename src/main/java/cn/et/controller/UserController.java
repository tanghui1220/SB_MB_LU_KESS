package cn.et.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.et.entity.ResponseEnt;
import cn.et.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	/***
	 * 用于发送给jsp页面,jsp页面只接收这样的数据格式
	 * @param page
	 * @param limit
	 * @param userName
	 * @param gender
	 * @return
	 */
	@GetMapping("/user/userList")
	public ResponseEnt userList(Integer page,Integer limit,String userName,String gender) {
		
		Map map = new HashMap<>();
		map.put("userName", userName);
		map.put("gender", gender);
		ResponseEnt re = new ResponseEnt();
		List<Map> selectAllUser = userService.selectAllUser(page, limit, map);
		re.setData(selectAllUser);
		re.setCount(userService.selectAllUserCount(map));
		return re;
	}
	
	@GetMapping("/user/deleteUser")
	public String deleteUser(String id) {
		try {
			userService.deleteUser(id);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			return "0";
		}
	}
	
	@GetMapping("/user/addUser")
	public String deleteUser(String userName,String age,String gender) {
		
		try {
			userService.saveUser(userName, age, gender);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			return "0";
		}
	}
	
	@GetMapping("/user/detailUser")
	public Map detailUser(String id) {
		return userService.detailUser(id);
		
	}
}
