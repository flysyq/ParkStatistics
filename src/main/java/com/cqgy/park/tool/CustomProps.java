/**
 * @作者 admin
 * @时间 2016年5月16日 下午2:31:06
 * @类名 CustomProps.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月16日 下午2:31:06
 *   修改描述
 */
package com.cqgy.park.tool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class CustomProps {

	
	static Properties props = null;
	static{		
		try {
			props = new Properties();
			ClassPathResource resource = new ClassPathResource("custom.properties");
            props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getProp(String key){
		return props.getProperty(key);
	}

}
