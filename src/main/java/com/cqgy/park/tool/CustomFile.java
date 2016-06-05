/**
 * @作者 admin
 * @时间 2016年5月16日 下午4:47:30
 * @类名 CustomFile.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月16日 下午4:47:30
 *   修改描述
 */
package com.cqgy.park.tool;

import java.nio.file.Paths;
import java.util.Base64;
import java.io.IOException;
import java.nio.file.Files;

public class CustomFile {

	static String file_path = CustomProps.getProp("file.save.path");
	static String open_hand="open_hand";
	static String car_in="car_in";
	static String car_out="car_out";
	static String car_park_space="car_park_space";
	public static String savePic(String pic, String image_prefix,String time) throws IOException {
		String day = time.split("\\ {1,}")[0];
		String image_name = Stool.uuid() + ".jpg";
		String image_path = file_path + "/"+image_prefix+"/" + day;
		String image_file =  image_path+ "/" + image_name;
		

		byte[] imgBytes = Base64.getDecoder().decode(pic);
		System.out.println(Paths.get(image_path));
		
		Files.createDirectories(Paths.get(image_path));
		Files.write(Paths.get(image_file), imgBytes);
		return image_name;
	}
	
	public static String saveOpenPic(String openPic,String openTime) throws IOException{
		return savePic(openPic,open_hand,openTime);
	}
	
	public static String saveComePic(String comePic,String comeTime) throws IOException{
		return savePic(comePic, car_in, comeTime);
	}
	
	public static String saveGoPic(String goPic,String goTime) throws IOException{
		return savePic(goPic,car_out,goTime);
	}
	
	public static String saveParkSpacePic(String parkSpacePic,String goTime) throws IOException{
		return savePic(parkSpacePic,car_park_space,goTime);
	}


	public static String getOpen_hand() {
		return open_hand;
	}

	public static String getCar_in() {
		return car_in;
	}

	public static String getCar_out() {
		return car_out;
	}

	public static String getCar_park_space() {
		return car_park_space;
	}
	
}
