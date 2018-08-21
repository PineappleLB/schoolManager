package club.pinea.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import club.pinea.school.common.BaseController;
import club.pinea.school.config.ConstShiro;
import club.pinea.school.shiro.annotation.Permission;
import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
	
	@Autowired
	JedisCluster jedisCluster;
	
	@Permission(ConstShiro.QUERY_STRING)
	@ResponseBody
	@RequestMapping("/queryString/{string}")
	public String queryString(@PathVariable("string")String string) {
		jedisCluster.set("string", string);
		return jedisCluster.get("string");
	}

	@ResponseBody
	@RequestMapping("/testAop")
	public String test() {
		return "hello";
	}
	
}
