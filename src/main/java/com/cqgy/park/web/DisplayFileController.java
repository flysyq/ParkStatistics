/**
 * @作者 admin
 * @时间 2016年5月27日 下午2:14:04
 * @类名 DisplayFileController.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月27日 下午2:14:04
 *   修改描述
 */
package com.cqgy.park.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.tool.CustomProps;

@Controller
public class DisplayFileController {

	@RequestMapping(value="/file/get.do",method=RequestMethod.GET)
	public void getFile(String file_name,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String file_path=CustomProps.getProp("file.temp.path")+"/"+file_name;
		
		response.setContentType("text/html;charset=UTF-8");   
        BufferedInputStream in = null;  
        BufferedOutputStream out = null; 
         
        try {  
            File f = new File(file_path);  
            response.setContentType("application/bin");  
            response.setCharacterEncoding("UTF-8");  
              response.setHeader("Content-Disposition", "attachment; filename="+file_name);  
            response.setHeader("Content-Length",String.valueOf(f.length()));  
            in = new BufferedInputStream(new FileInputStream(f));  
            out = new BufferedOutputStream(response.getOutputStream());  
            byte[] data = new byte[1024];  
            int len = 0;  
            while (-1 != (len=in.read(data, 0, data.length))) {  
                out.write(data, 0, len);  
            }  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (in != null) {  
                in.close();  
            }  
            if (out != null) {  
                out.close();  
            }  

            Files.delete(Paths.get(file_path));
        }  
	}
}
