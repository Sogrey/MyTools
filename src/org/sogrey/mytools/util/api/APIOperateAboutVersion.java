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
	 * ����,�ӽ��ҿ��Ƴ������ļ���ȡ<br>
	 *Context  otherAppsContext = mContext.createPackageContext(
					"com.glendale.iclassroom", 0);<br>
	 * sp_userinfo = otherAppsContext.getSharedPreferences("USERINFO",
					Context.MODE_WORLD_READABLE);<br>
	 * _url = sp_userinfo.getString("CLOUDURL", "");// ������ip<br>
	 */
	String url = "";
	/**
	 * API������
	 */
	APIHelper apiHelper = null;
	Param param = null;
	JSONObject resultObj = null;

	public APIOperateAboutVersion(String _url) {
		this.url = _url;
		apiHelper = new APIHelper();
	}
	
	/**HTTP GET���ʷ�����������ƴ�ӳ�key=value&��String��ʽ*/
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
	 * ��ȡ���°汾��Ϣ<br>
	 *  HTTP����ʽ : GET
	 * 
	 * @param platform
	 *            int ƽ̨���� (1: Windows ��ʦ��; 2: Android ѧ����; 3: Windows8 ѧ����; 4:
	 *            IOS �ҳ���; 5��Android �ҳ���)
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
	 * ��ȡ��ʷ�汾��Ϣ<br>
	 *  HTTP����ʽ ��GET
	 * 
	 * @param platform
	 *            int ƽ̨���� (1: windows �����ʦ��; 2: android ����������; 3: cloud ��ƽ̨;
	 *            4: board ���Ӻڰ�)
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
