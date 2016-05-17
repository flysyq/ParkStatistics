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

	public static String savePic(String openPic, String image_prefix,String openTime) throws IOException {
		String day = openTime.split("\\ {1,}")[0];
		String image_name = Stool.uuid() + ".jpg";
		String image_path = file_path + "/"+image_prefix+"/" + day;
		String image_file =  image_path+ "/" + image_name;
		

		byte[] imgBytes = Base64.getDecoder().decode(openPic);
		System.out.println(Paths.get(image_path));
		
		Files.createDirectories(Paths.get(image_path));
		Files.write(Paths.get(image_file), imgBytes);
		return image_name;
	}
	
	public static String saveOpenPic(String openPic,String openTime) throws IOException{
		return savePic(openPic,"open_hand",openTime);
	}

}
