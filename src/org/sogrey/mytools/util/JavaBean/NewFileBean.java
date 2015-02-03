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
	// "filename": "试题录入测试反馈意见.doc",
	// "filesize": 0,
	// "filepath":
	// "F:/apache-tomcat-7.0.39/webapps/lx/upload/upgrade/4000000002/",
	// "url":
	// "http://media.lirenkj.com/upload/upgrade/4000000002/试题录入测试反馈意见.doc",
	// "version": "Ver4.0.0.2",
	// "version_number": 4000000002
	int id;
	String filename;// string 文件名称
	long filesize;// long 文件大小
	String filepath;// string 文件相对路径
	String url;// 文件地址
	String version;// string 版本（可用户客户端显示）
	int version_number;// int 数值型版本号（可提供客户端进行版本比较）
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
