package org.sogrey.mytools.util;

import android.os.Environment;


/**
 * ������
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
	
	/** Tab ��ǩ������Ϊ��ǩΪ��ʶ�� */
	/** Tab-��ϰ */
	public static final String TAB_PRACTICE = "practice";
	/** Tab-���� */
	public static final String TAB_EXAM = "exam";
	/** Tab-�ҵ��ղ� */
	public static final String TAB_MY_FAVORITES = "my_favorites";
	
	/**��ȡ������Ϣ�㲥*/
	public static final String RECEIVER_GET_BASE_DATA="android.intent.action.lr.practice.GET_BASE_DATA";
    /**��Ŀ/ѧ���л��㲥*/
    public static final String RECEIVER_LEVEL_OR_SUBJECT_CHANGE="android.intent.action.lr.practice.LEVEL_OR_SUBJECT_CHANGE";
    /**��ȡ��Ŀ�б�㲥*/
    public static final String RECEIVER_EXAM_PAPER_LIST="android.intent.action.lr.practice.EXAM_PAPER_LIST";
    /**���������л���ȡ����㲥*/
    public static final String RECEIVER_EXAM_PAPER_LIST_TYPE_ORDER="android.intent.action.lr.practice.EXAM_PAPER_LIST_TYPE_ORDER";
    /**��ȡ��Ŀ����㲥*/
    public static final String RECEIVER_EXAM_PAPER_INFO="android.intent.action.lr.practice.EXAM_PAPER_INFO";
    /**��ȡ��Ŀ�б���ɹ㲥*/
    public static final String RECEIVER_EXAM_PAPER_INFO_SUCCESS="android.intent.action.lr.practice.EXAM_PAPER_INFO_SUCCESS";
    /**��ȡ��ϰ��¼��Ŀ�б���ɹ㲥*/
    public static final String RECEIVER_GOT_HISTORY_SUCCESS="android.intent.action.lr.practice.GOT_HISTORY_SUCCESS";
    public static final String RECEIVER_GOT_HISTORY_SUCCESS_2_PARSING="android.intent.action.lr.practice.GOT_HISTORY_SUCCESS_2_PARSING";
    /**��ȡ��Ŀ�б���ɹ㲥-Ԥ��*/
    public static final String RECEIVER_EXAM_PAPER_INFO_SUCCESS_PREVIEW="android.intent.action.lr.practice.EXAM_PAPER_INFO_SUCCESS_PREVIEW";
    /**ɾ���ղ���ɹ㲥*/
    public static final String RECEIVER_FAVORITS_DELETE="android.intent.action.lr.practice.FAVORITS_DELETE";
    /**ɾ����ϰ��ʷ��ɹ㲥*/
    public static final String RECEIVER_PRACTICE_DELETE="android.intent.action.lr.practice.PRACTICE_DELETE";
    /**ɾ��������ʷ��ɹ㲥*/
    public static final String RECEIVER_EXAM_DELETE="android.intent.action.lr.practice.EXAM_DELETE";
    /**��ȡ��ĿͼƬ��ɹ㲥*/
    public static final String RECEIVER_GOT_QUES_IMG="android.intent.action.lr.practice.GOT_QUES_IMG";
    
    /** ѧ���л��㲥������ */
    public static final int CODE_SUBJECT_CHANGE = 0x10;
    /** ѧ���л��㲥������ */
    public static final int CODE_LEVEL_CHANGE = 0x11;
    
    /**������ϰ�㲥�� */
    public static final int  RECEIVER_GET_TYPE_QUSET_LIST = 1000;
    /**�½���ϰ�㲥�� */
    public static final int  RECEIVER_GET_CHAPTER_QUSET_LIST = 2000;
    /**������ϰ�㲥�� */
    public static final int  RECEIVER_GET_EXAM_QUSET_LIST = 3000;
    /**��ѯ������ϰ�㲥�� */
    public static final int  RECEIVER_CHECK_PRACTICE_HISTORY = 4000;
    /**��ѯ���ز��Թ㲥�� */
    public static final int  RECEIVER_CHECK_EXAM_HISTORY = 5000;
    /**�ύ�𰸹㲥�� */
    public static final int  RECEIVER_SUBMIT_ANSWER = 10000;
    
    public enum TEST_FLAG{
    	TYPE,CHAPTER ,EXAM
    }
    /**��ϰ��ʶ */
    public static final String TEST_FLAG = "test_flag";
    
    /**������ */
    public static final int TEST_TYPE = 0x1;
    /**���½� */
    public static final int TEST_CHAPTER = 0x2;
    /**������ */
    public static final int TEST_EXAM = 0x3;
    
    /**������Ԥ�� */
    public static final int TEST_EXAM_PREVIEW = 0x1;
    /**��������� */
    public static final int TEST_EXAM_ANSWER = 0x2;
    
    /**����-��ѡ */
    public static final int QUSE_TYPE_SINGLE_SELECT = 0x1;
    /**����-�ж� */
    public static final int QUSE_TYPE_ANSWER = 0x2;
    /**����-�ж� */
    public static final int QUSE_TYPE_JUDGE = 0x3;
    /**����-��� */
    public static final int QUSE_TYPE_FILLBLANK = 0x4;
    /**����-��ѡ */
    public static final int QUSE_TYPE_MULTI_SELECT = 0x5;
    
    /**��ѡ��ID */
    public static final int QUSE_TYPE_SINGLE_SELECT_ID = 151;
    /**��ѡ��ID */
    public static final int QUSE_TYPE_MULTI_SELECT_ID = 152;
    /**�����ID */
    public static final int QUSE_TYPE_FILLBLANK_ID = 153;
    /**�ж���ID */
    public static final int QUSE_TYPE_JUDGE_ID = 154;
    /**�ʴ���ID */
    public static final int QUSE_TYPE_ASK_ID = 155;
    
    
    /**�ղ���Դ������:1����Դ */
    public static final int FAVORITES_RESOURCES = 1;
    /**�ղ���Դ������:2��ͼ�� */
    public static final int FAVORITES_BOOKS = 2;
    /**�ղ���Դ������: 3������ */
    public static final int FAVORITES_QUESTION = 3;
    /**�ղ���Դ������:4���Ծ� */
    public static final int FAVORITES_EXAM_PAPER = 4;
    /**�ղ���Դ������: 5���ĵ� */
    public static final int FAVORITES_DOCUMENT = 5;

    /**�������� */
    public static final String QUSE_ANSWER_DATE = "yyyy��MM��dd��";
    /**����(�������)���� */
    public static final String QUSE_ANSWER_ERR_DATE = "yyyy-M-d";
    /**����ʱ�� */
    public static final String QUSE_ANSWER_TIME= "HH:mm:ss";
    
    /**δ���� */
    public static final int QUSE_ANSWER_NOT_FINISHED= 0x0;
    /**�Ѵ��� */
    public static final int QUSE_ANSWER_FINISHED=0x1;
    
    /**��Դ����*/
//    public static final String RES_URL = "http://125.76.225.237:5080";
//    public static final String RES_URL = "http://123.139.89.90:5080";
    public static final String RES_URL = "http://media.liren-eschoolbag.com";
    
	
	/**��λ���泤��*/
	public static final int SIZE_BUFFER =1024;
	
	/** SD��·�� */
	public final static String PATH_SDCARD= Environment
			.getExternalStorageDirectory().getPath();
	/**
	 * �����ļ��б���·��<br>
	 * "/liren/update/"
	 */
	public static final String UPDATE_PATH= PATH_SDCARD+
			"/liren/update/";
	/**
	 * ����ͼƬ�ļ��б���·��<br>
	 * "/liren/lxpractice/camera/"
	 */
	public static final String CAMERA_PATH= PATH_SDCARD+
			 "/liren/lxpractice/camera/";
	/**
	 * ����/�ݸ�ֽͼƬ�ļ��б���·��<br>
	 * "sd +/liren/questions/{��ĿID}/"
	 */
//	public static final String LXPRACTICE_PATH= PATH_SDCARD+
//			"/liren/lxpractice/";
	public static final String LXPRACTICE_PATH= PATH_SDCARD+
			"/liren/questions/";
	/**��ĿͼƬ����λ��<br>SD+/liren/ques/*/
//	public static final String ERR_QUES_IMG_PATH= PATH_SDCARD+
//			"/liren/ques/";
/**
	 * ��Ƭ����·��<br>
	 * "/sdcard/lxpractice/camera"
	 *//*
	public final static String CAMERA_PATH = PATH_SDCARD
			+ "/lxpractice/camera/";*/
	/**
	 * Log�ļ�����·��<br>
	 * "/sdcard/lxpractice/lxpractice.txt"
	 */
	public final static String LOG_PATH = PATH_SDCARD
			+ "/lxpractice/lxpractice.txt";
	/**
	 * txt�ļ�����·��<br>
	 * "/sdcard/lxpractice/info.txt"
	 */
	public final static String INFO_PATH = PATH_SDCARD + "/lxpractice/info.txt";
	/** �������ͼƬ��ʱ��������ʱ���ʽ�� */
	public final static String FMT_FILENAME = "yyyyMMddkkmmssSSS";
	/** ��ϰ����ʱ���ʽ */
	public final static String FMT_REPORT_DATE ="yyyy-MM-dd HH:mm:ss";

}
