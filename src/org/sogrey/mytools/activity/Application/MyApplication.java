/**
 * 
 */
package org.sogrey.mytools.activity.Application;

import org.sogrey.mytools.util.Constants;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * @author Administrator
 *
 */
public class MyApplication extends Application {
	  public static int localVersion = 0;// 本地安装版本  
	  
	    public static int serverVersion = 2;// 服务器版本  
	  
	    public static String downloadDir =Constants.UPDATE_PATH;// 安装目录  
	  
	    @Override  
	    public void onCreate() {  
	        super.onCreate();  
	        try {  
	            PackageInfo packageInfo = getApplicationContext()  
	                    .getPackageManager().getPackageInfo(getPackageName(), 0);  
	            localVersion = packageInfo.versionCode;  
	        } catch (NameNotFoundException e) {  
	            e.printStackTrace();  
	        }  
	  
	        /*** 
	         * 在这里写一个方法用于请求获取服务器端的serverVersion. 
	         */  
	  
	    }  
}
