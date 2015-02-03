package org.sogrey.mytools.util;

import android.os.Environment;


/**
 * 常量表
 * 
 * @author Administrator
 * 
 */
public class Constants {

	public static final String PRACTICE_TYPE = "type";
	public static final String PRACTICE_CHAPTER = "chapter";
	public static final String PRACTICE_EXAM = "exam";
	
	public static final String REPORT_TYPE_SCENE = "scene";
	public static final String REPORT_TYPE_HISTORY = "history";
	
	/** Tab 标签名字作为标签为以识别 */
	/** Tab-练习 */
	public static final String TAB_PRACTICE = "practice";
	/** Tab-测试 */
	public static final String TAB_EXAM = "exam";
	/** Tab-我的收藏 */
	public static final String TAB_MY_FAVORITES = "my_favorites";
	
	/**获取基础信息广播*/
	public static final String RECEIVER_GET_BASE_DATA="android.intent.action.lr.practice.GET_BASE_DATA";
    /**科目/学段切换广播*/
    public static final String RECEIVER_LEVEL_OR_SUBJECT_CHANGE="android.intent.action.lr.practice.LEVEL_OR_SUBJECT_CHANGE";
    /**获取题目列表广播*/
    public static final String RECEIVER_EXAM_PAPER_LIST="android.intent.action.lr.practice.EXAM_PAPER_LIST";
    /**过滤条件切换获取套题广播*/
    public static final String RECEIVER_EXAM_PAPER_LIST_TYPE_ORDER="android.intent.action.lr.practice.EXAM_PAPER_LIST_TYPE_ORDER";
    /**获取题目详情广播*/
    public static final String RECEIVER_EXAM_PAPER_INFO="android.intent.action.lr.practice.EXAM_PAPER_INFO";
    /**获取题目列表完成广播*/
    public static final String RECEIVER_EXAM_PAPER_INFO_SUCCESS="android.intent.action.lr.practice.EXAM_PAPER_INFO_SUCCESS";
    /**获取练习记录题目列表完成广播*/
    public static final String RECEIVER_GOT_HISTORY_SUCCESS="android.intent.action.lr.practice.GOT_HISTORY_SUCCESS";
    public static final String RECEIVER_GOT_HISTORY_SUCCESS_2_PARSING="android.intent.action.lr.practice.GOT_HISTORY_SUCCESS_2_PARSING";
    /**获取题目列表完成广播-预览*/
    public static final String RECEIVER_EXAM_PAPER_INFO_SUCCESS_PREVIEW="android.intent.action.lr.practice.EXAM_PAPER_INFO_SUCCESS_PREVIEW";
    /**删除收藏完成广播*/
    public static final String RECEIVER_FAVORITS_DELETE="android.intent.action.lr.practice.FAVORITS_DELETE";
    /**删除练习历史完成广播*/
    public static final String RECEIVER_PRACTICE_DELETE="android.intent.action.lr.practice.PRACTICE_DELETE";
    /**删除测试历史完成广播*/
    public static final String RECEIVER_EXAM_DELETE="android.intent.action.lr.practice.EXAM_DELETE";
    /**获取题目图片完成广播*/
    public static final String RECEIVER_GOT_QUES_IMG="android.intent.action.lr.practice.GOT_QUES_IMG";
    
    /** 学科切换广播区分码 */
    public static final int CODE_SUBJECT_CHANGE = 0x10;
    /** 学段切换广播区分码 */
    public static final int CODE_LEVEL_CHANGE = 0x11;
    
    /**题型练习广播码 */
    public static final int  RECEIVER_GET_TYPE_QUSET_LIST = 1000;
    /**章节练习广播码 */
    public static final int  RECEIVER_GET_CHAPTER_QUSET_LIST = 2000;
    /**真题练习广播码 */
    public static final int  RECEIVER_GET_EXAM_QUSET_LIST = 3000;
    /**查询本地练习广播码 */
    public static final int  RECEIVER_CHECK_PRACTICE_HISTORY = 4000;
    /**查询本地测试广播码 */
    public static final int  RECEIVER_CHECK_EXAM_HISTORY = 5000;
    /**提交答案广播码 */
    public static final int  RECEIVER_SUBMIT_ANSWER = 10000;
    
    public enum TEST_FLAG{
    	TYPE,CHAPTER ,EXAM
    }
    /**练习标识 */
    public static final String TEST_FLAG = "test_flag";
    
    /**按题型 */
    public static final int TEST_TYPE = 0x1;
    /**按章节 */
    public static final int TEST_CHAPTER = 0x2;
    /**按套题 */
    public static final int TEST_EXAM = 0x3;
    
    /**按套题预览 */
    public static final int TEST_EXAM_PREVIEW = 0x1;
    /**按套题答题 */
    public static final int TEST_EXAM_ANSWER = 0x2;
    
    /**题型-单选 */
    public static final int QUSE_TYPE_SINGLE_SELECT = 0x1;
    /**题型-判断 */
    public static final int QUSE_TYPE_ANSWER = 0x2;
    /**题型-判断 */
    public static final int QUSE_TYPE_JUDGE = 0x3;
    /**题型-填空 */
    public static final int QUSE_TYPE_FILLBLANK = 0x4;
    /**题型-多选 */
    public static final int QUSE_TYPE_MULTI_SELECT = 0x5;
    
    /**单选题ID */
    public static final int QUSE_TYPE_SINGLE_SELECT_ID = 151;
    /**多选题ID */
    public static final int QUSE_TYPE_MULTI_SELECT_ID = 152;
    /**填空题ID */
    public static final int QUSE_TYPE_FILLBLANK_ID = 153;
    /**判断题ID */
    public static final int QUSE_TYPE_JUDGE_ID = 154;
    /**问答题ID */
    public static final int QUSE_TYPE_ASK_ID = 155;
    
    
    /**收藏资源区分码:1：资源 */
    public static final int FAVORITES_RESOURCES = 1;
    /**收藏资源区分码:2：图书 */
    public static final int FAVORITES_BOOKS = 2;
    /**收藏资源区分码: 3：试题 */
    public static final int FAVORITES_QUESTION = 3;
    /**收藏资源区分码:4：试卷 */
    public static final int FAVORITES_EXAM_PAPER = 4;
    /**收藏资源区分码: 5：文档 */
    public static final int FAVORITES_DOCUMENT = 5;

    /**答题日期 */
    public static final String QUSE_ANSWER_DATE = "yyyy年MM月dd日";
    /**答题(错题入库)日期 */
    public static final String QUSE_ANSWER_ERR_DATE = "yyyy-M-d";
    /**答题时间 */
    public static final String QUSE_ANSWER_TIME= "HH:mm:ss";
    
    /**未答完 */
    public static final int QUSE_ANSWER_NOT_FINISHED= 0x0;
    /**已答完 */
    public static final int QUSE_ANSWER_FINISHED=0x1;
    
    /**资源域名*/
//    public static final String RES_URL = "http://125.76.225.237:5080";
//    public static final String RES_URL = "http://123.139.89.90:5080";
    public static final String RES_URL = "http://media.liren-eschoolbag.com";
    
	
	/**单位缓存长度*/
	public static final int SIZE_BUFFER =1024;
	
	/** SD卡路径 */
	public final static String PATH_SDCARD= Environment
			.getExternalStorageDirectory().getPath();
	/**
	 * 更新文件夹保存路径<br>
	 * "/liren/update/"
	 */
	public static final String UPDATE_PATH= PATH_SDCARD+
			"/liren/update/";
	/**
	 * 缓存图片文件夹保存路径<br>
	 * "/liren/lxpractice/camera/"
	 */
	public static final String CAMERA_PATH= PATH_SDCARD+
			 "/liren/lxpractice/camera/";
	/**
	 * 拍照/草稿纸图片文件夹保存路径<br>
	 * "sd +/liren/questions/{题目ID}/"
	 */
//	public static final String LXPRACTICE_PATH= PATH_SDCARD+
//			"/liren/lxpractice/";
	public static final String LXPRACTICE_PATH= PATH_SDCARD+
			"/liren/questions/";
	/**题目图片缓存位置<br>SD+/liren/ques/*/
//	public static final String ERR_QUES_IMG_PATH= PATH_SDCARD+
//			"/liren/ques/";
/**
	 * 照片保存路径<br>
	 * "/sdcard/lxpractice/camera"
	 *//*
	public final static String CAMERA_PATH = PATH_SDCARD
			+ "/lxpractice/camera/";*/
	/**
	 * Log文件保存路径<br>
	 * "/sdcard/lxpractice/lxpractice.txt"
	 */
	public final static String LOG_PATH = PATH_SDCARD
			+ "/lxpractice/lxpractice.txt";
	/**
	 * txt文件保存路径<br>
	 * "/sdcard/lxpractice/info.txt"
	 */
	public final static String INFO_PATH = PATH_SDCARD + "/lxpractice/info.txt";
	/** 相机保存图片按时间命名，时间格式化 */
	public final static String FMT_FILENAME = "yyyyMMddkkmmssSSS";
	/** 练习报告时间格式 */
	public final static String FMT_REPORT_DATE ="yyyy-MM-dd HH:mm:ss";

}
