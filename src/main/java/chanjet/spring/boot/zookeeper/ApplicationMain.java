package chanjet.spring.boot.zookeeper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;


@SpringBootApplication
public class ApplicationMain {
	
	private static Log logger = LogFactory.getLog(ApplicationMain.class); 
	
	private static ZookeeperConfigGroup dbConfig;
	
    public static void main( String[] args ){
    	SpringApplication.run(ApplicationMain.class, args);
    	logger.info("test zookeeper the db url is " + dbConfig.get("url"));
    }

	public ZookeeperConfigGroup getDbConfig() {
		return dbConfig;
	}
	
	@Autowired
	public void setZooConfig(ZookeeperConfigGroup dbConfig) {
		ApplicationMain.dbConfig = dbConfig;
	}
}
