package hpst.hibernate;


import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.DataSourceQueryCountListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@SpringBootApplication
public class HibernateAdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateAdvApplication.class, args);
	}

	

}
