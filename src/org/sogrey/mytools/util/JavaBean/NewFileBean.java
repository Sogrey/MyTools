/**
 * 
 */
package org.sogrey.mytools.util.JavaBean;

/**
 * @author Administrator
 * 
 */
public class NewFileBean {
	// "id": 1,
	// "filename": "����¼����Է������.doc",
	// "filesize": 0,
	// "filepath":
	// "F:/apache-tomcat-7.0.39/webapps/lx/upload/upgrade/4000000002/",
	// "url":
	// "http://media.lirenkj.com/upload/upgrade/4000000002/����¼����Է������.doc",
	// "version": "Ver4.0.0.2",
	// "version_number": 4000000002
	int id;
	String filename;// string �ļ�����
	long filesize;// long �ļ���С
	String filepath;// string �ļ����·��
	String url;// �ļ���ַ
	String version;// string �汾�����û��ͻ�����ʾ��
	int version_number;// int ��ֵ�Ͱ汾�ţ����ṩ�ͻ��˽��а汾�Ƚϣ�
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
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the filesize
	 */
	public long getFilesize() {
		return filesize;
	}
	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
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
	 * @param version_number the version_number to set
	 */
	public void setVersion_number(int version_number) {
		this.version_number = version_number;
	}
}
