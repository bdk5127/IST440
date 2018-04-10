package ist.barline.config;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean(name = "testBean")
	public String testBean() {
		return "bean worked";
	}
	@Bean(name = "connection")
	public Connection getConnection() {
		DataSourceConfig ds = new DataSourceConfig();
		return ds.getConnection();
	}
}
