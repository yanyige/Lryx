package cn.edu.nenu.lryx.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

/**   
* @Title: FileUtil.java 
* @Package cn.edu.nenu.lryx.action 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月17日 上午12:04:40 
* @version V1.0   
*/
public class ActionUtil {
	public static  String upload(String dir, File upload, String uploadFileName){
		System.out.println("90909090909090");
		String path = ServletActionContext.getServletContext().getRealPath(dir);
		String rp = "";
		try {
			if (upload != null) {
				File f = upload;
				String fileName = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名
				String name = fileName+ uploadFileName.substring(uploadFileName.lastIndexOf(".")); // 保存在硬盘中的文件名

				FileInputStream inputStream = new FileInputStream(f);
				FileOutputStream outputStream = new FileOutputStream(path+ "\\" + name);
				byte[] buf = new byte[1024];
				int length = 0;
				while ((length = inputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, length);
				}
				inputStream.close();
				outputStream.flush();
				rp = dir+"/"+name;
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rp;
	}
	public static void setJsonMap(Map<String,Object> jsonMap, boolean finish, String msg){
			jsonMap.put("finish", finish);
			jsonMap.put("erorrMsg", msg);
	
	}
}
