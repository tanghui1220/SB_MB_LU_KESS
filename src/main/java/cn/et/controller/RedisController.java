package cn.et.controller;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

	@Autowired
	StringRedisTemplate template;
	
	@GetMapping("/testRedis")
	public String testRedis() {
		
		//BoundValueOps("建").set("值") 字符串类型
		//template.boundSetOps(key).add() set 类型 
		//template.boundHashOps("").put("属性","值")
		//template.boundListOps(key).add()
		//template.boundZSetOps(key)
		//template.boundValueOps("user:1").set("zs")0
		BoundValueOperations<String, String> boundValueOps = template.boundValueOps("user:2");
		System.out.println(boundValueOps);
		System.out.println(boundValueOps.get());
		return null;
	}
}
