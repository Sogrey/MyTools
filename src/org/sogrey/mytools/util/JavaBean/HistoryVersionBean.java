/**
 * 
 */
package org.sogrey.mytools.util.JavaBean;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class HistoryVersionBean {

//	 "count" : 5,
//	  "upgrades" : [ {
//	    "id" : 3,
//	    "title" : "test经费解放军",
//	    "show_version" : "Ver3.0.0.1",
//	    "version" : 3000000001,
//	    "description" : " 密码锁魔法膏，，风格",
//	    "platform" : 1,
//	    "status" : 0,
//	    "version_type" : 1
//	  }]
	int count;//更新次数
	ArrayList<LastVersionBean> HistoryVersionList;//每次更新标题版本及描述   newFileList 为空
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the historyVersionList
	 */
	public ArrayList<LastVersionBean> getHistoryVersionList() {
		return HistoryVersionList;
	}
	/**
	 * @param historyVersionList the historyVersionList to set
	 */
	public void setHistoryVersionList(ArrayList<LastVersionBean> historyVersionList) {
		HistoryVersionList = historyVersionList;
	}
}
