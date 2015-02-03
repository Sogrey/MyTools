/**
 * 
 */
package org.sogrey.mytools.util.api;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.sogrey.mytools.util.Param;
import org.sogrey.mytools.util.JavaBean.NameValue;

/**
 * @author Administrator
 * 
 */
public class APIOperateAboutVersion {
	String TAG = this.getClass().getSimpleName();
	/**
	 * 域名,从教室控制程序共享文件读取<br>
	 *Context  otherAppsContext = mContext.createPackageContext(
					"com.glendale.iclassroom", 0);<br>
	 * sp_userinfo = otherAppsContext.getSharedPreferences("USERINFO",
					Context.MODE_WORLD_READABLE);<br>
	 * _url = sp_userinfo.getString("CLOUDURL", "");// 服务器ip<br>
	 */
	String url = "";
	/**
	 * API访问类
	 */
	APIHelper apiHelper = null;
	Param param = null;
	JSONObject resultObj = null;

	public APIOperateAboutVersion(String _url) {
		this.url = _url;
		apiHelper = new APIHelper();
	}
	
	/**HTTP GET访问方法，将参数拼接成key=value&的String形式*/
	public String makeArgs(List<NameValue> param) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < param.size(); i++) {
			sb.append(param.get(i).getName());
			sb.append("=");
			sb.append(param.get(i).getValue());
			sb.append("&");
		}
		return sb.toString();
	}

	/**
	 * 获取最新版本信息<br>
	 *  HTTP请求方式 : GET
	 * 
	 * @param platform
	 *            int 平台类型 (1: Windows 教师端; 2: Android 学生端; 3: Windows8 学生端; 4:
	 *            IOS 家长端; 5：Android 家长端)
	 * @return
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws IOException
	 */
	public JSONObject GetLastVersion(int platform)
			throws ClientProtocolException, JSONException, IOException {
		param = new Param();
		param.setPair("platform", platform);
		resultObj = apiHelper.SEND_MESSAGE_HTTP(url + "upgrades/last.json?"
				+ makeArgs(param.GetParamList()));
		return resultObj;
	}

	/**
	 * 获取历史版本信息<br>
	 *  HTTP请求方式 ：GET
	 * 
	 * @param platform
	 *            int 平台类型 (1: windows 乐享教师端; 2: android 乐享电子书包; 3: cloud 云平台;
	 *            4: board 电子黑板)
	 * @return
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws IOException
	 */
	public JSONObject GetHistroyVersion(int platform)
			throws ClientProtocolException, JSONException, IOException {
		param = new Param();
		param.setPair("platform", platform);
		resultObj = apiHelper.SEND_MESSAGE_HTTP(url + "upgrades/histroy?"
				+ makeArgs(param.GetParamList()));
		return resultObj;
	}
}
