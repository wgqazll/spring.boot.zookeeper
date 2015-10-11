package chanjet.spring.boot.zookeeper.config;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigProfile;


@Configuration
public class ZooKeeperConfig implements EnvironmentAware{
	
	private static Log logger = LogFactory.getLog(ZooKeeperConfig.class); 
	
	private RelaxedPropertyResolver propertyResolver;
	
	@Override
	public void setEnvironment(Environment environment) {
		 this.propertyResolver = new RelaxedPropertyResolver(environment, "zookeeper."); 
	}

	public ZookeeperConfigProfile configProfile(){
		ZookeeperConfigProfile zookeeperConfigProfile = new ZookeeperConfigProfile(
				propertyResolver.getProperty("connectStr"),
				propertyResolver.getProperty("rootNode"),propertyResolver.getProperty("version"));
		logger.info("init zookeeper success");
		return zookeeperConfigProfile;
	}
	
	@Bean(name="dbConfig")
	public ZookeeperConfigGroup zookeeperConfigGroup(){
		ZookeeperConfigGroup  zookeeperConfigGroup = new ZookeeperConfigGroup(configProfile(),
				propertyResolver.getProperty("db.node"));
		return zookeeperConfigGroup;
	}
}
