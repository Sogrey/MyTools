package org.sogrey.mytools.util.JavaBean;

import java.util.ArrayList;

/**
 * 最新版本
 * 
 * @author Administrator
 * 
 */
public class LastVersionBean {

	// {
	// "id": 1,
	// "title": "更你好美女硅化木更好",
	// "version": "Ver4.0.0.2",
	// "version_number": 4000000002,
	// "version_type": 1,
	// "description": "男后宫红玫瑰黑玫瑰",
	// "platform": 4,
	// "status": 0,
	// "attachments": [
	// {
	// "id": 1,
	// "filename": "试题录入测试反馈意见.doc",
	// "filesize": 0,
	// "filepath":
	// "F:/apache-tomcat-7.0.39/webapps/lx/upload/upgrade/4000000002/",
	// "url":
	// "http://media.lirenkj.com/upload/upgrade/4000000002/试题录入测试反馈意见.doc",
	// "version": "Ver4.0.0.2",
	// "version_number": 4000000002
	// }
	// ]
	// }
	int id;
	String title;// string 版本标题
	String version;// string 版本（可用户客户端显示）
	int version_number;// int 数值型版本号（可提供客户端进行版本比较）
	String description;// string 版本描述
	int platform;// int 平台类型 (1: Windows 教师端; 2: Android 学生端; 3: Windows8 学生端;
					// 4: IOS 家长端; 5：Android 家长端)
	int status;// int 版本状态 (0, "未发布", 1, "已发布", 2, "禁用")
	int version_type;// int 版本类型（0： "未知版本"； 1："内部测试版"； 2：外部测试版； 3：正式发行版）

	ArrayList<NewFileBean> newFileList;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	// String filename ;//string 文件名称
	// long filesize ;//long 文件大小
	// String filepath ;//string 文件相对路径
	// String url ;//文件地址
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the version_number
	 */
	public int getVersion_number() {
		return version_number;
	}

	/**
	 * @param version_number
	 *            the version_number to set
	 */
	public void setVersion_number(int version_number) {
		this.version_number = version_number;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the platform
	 */
	public int getPlatform() {
		return platform;
	}

	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(int platform) {
		this.platform = platform;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the version_type
	 */
	public int getVersion_type() {
		return version_type;
	}

	/**
	 * @param version_type
	 *            the version_type to set
	 */
	public void setVersion_type(int version_type) {
		this.version_type = version_type;
	}

	/**
	 * @return the newFileList
	 */
	public ArrayList<NewFileBean> getNewFileList() {
		return newFileList;
	}

	/**
	 * @param newFileList the newFileList to set
	 */
	public void setNewFileList(ArrayList<NewFileBean> newFileList) {
		this.newFileList = newFileList;
	}
}
