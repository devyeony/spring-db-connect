package kr.com.inspect.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * PostgreSQL JDBC 설정(xml 대체)
 * @author Yeonhee Kim
 * @version 1.0
 *
 */

@Configuration
@PropertySource(value = "classpath:properties/db.properties")
@EnableTransactionManagement
public class PostgreConfig {
	
	/*
	 * PostgreSQL의 JDBC 드라이브 이름
	 */
	@Value("${jdbc.driverClassName}") 
	private String driverClassName;
	
	/*
	 * PostgreSQL의 JDBC url
	 */
	@Value("${jdbc.url}")
	private String url;
	
	/*
	 * PostgreSQL의 JDBC 유저 이름
	 */
	@Value("${jdbc.username}")
	private String userName;
	
	/*
	 * PostgreSQL의 JDBC 유저 비밀번호
	 */
	@Value("${jdbc.password}")
	private String password;

	/**
	 * 자원을 얻어오기 위한 ApplicationContext
	 */
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * JDBC 입력 값
	 * @return PostgreSQL의 JDBC 정보 반환
	 */
	@Bean("dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return (DataSource)dataSource;
	}
	
	/**
	 * SqlSession을 만들기 위해 SQL Mapper xml 경로가 지정된 SqlSessionFactory 리턴
	 * @return SqlSessionFacotry를 리턴
	 * @throws Exception 예외처리
	 */
	@Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
       SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
       sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
       sqlSessionFactoryBean.setDataSource(dataSource());
       return sqlSessionFactoryBean.getObject();
    }
	
    /**
     * SqlSession을 반환함 
     * @return SqlSession을 구현한 SqlSessionTemplate 반환
     * @throws Exception 예외처리
     */
    @Bean
    public SqlSession sqlSession() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }
}
