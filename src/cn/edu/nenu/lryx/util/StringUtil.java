package cn.edu.nenu.lryx.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**   
 * @Title: StringUtil.java 
 * @Package cn.edu.nenu.lryx.util 
 * @Description: TODO
 * @author py pengyang813@foxmail.com
 * @date 2016年3月7日 下午8:17:49 
 * @version V1.0   
 */
public class StringUtil {
	public static String getCurrentTimeStr(){
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date n_d = new Date();
		Random r = new Random();
		n_d.setTime(n_d.getTime() - r.nextInt(2000) - 1000);
		return fmt.format(n_d);
	}
}
