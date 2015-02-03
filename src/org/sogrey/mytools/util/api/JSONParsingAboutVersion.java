package org.sogrey.mytools.util.api;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sogrey.mytools.util.JavaBean.HistoryVersionBean;
import org.sogrey.mytools.util.JavaBean.LastVersionBean;
import org.sogrey.mytools.util.JavaBean.NewFileBean;

/**
 * JSON 解析（按接口）
 * 
 * @author Administrator
 * 
 */
public class JSONParsingAboutVersion {

	String TAG = this.getClass().getSimpleName();

	/**
	 * 获取最新版本<br>
	 * 		{<br>
	    "id": 1,<br>
	    "title": "更你好美女硅化木更好",<br>
	    "version": "Ver4.0.0.2",<br>
	    "version_number": 4000000002,<br>
	    "version_type": 1,<br>
	    "description": "男后宫红玫瑰黑玫瑰",<br>
	    "platform": 4,<br>
	    "status": 0,<br>
	    "attachments": [<br>
	        {<br>
	            "id": 1,<br>
	            "filename": "试题录入测试反馈意见.doc",<br>
	            "filesize": 0,<br>
	            "filepath": "F:/apache-tomcat-7.0.39/webapps/lx/upload/upgrade/4000000002/",<br>
	            "url": "http://media.lirenkj.com/upload/upgrade/4000000002/试题录入测试反馈意见.doc",<br>
	            "version": "Ver4.0.0.2",<br>
	            "version_number": 4000000002<br>
	        }<br>
	    ]<br>
	}<br>
	 * @param object
	 * @return
	 */
	public LastVersionBean GetLastVersionBean(JSONObject object) {
		LastVersionBean last = new LastVersionBean();
		try {
			
			last.setId(object.getInt("id"));
			last.setTitle(object.getString("title"));
			last.setVersion(object.getString("version"));
			last.setVersion_number(object.getInt("version_number"));
			last.setVersion_type(object.getInt("version_type"));
			last.setDescription(object.getString("description"));
			last.setPlatform(object.getInt("platform"));
			last.setStatus(object.getInt("status"));
			
			JSONArray attachments = object.getJSONArray("attachments");
			ArrayList<NewFileBean> newFileList = new ArrayList<NewFileBean>();
			NewFileBean newFile ;
			for (int i = 0; i < attachments.length(); i++) {
				JSONObject object2 = attachments.getJSONObject(i);
				newFile = new NewFileBean();
				newFile.setId(object2.getInt("id"));
				newFile.setFilename(object2.getString("filename"));
				newFile.setFilesize(object2.getLong("filesize"));
				newFile.setFilepath(object2.getString("filepath"));
				newFile.setUrl(object2.getString("url"));
				newFile.setVersion(object2.getString("version"));
				newFile.setVersion_number(object2.getInt("version_number"));
				newFileList.add(newFile);
			}
			last.setNewFileList(newFileList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return last;
	}
	/**
	 * 获取历史更新列表<br>
	 * 	{ "count" : 5,<br>
	  "upgrades" : [ {<br>
	    "id" : 3,<br>
	    "title" : "test经费解放军",<br>
	    "show_version" : "Ver3.0.0.1",<br>
	    "version" : 3000000001,<br>
	    "description" : " 密码锁魔法膏，，风格",<br>
	    "platform" : 1,<br>
	    "status" : 0,<br>
	    "version_type" : 1<br>
	  }]<br>
	  }<br>
	 * @param object
	 * @return
	 */
	public HistoryVersionBean GetHistoryVersionBean(JSONObject object) {
		HistoryVersionBean historyVersion = new HistoryVersionBean();
		try {
			historyVersion.setCount(object.getInt("count"));
			JSONArray upgrades = object.getJSONArray("upgrades");
			ArrayList<LastVersionBean> historyVersionList=new ArrayList<LastVersionBean>();
			LastVersionBean lastVersionBean ;
			for (int i = 0; i < upgrades.length(); i++) {
				lastVersionBean = new LastVersionBean();
				JSONObject object2 = upgrades.getJSONObject(i);
				lastVersionBean.setId(object2.getInt("id"));
				lastVersionBean.setTitle(object2.getString("title"));
				lastVersionBean.setVersion(object2.getString("show_version"));
				lastVersionBean.setVersion_number(object2.getInt("version"));
				lastVersionBean.setDescription(object2.getString("description"));
				lastVersionBean.setPlatform(object2.getInt("platform"));
				lastVersionBean.setStatus(object2.getInt("status"));
				lastVersionBean.setVersion_type(object2.getInt("version_type"));
				lastVersionBean.setNewFileList(null);//详细某次更新列表暂未获取设为空
				historyVersionList.add(lastVersionBean);
			}
			historyVersion.setHistoryVersionList(historyVersionList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return historyVersion;
	}
}
