package com.cqgy.park.tool;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class Stool {

	//从request中读取json数据
	public static String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }
	
	//根据键获取json值
	public static String getJsonValue(String json,String keyString) throws JsonProcessingException, IOException{
		ObjectMapper m = new ObjectMapper();
		JsonNode jnode = m.readTree(json);
		String[] keys = keyString.split("\\.");
		
		int n = keys.length;
		for(int i=0;i<n-1;i++){
			jnode = jnode.path(keys[i]);
		}
		
		return jnode.path(keys[n-1]).textValue();
	}
	//模拟post提交json
	public static String postJson(String urlString,String json){
		StringBuffer sb = new StringBuffer("");
		try {
			// 创建连接
			URL url = new URL(urlString);
			System.out.println(json);
			
			System.out.println(Integer.toString(json.getBytes().length));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			connection.setRequestProperty("Content-Length", "" + Integer.toString(json.getBytes().length));
			connection.setRequestProperty("Content-Language", "utf-8");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(json.getBytes("utf-8"));
			out.flush();
			out.close();

			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines;
	
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			System.out.println(sb);
			reader.close();
			// 断开连接
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	//获取uuid()
	public static String uuid(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();		
	}
	//根据文件名返回Content类型
	public static String getContentType(String fileName){
		String ctype = "application/bin";
		if(fileName.toLowerCase().endsWith("gif")){
			ctype = "image/gif";
		}
		if(fileName.toLowerCase().endsWith("jpg")){
			ctype = "image/jpeg";
		}
		if(fileName.toLowerCase().endsWith("png")){
			ctype = "image/png";
		}
		return ctype;
	}
}
