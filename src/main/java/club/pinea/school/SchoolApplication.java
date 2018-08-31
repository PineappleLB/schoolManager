package club.pinea.school;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan("club.pinea.school.mapper")
public class SchoolApplication {
//	@Value("${spring.redis.cluster.nodes}")
//	private String clusterNodes;
//	
//	@Bean
//	public JedisCluster jedisCluster() {
//		// 截取集群节点
//		String[] cluster = clusterNodes.split(",");
//		// 创建set集合
//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		// 循环数组把集群节点添加到set集合中
//		for (String node : cluster) {
//			String[] host = node.split(":");
//			// 添加集群节点
//			nodes.add(new HostAndPort(host[0], Integer.parseInt(host[1])));
//		}
//		JedisCluster jc = new JedisCluster(nodes);
//		return jc;
//	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}
}
