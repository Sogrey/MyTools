package org.sogrey.mytools.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件操作
 * 
 * @author Sogrey
 * 
 */
public final class FileUtil {
	/** 流缓存大小 */
	public static final int BUFFERSIZE = 1024;
	public static File updateFile = null;// 更新后文件

	/**
	 * 把文件从src复制到dst<br>
	 * 
	 * @param src
	 *            源文件
	 * @param dst
	 *            目标文件
	 * */
	public static boolean copyFile(File src, File dst) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			byte[] buffer = new byte[BUFFERSIZE];
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dst);
			int len = 0;// 实际读取大小
			/*
			 * fis.read(byte[] buffer, int byteOffset, int byteCount)
			 * @param buffer 缓存（本次读写内容）
			 * @param byteOffset 偏移量（自动移到上次读到的位置）
			 * @param byteCount 缓存数据量
			 * @return 实际读取大小
			 * */
			try {
				// while 条件是判断有没有读到文件，读到了，写入，没读到则不写
				while ((len = fis.read(buffer, 0, buffer.length)) > 0) {
					fos.write(buffer);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 读文本文件
	 * 
	 * @param path
	 *            文件路径
	 * @return 文件内容
	 * */
	public static String read(String path) {
		String str = null;
		FileReader fr = null;
		char[] buffer = new char[BUFFERSIZE];
		StringBuilder sb = new StringBuilder();
		try {
			fr = new FileReader(path);
			int count;
			// read 读字符，读到的是真实长度
			while ((count = fr.read(buffer)) > 0) {
				sb.append(buffer, 0, count);
			}
			str = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (Exception e) {
				}
			}
		}
		return str;
	}

	/**
	 * 写出到文件
	 * 
	 * @param output
	 *            需要写出到文件的字符串
	 * */
	public static void write(String output) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(Constants.INFO_PATH);
			byte[] bytes = output.getBytes();
			fos.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** 文件创建类 */
	public static void createFile(String app_name) throws IOException {
		// 创建文件夹及文件
		File file = new File(Constants.UPDATE_PATH); // 软件更新保存根目录
		if (!file.exists()) {
			try {
				// 按照指定的路径创建文件夹
				file.mkdirs();
			} catch (Exception e) {
			}
		}
		String path = Constants.UPDATE_PATH + app_name;
		updateFile = new File(path); // 软件更新文件
		if (updateFile.exists()) {
			updateFile.delete();
		}
		if (!updateFile.exists()){
			try {
				// 在指定的文件夹中创建文件
				updateFile.createNewFile();
			} catch (Exception e) {
			}
		}
	}
}
