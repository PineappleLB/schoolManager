package club.pinea.school.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisClusterManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import club.pinea.school.shiro.ShiroDBRealm;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class ShiroConfig {
	
	@Value("${spring.redis.cluster.nodes}")
	private String clusterNodes;

	@Bean
	public ShiroDBRealm shiroDbRealm() {
		return new ShiroDBRealm();
	}
	

	@Bean
	public JedisCluster jedisCluster() {
		// 截取集群节点
		String[] cluster = clusterNodes.split(",");
		// 创建set集合
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		// 循环数组把集群节点添加到set集合中
		for (String node : cluster) {
			String[] host = node.split(":");
			// 添加集群节点
			nodes.add(new HostAndPort(host[0], Integer.parseInt(host[1])));
		}
		JedisCluster jc = new JedisCluster(nodes);
		return jc;
	}
	
	/**
	 * 权限管理，配置主要是Realm的管理认证
	 */
	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroDbRealm());
		// 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(SessionManager());
		return securityManager;
	}
	
	/**
     * shiro session的管理
     */
    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
	
	/**
     * 配置shiro redisManager
     * 
     * @return
     */
    public IRedisManager redisManager() {
    	RedisClusterManager redisManager = new RedisClusterManager();
        redisManager.setHost(clusterNodes);
        // redisManager.setTimeout(timeout);
        // redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
    
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
    
    
	/**
	 * Filter工厂，设置对应的过滤条件和跳转条件
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> map = new HashMap<>();
		map.put("/test/**", "anon");//测试不需要认证
		map.put("/login/**", "anon");//登录接口不需要认证
		map.put("/noUser", "anon");//登录接口不需要认证
		// 对所有用户认证
		map.put("/**", "authc");
		// 登录
		shiroFilterFactoryBean.setLoginUrl("/noUser");
		// 首页
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 没有权限跳转的页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
		// 错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	/**
	 * 加入注解的使用，不加入这个注解不生效
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 在方法中 注入 securityManager,进行代理控制
	 * @return
	 */
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		methodInvokingFactoryBean.setArguments(securityManager());
		return methodInvokingFactoryBean;
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

//	/**
//	 * 保证实现了Shiro内部lifecycle函数的bean执行
//	 * @return
//	 */
//	@Bean
//	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//	}

	/**
	 * 启用shrio授权注解拦截方式
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

}
