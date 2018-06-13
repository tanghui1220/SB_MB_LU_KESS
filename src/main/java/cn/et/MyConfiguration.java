package cn.et;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;

/***
 * <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
 * </bean>
 */

@Configuration
public class MyConfiguration {

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Bean
	public DataSource dataSource() {
		DruidDataSource dds=new DruidDataSource();
		dds.setDriverClassName(driverClassName);
		dds.setUrl(url);
		dds.setUsername(userName);
		dds.setPassword(password);
		return dds;
	}
	@Bean
	public ServletRegistrationBean DruidStatView() {
		ServletRegistrationBean sr=new ServletRegistrationBean();
		sr.setServlet(new StatViewServlet());
		List<String> list=new ArrayList<String>();
		list.add("/druid/*");
		sr.setUrlMappings(list);
		return sr;
	}
}
