package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Properties;


@Configuration
@PropertySource("classpath:dbPostgreSQL.properties") // работаем с базами   PostgreSQL
//@PropertySource("classpath:dbMySQL.properties") // работаем с базами   MySQL
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

   @Autowired
   private Environment env;


   @Bean
   public DataSource dataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      dataSource.setInitialSize(5); // начальное количество соединений
      dataSource.setMaxTotal(10); // максимальное количество соединений
      return dataSource;
   }



   @Bean // Настройка EntityManagerFactory
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
      factoryBean.setDataSource(dataSource());
      factoryBean.setPackagesToScan("web.model"); // Сканируем пакет с сущностями

      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      vendorAdapter.setShowSql(true);
      vendorAdapter.setGenerateDdl(false); // отключаем автоматическое создание схемы, если это не нужно
      factoryBean.setJpaVendorAdapter(vendorAdapter);

      Properties jpaProperties = new Properties();
      jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
      jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      jpaProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));

      factoryBean.setJpaProperties(jpaProperties);

      return factoryBean;
   }



   @Bean  // Транзакционный менеджер
   public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
      return new JpaTransactionManager(emf);
   }
}
