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
	  public static int localVersion = 0;// ���ذ�װ�汾  
	  
	    public static int serverVersion = 2;// �������汾  
	  
	    public static String downloadDir =Constants.UPDATE_PATH;// ��װĿ¼  
	  
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
	         * ������дһ���������������ȡ�������˵�serverVersion. 
	         */  
	  
	    }  
}
