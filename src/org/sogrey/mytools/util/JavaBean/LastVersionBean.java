package org.sogrey.mytools.util.JavaBean;

import java.util.ArrayList;

/**
 * ���°汾
 * 
 * @author Administrator
 * 
 */
public class LastVersionBean {

	// {
	// "id": 1,
	// "title": "�������Ů�軯ľ����",
	// "version": "Ver4.0.0.2",
	// "version_number": 4000000002,
	// "version_type": 1,
	// "description": "�к󹬺�õ���õ��",
	// "platform": 4,
	// "status": 0,
	// "attachments": [
	// {
	// "id": 1,
	// "filename": "����¼����Է������.doc",
	// "filesize": 0,
	// "filepath":
	// "F:/apache-tomcat-7.0.39/webapps/lx/upload/upgrade/4000000002/",
	// "url":
	// "http://media.lirenkj.com/upload/upgrade/4000000002/����¼����Է������.doc",
	// "version": "Ver4.0.0.2",
	// "version_number": 4000000002
	// }
	// ]
	// }
	int id;
	String title;// string �汾����
	String version;// string �汾�����û��ͻ�����ʾ��
	int version_number;// int ��ֵ�Ͱ汾�ţ����ṩ�ͻ��˽��а汾�Ƚϣ�
	String description;// string �汾����
	int platform;// int ƽ̨���� (1: Windows ��ʦ��; 2: Android ѧ����; 3: Windows8 ѧ����;
					// 4: IOS �ҳ���; 5��Android �ҳ���)
	int status;// int �汾״̬ (0, "δ����", 1, "�ѷ���", 2, "����")
	int version_type;// int �汾���ͣ�0�� "δ֪�汾"�� 1��"�ڲ����԰�"�� 2���ⲿ���԰棻 3����ʽ���а棩

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

	// String filename ;//string �ļ�����
	// long filesize ;//long �ļ���С
	// String filepath ;//string �ļ����·��
	// String url ;//�ļ���ַ
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
