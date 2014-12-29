package org.sogrey.mytools.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * 提示框公共类
 * @author licheng
 *
 */
public class MyTools {
    static String TAG = "MyTools";
    static SimpleDateFormat ldf;
    /**
     * 获取手机型号
     * @return
     */
    public static String getTelBrand(){
        String bound = "";
        bound = Build.BRAND;
        return bound;
    }
    public static int execRootCmdSilent(String paramString) {
        try {
            Process localProcess = Runtime.getRuntime().exec("su");
            Object localObject = localProcess.getOutputStream();
            DataOutputStream localDataOutputStream = new DataOutputStream(
                    (OutputStream) localObject);
            String str = String.valueOf(paramString);
            localObject = str + "\n";
            localDataOutputStream.writeBytes((String) localObject);
            localDataOutputStream.flush();
            localDataOutputStream.writeBytes("exit\n");
            localDataOutputStream.flush();
            localProcess.waitFor();
            int result = localProcess.exitValue();
            return (Integer) result;
        } catch (Exception localException) {
            localException.printStackTrace();
            return -1;
        }
    }

    public static boolean haveRoot(String cmd) {
        int i = execRootCmdSilent(cmd);
        Log.e(TAG, "result = " + i);
        if (i != -1) {
            return true;
        }
        return false;
    }
   
	/**
	 * 弹出一个提示框
	 * @param context 上下文对象
	 * @param msg 提示信息
	 * @param Btn_text 按钮的text
	 */
	public static void Alert(Context context, int iconId, String tip, String msg, String Btn_text){
		AlertDialog.Builder b = new AlertDialog.Builder(context);
		b.setTitle(tip);
		b.setMessage(msg);
		b.setIcon(iconId);
		b.setPositiveButton(Btn_text, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}
		});
		b.show();
	}
	
	
	/**
	 * ShowToast
	 * @param context
	 * @param showText
	 * @param duration
	 * @param gravity
	 */
	public static void showToast(Context context,String showText, int duration, int gravity){
		Toast t = Toast.makeText(context, showText, duration);
		t.setGravity(gravity, 0, 0);
		t.show();
	}
	
    
    /**
     * 检测网络状态
     * @return true 有可用的网络  false 无可用的网络
     */
 	public static boolean NetWorkStatus(Context context){
 	   	boolean netSataus = false;
 	   	ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
 	   	cwjManager.getActiveNetworkInfo();
 	   	if (cwjManager.getActiveNetworkInfo() != null) {
 	   		netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
 	   	}
 		return netSataus;
 	}
 	
 	/**
 	 * 弹出时间选择框
 	 * @param context 上下文对象
 	 * @param edit 显示时间的EditText
 	 * @return OnClickListener 控件的点击事件
 	 */
 	public static OnClickListener Dia_Time(final Context context, final EditText edit){
 		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				new TimePickerDialog(
		                context,
		                new TimePickerDialog.OnTimeSetListener(){
		                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		                    	String hstr = hourOfDay<10?"0"+hourOfDay:hourOfDay+"";
		                    	String mstr = minute<10?"0"+minute:minute+"";
		                    	edit.setText(hstr+":"+mstr+":00");
		                    }
		                },
		                c.get(Calendar.HOUR_OF_DAY),
		                c.get(Calendar.MINUTE),
		                true
		            ).show();
			}
		};
 	}
 	/**
 	 * 从Drawable转换为Bitmap
 	 * @param d
 	 * @return
 	 */
 	public static Bitmap getBitmapByDrawable(Drawable d){
 		BitmapDrawable bd = (BitmapDrawable) d;
 		return bd.getBitmap();
 	}
 	/**
 	 * 将PNG转换为Base64字符串
 	 * @param b
 	 * @return
 	 */
 	public static String getBase64ByBitMap_PNG(Bitmap b){
 		byte[] bt = Bitmap2Bytes_PNG(b);
 		String str_base64 = Base64.encodeToString(bt, 0);
 		return str_base64;
 	}
 	/**
 	 * 将JPEG转换为Base64字符串
 	 * @param b
 	 * @return
 	 */
 	public static String getBase64ByBitMap_JPEG(Bitmap b){
 		byte[] bt = Bitmap2Bytes_JPEG(b);
 		String str_base64 = Base64.encodeToString(bt, 0);
 		return str_base64;
 	}
 	/**
 	 * 将Base64字符串转换为BitMap
 	 * @param
 	 * @return
 	 */
 	public static Bitmap getBitMapByBase64(String base64Str){
 		byte[] b = Base64.decode(base64Str, 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize=2;//图片高宽度都为原来的二分之一，即图片大小为原来的大小的四分之一
        options.inTempStorage = new byte[5*1024];
        return BitmapFactory.decodeByteArray(b, 0, b.length, options);
 	}
 	/**
 	 * 将Base64字符串转换为BitMap  公式图片  不缩小
 	 * @param
 	 * @return
 	 */
 	public static Bitmap getBitMapByBase64_gs(String base64Str){
 		byte[] b = Base64.decode(base64Str, 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize=1;
        options.inTempStorage = new byte[5*1024];
        return BitmapFactory.decodeByteArray(b, 0, b.length, options);
 	}

 	
 	

	/**
	 * 将bitmap对象转换成byte数组(JPEG)
	 * @param bm
	 * @return
	 */
	public static byte[] Bitmap2Bytes_JPEG(Bitmap bm){
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 bm.compress(Bitmap.CompressFormat.JPEG, 20, baos);
		 return baos.toByteArray();
	}
	/**
	 * 将bitmap对象转换成byte数组(png)
	 * @param bm
	 * @return
	 */
	public static byte[] Bitmap2Bytes_PNG(Bitmap bm){
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 bm.compress(Bitmap.CompressFormat.PNG, 20, baos);
		 return baos.toByteArray();
	}

 	
	/**
	 * 获取CPU序列号(需ROOT权限？)
	 * @return
	 */
	public static String getCPUSerial() {
		String str = "", strCPU = "", cpuAddress = "0000000000000000";
		try {
			// 读取CPU信息
			Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			// 查找CPU序列号
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					// 查找到序列号所在行
					if (str.indexOf("Serial") > -1) {
						// 提取序列号
						strCPU = str.substring(str.indexOf(":") + 1,
								str.length());
						// 去空格
						cpuAddress = strCPU.trim();
						break;
					}
				} else {
					// 文件结尾
					break;
				}
			}
		} catch (IOException ex) {
			// 赋予默认值
			ex.printStackTrace();
		}
		return cpuAddress;
	}
	
	/**
	 * 读取图片
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap readBitMap(Context context, int resId, int SampleSize) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;  
        opt.inInputShareable = true;
        opt.inSampleSize = SampleSize;//原大小1/SampleSize
        // 获取资源图片  
        InputStream is = context.getResources().openRawResource(resId);
        Bitmap b = BitmapFactory.decodeStream(is, null, opt); //此句根据图片大小会有不同的时间
        return b;
    }
	/**
	 * 将view转换为bitmap
	 * @param view
	 * @return
	 */
	public static Bitmap getViewBitmap(View view) {
		view.clearFocus();
		view.setPressed(false);

		boolean willNotCache = view.willNotCacheDrawing();
		view.setWillNotCacheDrawing(false);

		int color = view.getDrawingCacheBackgroundColor();
		view.setDrawingCacheBackgroundColor(0);

		if (color != 0) {
			view.destroyDrawingCache();
		}
		view.buildDrawingCache();
		Bitmap cacheBitmap = view.getDrawingCache();
		if (cacheBitmap == null) {
			return null;
		}

		Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

		view.destroyDrawingCache();
		view.setWillNotCacheDrawing(willNotCache);
		view.setDrawingCacheBackgroundColor(color);

		return bitmap;
	}
	
	/**
	 * 删除SDcard文件
	 * @param dir
	 * @return
	 */
	public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    /**
     * 将一个date转换为长日期格式
     * @param date
     * @return
     */
    public static String getLongDate(Long date){
        ldf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = new Date(date);
        return ldf.format(d);
    }
    /**
     * 将一个date转换为长日期格式
     * @param date
     * @return
     */
    public static String getCurrentDate(Long date){
        ldf = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
        Date d = new Date(date);
        return ldf.format(d);
    }
    
    /**
     * 保存题目图片到SDCARD
     * @param path
     * @param bitName
     * @param mBitmap
     */
	public static void saveBitmapToSDCard(String path, String bitName, Bitmap mBitmap) {
		File f = new File(path);
		try {
			if (f.exists()) {
	    		f.delete();
	    	}
			if(!f.exists()) {
				f.mkdirs();
			}

			f = new File(path+bitName + ".png");
			if(!f.isFile()){
				f.createNewFile();
			}
		} catch (Exception e) {
			Log.e("", "创建文件时出错：" + e.toString());
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 
	 public static String getSDPath(){
	       File sdDir = null;
	       boolean sdCardExist = Environment.getExternalStorageState()  
	                           .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
	       if   (sdCardExist)  
	       {                              
	         sdDir = Environment.getExternalStorageDirectory();//获取跟目录
	      }  
	       return sdDir.toString();
	}
	 
	/**
     * 删除SDCARD文件夹
     */
	public static void delete_directory(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}
		if(file.isDirectory()){
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}
			for (int i = 0; i < childFiles.length; i++) {
				delete_directory(childFiles[i]);
			}
			file.delete();
		}
	}

       /**
        * 	获取当前应用的版本号：
        * @param context
        * @return
        * @throws Exception
        */
	public static String getVersionName(Context context){
		String version = "";
	           // 获取packagemanager的实例
	           PackageManager packageManager = context.getPackageManager();
	           // getPackageName()是你当前类的包名，0代表是获取版本信息
	           PackageInfo packInfo;
			try {
				packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
//			    version = packInfo.versionName;
			    version = packInfo.versionName.replace(".", "");
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
	           return version;
	   }

    /**
     * 关机，前提是手机必须经过root
     * @param context
     */
    public static void shutdown(Context context){
        try {

            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream out = new DataOutputStream(
                    process.getOutputStream());
            out.writeBytes("reboot -p\n");
            out.writeBytes("exit\n");
            out.flush();
        } catch (IOException e) {
            new AlertDialog.Builder(context).setTitle("Error").setMessage(
                    e.getMessage()).setPositiveButton("OK", null).show();
        }
    }

    /**
     * 设置手机是否静音
     * @param context
     * @param isOff
     */
    public static void soundOff(Context context,boolean isOff){
        AudioManager audioManager = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, isOff); //设置是否静音
    }
    
    /**求比值（保留两位有效数字）<br>
     * @param progress 总进度
     * @param totle 总长度
     * @return 返回进度百分值 double（两位小数）*/
    public static double downloadPre(int progress,int totle){
    	double progressDou = 100.0 * progress / totle;
    	progressDou = (double) (Math.round(progressDou * 100) / 100.00);// 保留三位有效数字 
		return progressDou;
    }
    
    /**
     * 将dip转换为px
     * 
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px转换为dip
     * 
     * @param context
     * @param dipValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
    }
    
	
	/**
	 * 时间戳转"yyyy-M-d"格式
	 * @param date long 时间戳
	 * @return String  yyyy-M-d
	 */
	public static String GetStringFromDate(long date){
		/* 日期格式 */
		String patternDate = Constants.QUSE_ANSWER_ERR_DATE;
		/* new 出SimpleDateFormat 对象sdf */
		SimpleDateFormat sdfDate = new SimpleDateFormat(patternDate);
		/* 格式化当前时间赋给 dateString */
		String dateString = sdfDate.format(date);
		return dateString;
	}

	
	/**判断某题是哪个题型<br>依据原题型代码的前三位数字判断是哪一类*/
	public static int whitchType(int  id){
		//取参数前三位判断是哪一类题型
		int type = 0;
		if (id>99) {//保证三位数
			// 最后一位
//			System.out.println(id % 10);

			int m = 1;
			for (int i = 0; i < (int) Math.log10(id)-2; i++) {
				m *= 10;
			}
			// 第一位
//			System.out.println(id / m);
			type = id / m;
		}
		return type;
	}
	/**检查某个元素是数组中的第几个（从0开始）*/
	public static String indexInArr(String[] arr, int d,boolean isNext) {
		int index = 0;
		String result ="";
		for (int i = 0; i < arr.length; i++) {
			if (d==Integer.parseInt(arr[i])) {
				index=i;
			}
		}
		if (isNext) {//下一题
			if (index!=arr.length-1) {//不是最后一个
				result=arr[index+1];
			}else{//最后一个
				result=null;
			}
		}else{//上一题
			if (index!=0) {//不是第一个
				result=arr[index-1];
			}else{//第一个
				result=null;
			}
		}
		return result;
		}
	
	/** 图文混排 */
	public static void showTxtImg(Context context,TextView tv,int mQuesId, String str) {
		String title = "";
		tv.setText(title);
		ArrayList<String> imgs = new ArrayList<String>();
		Map<String, ArrayList<String>> map = patternPic(str);
		if (null != map) {// 图文混排
			for (Map.Entry entry : map.entrySet()) {
				System.out.println("key= " + entry.getKey() + "\nvalue= "
						+ entry.getValue());
				title = (String) (entry.getKey());
				imgs.addAll((ArrayList<String>) entry.getValue());
			}
			if (title.endsWith("#**#")) {
				title+=".";
			}
			String[] questionTitle = title.split("#\\*\\*#");
			for (int i = 0; i < questionTitle.length; i++) {
				String titleAll = "";
				titleAll += questionTitle[i];
				if ( i < questionTitle.length - 1) {
					String imgPath = imgs.get(i);
					if (imgPath.endsWith(",")) {
						imgPath = imgPath.replaceAll(",", "");
					}
					titleAll += " <img src=\"" +imgPath + "\"/>";
//					titleAll += " <img src=\"" + imgs.get(i) + "\"/>";
//					titleAll += "<img src=\""+imgs.get(i)+"\""+"  alt=\"img\"/>";
				}
				System.out.println(titleAll);
				TextViewShowHTML TxtHtml = new TextViewShowHTML(context, mQuesId);
//				tv.append(Html.fromHtml(titleAll, imgGetter2, null));
				tv.append(Html.fromHtml(titleAll, TxtHtml.imageGetterNet, null));
			}
		} else {// 只是单纯文字
			if (null!=str) {
				tv.append(str);
			}
		}
	}

	/** 处理含有图片资源路径的文本，管理图片位置占位符，管理图片资源URL */
	public static Map<String, ArrayList<String>> patternPic(String str) {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		if (null==str) {
			return null;
		}
		String[] arr = str.split("@");
		if (1 == arr.length) {// 没有图片资源
			return null;
		}
		// for (int i = 0; i < arr.length; i++) {
		// System.out.println("arr[" + i + "]=" + arr[i]);
		// }
		// arr[1]掐头去尾
		arr[1] = arr[1].replace("{", "");
		arr[1] = arr[1].replace("}", "");
		// System.out.println("arr[1]==" + arr[1]);
		ArrayList<String> listImgs = new ArrayList<String>();
		if (!isSDResourse(arr[1])) {
			String[] arr2 = arr[1].split("/upload/");
			for (int i = 0; i < arr2.length; i++) {
				if (!TextUtils.isEmpty(arr2[i])) {
					listImgs.add(Constants.RES_URL + "/upload/" + arr2[i]);
				}
			}
		} else {// SD卡上的资源
			String[] arr2 = arr[1].split("/liren/");
			for (int i = 0; i < arr2.length; i++) {
				if (!TextUtils.isEmpty(arr2[i])) {
					listImgs.add(Constants.PATH_SDCARD + "/liren/" + arr2[i]);
				}
			}
		}
		// System.out.println(listImgs.toString());

		String pat = "\\{\\d+\\}";
		Pattern pattern1 = Pattern.compile(pat);
		Matcher matcher1 = pattern1.matcher(arr[0]);
		arr[0] = matcher1.replaceAll("#**#");
		// System.out.println(arr[0]);
		map.put(arr[0], listImgs);
		return map;
	}

	/** 加载图片 */
	public static ImageGetter imgGetter2 = new Html.ImageGetter() {
		@Override
		public Drawable getDrawable(String source) {
			Drawable drawable = null;
			URL url;
			if (!isSDResourse(source)) {// 不是Sd卡上的
				
//				Log.i("DEBUG", source+" Do not eixts");
//                //启动新线程下载
//                downLoadUtils.download(source, path+String.valueOf(source.hashCode()));
//                return drawable;
                //===================
					try {
						if (!isBmp(source)) {// 不是bmp图像
						url = new URL(source);
						InputStream is = url.openStream();
						drawable = Drawable.createFromStream(is, "");
						} else {// 是bmp图像
							Bitmap bmp = getHttpBitmap(source);
//							Bitmap bmp = loadImageFromUrl(source);
							BitmapDrawable bd = new BitmapDrawable(bmp);
							if (null!=bd) {
								Thread.sleep(3000);
							bd.setBounds(0, 0, bd.getIntrinsicWidth(),
									bd.getIntrinsicHeight());
							return bd;
							}else return null;
						}
					} catch (Exception e) {
						return null;
					}
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight());
					return drawable;
			} else {// SD卡上的图片
				Bitmap bitmap = getLoacalBitmap(source); // 从本地取图片(在sdcard中获取)
				BitmapDrawable bd = new BitmapDrawable(bitmap);
				bd.setBounds(0, 0, bd.getIntrinsicWidth(),
						bd.getIntrinsicHeight());
				return bd;
			}
		}
	};

	private static boolean isBmp(String source) {
		boolean result = false;
		String[] arr = source.split("\\.");

		if (arr[arr.length - 1].indexOf("bmp") != -1
				|| arr[arr.length - 1].indexOf("BMP") != -1) {
			result = true;
		}
		return result;
	}
	

	/** 判断某个资源路径是不是SD卡上的资源，true为 sd卡上的资源，false不是 */
	private static boolean isSDResourse(String source) {
		boolean result = false;

		if (source.indexOf("/liren/") != -1) {// 包含"/upload/"
												// 说明是网络资源，包含"/liren/"是本地资源
			result = true;
		}
		return result;
	}
	

	/**
	 * 加载本地图片
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** 读取网络图片 */
	public static Bitmap loadImageFromUrl(String urlStr) {
		URL url;
		InputStream i = null;
		Bitmap bmp=null;
		try {
			url = new URL(urlStr);
			i = (InputStream) url.getContent();
			bmp = BitmapFactory.decodeStream(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bmp;
	}
	

    /**
    * 从服务器取图片
    * @param url
    * @return
    */
    public static Bitmap getHttpBitmap(String url) {
         URL myFileUrl = null;
         Bitmap bitmap = null;
         try {
              myFileUrl = new URL(url);
         } catch (MalformedURLException e) {
              e.printStackTrace();
         }
         try {
              HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
              conn.setConnectTimeout(3000);
              conn.setDoInput(true);
              conn.connect();
              InputStream is = conn.getInputStream();
              bitmap = BitmapFactory.decodeStream(is);
              is.close();
         } catch (IOException e) {
              e.printStackTrace();
         }
         return bitmap;
    }

  public static boolean  isInArray(String[] array,String arg){
	  for (int i = 0; i < array.length; i++) {
		if (arg.equals(array[i])) {
			return true;
		}
	}
	return false;
  }
  
  
  
  
	/**
	 * MD5 转码
	 * 
	 * @param rawString
	 *            需要转妈的字符串
	 * */
	public static String generateName(String rawString) {
		try {
			// 得到MD5算法引用
			MessageDigest md5 = MessageDigest.getInstance("md5");
			// update 设置计算数据源
			md5.update(rawString.getBytes());
			// 得到计算结果，16进制数
			return toHexString(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "default";
	}
	public static final byte[] HEX_CHAR = "0123456789ABCDEF".getBytes();
	/**
	 * @param digest
	 *            需要转换的字符串
	 * @return 转换结果，字符串
	 */
	private static String toHexString(byte[] digest) {
		StringBuilder sb = new StringBuilder(digest.length * 2);
		for (int i = 0; i < digest.length; i++) {
			sb.append(HEX_CHAR[(digest[i] & 0xf0) >>> 4]);
			sb.append(HEX_CHAR[digest[i] & 0x0f]);
		}
		return sb.toString();
	}
}
