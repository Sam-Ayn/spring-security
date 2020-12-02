package ru.springcourse.lessons.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.springcourse.lessons")
@PropertySource("classpath:persistence-mysql.properties")
public class SpringConfig {

    @Autowired
    private Environment environment;

    private static final Logger logger = LoggerFactory.getLogger(SpringConfig.class);

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public DataSource securityDataSource(){

        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
        try {
            securityDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        logger.info(">>> jdbc.url=" + environment.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user=" + environment.getProperty("jdbc.user"));

        securityDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        securityDataSource.setUser(environment.getProperty("jdbc.user"));
        securityDataSource.setPassword(environment.getProperty("jdbc.password"));

        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    private int getIntProperty(String propertyName){
        String propertyValue = environment.getProperty(propertyName);
        return Integer.parseInt(propertyValue);
    }

}
