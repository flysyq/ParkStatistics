/**
 * @作者 admin
 * @时间 2016年4月20日 下午1:54:56
 * @类名 WebConfig.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 下午1:54:56
 *   修改描述
 */
package com.cqgy.park.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cqgy.park.filter.TestFilter;


@Configuration
public class WebConfig {
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean(TestFilter tFilter){
		FilterRegistrationBean  filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(tFilter);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("*.do");
		return filterRegistrationBean;
		
	}
}
