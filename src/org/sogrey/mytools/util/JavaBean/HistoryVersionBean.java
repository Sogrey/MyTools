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
//	    "title" : "test���ѽ�ž�",
//	    "show_version" : "Ver3.0.0.1",
//	    "version" : 3000000001,
//	    "description" : " ������ħ���࣬�����",
//	    "platform" : 1,
//	    "status" : 0,
//	    "version_type" : 1
//	  }]
	int count;//���´���
	ArrayList<LastVersionBean> HistoryVersionList;//ÿ�θ��±���汾������   newFileList Ϊ��
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
