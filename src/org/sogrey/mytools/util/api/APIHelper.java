package org.sogrey.mytools.util.api;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.sogrey.mytools.util.JavaBean.NameValue;

import android.util.Log;

/**
 * @author:licheng
 * @describe:API������
 * @name:APIHelper.java
 * @date:2014-8-13
 */
public class APIHelper {
	String TAG = this.getClass().getSimpleName();
	HttpPost request=null;
	HttpResponse httpResponse=null;
	JSONObject jsonObj=null;
	StringEntity se=null;
	
	/**
	 * ���������������
	 * @param url
	 * @param param
	 * @return
	 * @throws JSONException 
	 * @throws ClientProtocolException 
	 * @throws IOException
	 */
	public JSONObject SEND_MESSAGE_JSON(final String url, final List<NameValue> param) throws JSONException, ClientProtocolException, IOException{
		JSONObject result =null;
				Log.e(TAG, "�ӿڵ�ַ:"+url);
				request = new HttpPost(url);
				request.setHeader("Content-Type", "application/json;charset=utf-8");
//				request.setHeader("Range","bytes="+"");
				Log.e(TAG, "----------������----------");
				for (int i = 0; i < param.size(); i++) {
					Log.e(TAG, "NAME:"+param.get(i).getName()+" VALUE:"+param.get(i).getValue());
				}
				Log.e(TAG, "----------������----------");
					jsonObj = ParamToJson(param);
				Log.e(TAG, "jsonObj===>"+jsonObj.toString());
					se = new StringEntity(jsonObj.toString(),"utf-8");
				request.setEntity(se);
				HttpClient client = new DefaultHttpClient();
//				client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
				// ����ʱ
		        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		        // ��ȡ��ʱ
		        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
					httpResponse = client.execute(request);
					
				String EntityString = null;
					EntityString = EntityUtils.toString(httpResponse.getEntity());
				Log.e(TAG, "����ֵ��EntityString=="+EntityString);
					result = new JSONObject(EntityString);
				Log.e(TAG, "����ֵ��JSONObject=="+result.toString());
		return result;
	}
	/**
	 * ���������������
	 * @param url
	 * @param param
	 * @return
	 * @throws JSONException 
	 * @throws ClientProtocolException 
	 * @throws IOException
	 */
//	public JSONObject SEND_MESSAGE_DELETE(String url, List<NameValue> param) throws JSONException, ClientProtocolException, IOException{
		public JSONObject SEND_MESSAGE_DELETE(String url) throws JSONException, ClientProtocolException, IOException{
		Log.e(TAG, "�ӿڵ�ַ:"+url);
		HttpDelete request = new HttpDelete(url);
		request.setHeader("Content-Type", "application/json;charset=utf-8");
//		Log.e(TAG, "----------������----------");
//		HttpParams params = request.getParams();
//		for (int i = 0; i < param.size(); i++) {
//			Log.e(TAG, "NAME:"+param.get(i).getName()+" VALUE:"+param.get(i).getValue());
//		params.setParameter(param.get(i).getName(), param.get(i).getValue().toString());
//		}
//		Log.e(TAG, "----------������----------");
//		request.setParams(params);
//		HttpClient client = new DefaultHttpClient();
//		client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
		HttpClient client = new DefaultHttpClient();
		// ����ʱ
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        // ��ȡ��ʱ
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
		httpResponse = client.execute(request);
		
		String EntityString = EntityUtils.toString(httpResponse.getEntity());
		Log.e(TAG, "����ֵ��EntityString=="+EntityString);
		JSONObject result = new JSONObject(EntityString);
		Log.e(TAG, "����ֵ��JSONObject=="+result.toString());
		return result;
	}
	
	public JSONObject SEND_MESSAGE_HTTP(String url) throws JSONException, ClientProtocolException, IOException{
		Log.e(TAG, "�ӿڵ�ַ:"+url);
		request = new HttpPost(url);
//		request.setHeader("Content-Type", "application/jso/n;charset=utf-8");
		HttpClient client = new DefaultHttpClient();
		// ����ʱ
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        // ��ȡ��ʱ
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
		httpResponse = client.execute(request);
		String result = EntityUtils.toString(httpResponse.getEntity());
		Log.e(TAG, "����ֵ��"+result.toString());
		return new JSONObject(result);
	}
//	
//	public String SEND_MESSAGE_HTTP_DELETE(String url) throws JSONException, ClientProtocolException, IOException{
//		Log.e(TAG, "�ӿڵ�ַ:"+url);
//		HttpDelete request_delete = new HttpDelete(url);
////		request_get.setHeader("Content-Type", "application/json;charset=utf-8");
//		httpResponse = new DefaultHttpClient().execute(request_delete);
//		String result = EntityUtils.toString(httpResponse.getEntity());
//		Log.e(TAG, "����ֵ��"+result.toString());
//		return result;
//	}
	
	/**
	 * ��List<NameValue>����ת��ΪJSONObject����
	 * @param param
	 * @return
	 * @throws JSONException
	 */
	public JSONObject ParamToJson(List<NameValue> param) throws JSONException{
		jsonObj = new JSONObject();
		for (int i = 0; i < param.size(); i++) {
			jsonObj.put(param.get(i).getName(), param.get(i).getValue());
		}
		return jsonObj;
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
//		for (NameValue entry : param) {
//			sb.append(entry.getName());
//			sb.append("=");
//			sb.append(entry.getValue());
//			sb.append("&");
//		}
		return sb.toString();
	}
}
