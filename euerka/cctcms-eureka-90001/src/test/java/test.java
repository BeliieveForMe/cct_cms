/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/12/22 3:31
 * @modified By :  guodf
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

/**
 * @ClassName test
 * @Description TOOD
 * @Author Administrator
 * @Date 2020/12/223:31
 * @Version 1.0
 **/
public class test {
    @Bean
    public String jedisPool(@Value("${redis.host}") String host,
                                         @Value("${redis.port}") int port) {
        return new String(host+port);
    }

    @Bean(initMethod="init",destroyMethod="close")
    @ConfigurationProperties(prefix="dataSource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        return reg;
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    @Bean
    public FilterRegistrationBean appFilter() {
        FilterRegistrationBean reg = new FilterRegistrationBean();
        reg.setFilter(new LoggingFilter());
        reg.addUrlPatterns("/api/*");
        return reg;
    }
    @Test
    public static void main(String[] args) {
        System.out.println(new jedisPool());
    }
}
