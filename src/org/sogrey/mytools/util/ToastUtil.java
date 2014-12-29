package org.sogrey.mytools.util;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Toast��װ��
 * @author Sogrey
 *
 */
public class ToastUtil {
	   private static Toast toast = null;  
	    public static int LENGTH_LONG = Toast.LENGTH_LONG;  
	    private static int LENGTH_SHORT = Toast.LENGTH_SHORT;  
	      
	    /** 
	     * ��ͨ�ı���Ϣ��ʾ 
	     * @param context 
	     * @param text 
	     * @param duration 
	     * @param gravity λ�ã�Gravity.CENTER��Gravity.BOTTOM��Gravity.TOP��Gravity.LEFT��Gravity.RIGHT
	     */  
	    public static void TextToast(Context context,CharSequence text,int duration,int gravity){  
	        //����һ��Toast��ʾ��Ϣ   
	        toast = Toast.makeText(context, text, duration);  
	        //����Toast��ʾ��Ϣ����Ļ�ϵ�λ��   
	        toast.setGravity(gravity, 0, 0);  
	        //��ʾ��Ϣ   
	        toast.show();  
	    }  
	      
	    /** 
	     * ��ͼƬ��Ϣ��ʾ 
	     * @param context 
	     * @param ImageResourceId 
	     * @param text 
	     * @param duration 
	     * @param gravity λ�ã�Gravity.CENTER��Gravity.BOTTOM��Gravity.TOP��Gravity.LEFT��Gravity.RIGHT
	     */  
	    public static void ImageToast(Context context,int ImageResourceId,CharSequence text,int duration,int gravity){  
	        //����һ��Toast��ʾ��Ϣ   
	        toast = Toast.makeText(context, text, Toast.LENGTH_LONG);  
	        //����Toast��ʾ��Ϣ����Ļ�ϵ�λ��   
	        toast.setGravity(gravity, 0, 0);  
	        //��ȡToast��ʾ��Ϣ��ԭ�е�View   
	        View toastView = toast.getView();  
	        //����һ��ImageView   
	        ImageView img = new ImageView(context);  
	        img.setImageResource(ImageResourceId);  
	        //����һ��LineLayout����   
	        LinearLayout ll = new LinearLayout(context);  
	        //��LinearLayout�����ImageView��Toastԭ�е�View   
	        ll.addView(img);  
	        ll.addView(toastView);  
	        //��LineLayout��������Ϊtoast��View   
	        toast.setView(ll);  
	        //��ʾ��Ϣ   
	        toast.show();  
	    }  
}
