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
 * ��ʾ�򹫹���
 * @author licheng
 *
 */
public class MyTools {
    static String TAG = "MyTools";
    static SimpleDateFormat ldf;
    /**
     * ��ȡ�ֻ��ͺ�
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
	 * ����һ����ʾ��
	 * @param context �����Ķ���
	 * @param msg ��ʾ��Ϣ
	 * @param Btn_text ��ť��text
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
     * �������״̬
     * @return true �п��õ�����  false �޿��õ�����
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
 	 * ����ʱ��ѡ���
 	 * @param context �����Ķ���
 	 * @param edit ��ʾʱ���EditText
 	 * @return OnClickListener �ؼ��ĵ���¼�
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
 	 * ��Drawableת��ΪBitmap
 	 * @param d
 	 * @return
 	 */
 	public static Bitmap getBitmapByDrawable(Drawable d){
 		BitmapDrawable bd = (BitmapDrawable) d;
 		return bd.getBitmap();
 	}
 	/**
 	 * ��PNGת��ΪBase64�ַ���
 	 * @param b
 	 * @return
 	 */
 	public static String getBase64ByBitMap_PNG(Bitmap b){
 		byte[] bt = Bitmap2Bytes_PNG(b);
 		String str_base64 = Base64.encodeToString(bt, 0);
 		return str_base64;
 	}
 	/**
 	 * ��JPEGת��ΪBase64�ַ���
 	 * @param b
 	 * @return
 	 */
 	public static String getBase64ByBitMap_JPEG(Bitmap b){
 		byte[] bt = Bitmap2Bytes_JPEG(b);
 		String str_base64 = Base64.encodeToString(bt, 0);
 		return str_base64;
 	}
 	/**
 	 * ��Base64�ַ���ת��ΪBitMap
 	 * @param
 	 * @return
 	 */
 	public static Bitmap getBitMapByBase64(String base64Str){
 		byte[] b = Base64.decode(base64Str, 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize=2;//ͼƬ�߿�ȶ�Ϊԭ���Ķ���֮һ����ͼƬ��СΪԭ���Ĵ�С���ķ�֮һ
        options.inTempStorage = new byte[5*1024];
        return BitmapFactory.decodeByteArray(b, 0, b.length, options);
 	}
 	/**
 	 * ��Base64�ַ���ת��ΪBitMap  ��ʽͼƬ  ����С
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
	 * ��bitmap����ת����byte����(JPEG)
	 * @param bm
	 * @return
	 */
	public static byte[] Bitmap2Bytes_JPEG(Bitmap bm){
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 bm.compress(Bitmap.CompressFormat.JPEG, 20, baos);
		 return baos.toByteArray();
	}
	/**
	 * ��bitmap����ת����byte����(png)
	 * @param bm
	 * @return
	 */
	public static byte[] Bitmap2Bytes_PNG(Bitmap bm){
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 bm.compress(Bitmap.CompressFormat.PNG, 20, baos);
		 return baos.toByteArray();
	}

 	
	/**
	 * ��ȡCPU���к�(��ROOTȨ�ޣ�)
	 * @return
	 */
	public static String getCPUSerial() {
		String str = "", strCPU = "", cpuAddress = "0000000000000000";
		try {
			// ��ȡCPU��Ϣ
			Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			// ����CPU���к�
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					// ���ҵ����к�������
					if (str.indexOf("Serial") > -1) {
						// ��ȡ���к�
						strCPU = str.substring(str.indexOf(":") + 1,
								str.length());
						// ȥ�ո�
						cpuAddress = strCPU.trim();
						break;
					}
				} else {
					// �ļ���β
					break;
				}
			}
		} catch (IOException ex) {
			// ����Ĭ��ֵ
			ex.printStackTrace();
		}
		return cpuAddress;
	}
	
	/**
	 * ��ȡͼƬ
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap readBitMap(Context context, int resId, int SampleSize) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;  
        opt.inInputShareable = true;
        opt.inSampleSize = SampleSize;//ԭ��С1/SampleSize
        // ��ȡ��ԴͼƬ  
        InputStream is = context.getResources().openRawResource(resId);
        Bitmap b = BitmapFactory.decodeStream(is, null, opt); //�˾����ͼƬ��С���в�ͬ��ʱ��
        return b;
    }
	/**
	 * ��viewת��Ϊbitmap
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
	 * ɾ��SDcard�ļ�
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
     * ��һ��dateת��Ϊ�����ڸ�ʽ
     * @param date
     * @return
     */
    public static String getLongDate(Long date){
        ldf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = new Date(date);
        return ldf.format(d);
    }
    /**
     * ��һ��dateת��Ϊ�����ڸ�ʽ
     * @param date
     * @return
     */
    public static String getCurrentDate(Long date){
        ldf = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
        Date d = new Date(date);
        return ldf.format(d);
    }
    
    /**
     * ������ĿͼƬ��SDCARD
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
			Log.e("", "�����ļ�ʱ����" + e.toString());
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
	                           .equals(Environment.MEDIA_MOUNTED);   //�ж�sd���Ƿ����
	       if   (sdCardExist)  
	       {                              
	         sdDir = Environment.getExternalStorageDirectory();//��ȡ��Ŀ¼
	      }  
	       return sdDir.toString();
	}
	 
	/**
     * ɾ��SDCARD�ļ���
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
        * 	��ȡ��ǰӦ�õİ汾�ţ�
        * @param context
        * @return
        * @throws Exception
        */
	public static String getVersionName(Context context){
		String version = "";
	           // ��ȡpackagemanager��ʵ��
	           PackageManager packageManager = context.getPackageManager();
	           // getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ
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
     * �ػ���ǰ�����ֻ����뾭��root
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
     * �����ֻ��Ƿ���
     * @param context
     * @param isOff
     */
    public static void soundOff(Context context,boolean isOff){
        AudioManager audioManager = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, isOff); //�����Ƿ���
    }
    
    /**���ֵ��������λ��Ч���֣�<br>
     * @param progress �ܽ���
     * @param totle �ܳ���
     * @return ���ؽ��Ȱٷ�ֵ double����λС����*/
    public static double downloadPre(int progress,int totle){
    	double progressDou = 100.0 * progress / totle;
    	progressDou = (double) (Math.round(progressDou * 100) / 100.00);// ������λ��Ч���� 
		return progressDou;
    }
    
    /**
     * ��dipת��Ϊpx
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
     * ��pxת��Ϊdip
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
	 * ʱ���ת"yyyy-M-d"��ʽ
	 * @param date long ʱ���
	 * @return String  yyyy-M-d
	 */
	public static String GetStringFromDate(long date){
		/* ���ڸ�ʽ */
		String patternDate = Constants.QUSE_ANSWER_ERR_DATE;
		/* new ��SimpleDateFormat ����sdf */
		SimpleDateFormat sdfDate = new SimpleDateFormat(patternDate);
		/* ��ʽ����ǰʱ�丳�� dateString */
		String dateString = sdfDate.format(date);
		return dateString;
	}

	
	/**�ж�ĳ�����ĸ�����<br>����ԭ���ʹ����ǰ��λ�����ж�����һ��*/
	public static int whitchType(int  id){
		//ȡ����ǰ��λ�ж�����һ������
		int type = 0;
		if (id>99) {//��֤��λ��
			// ���һλ
//			System.out.println(id % 10);

			int m = 1;
			for (int i = 0; i < (int) Math.log10(id)-2; i++) {
				m *= 10;
			}
			// ��һλ
//			System.out.println(id / m);
			type = id / m;
		}
		return type;
	}
	/**���ĳ��Ԫ���������еĵڼ�������0��ʼ��*/
	public static String indexInArr(String[] arr, int d,boolean isNext) {
		int index = 0;
		String result ="";
		for (int i = 0; i < arr.length; i++) {
			if (d==Integer.parseInt(arr[i])) {
				index=i;
			}
		}
		if (isNext) {//��һ��
			if (index!=arr.length-1) {//�������һ��
				result=arr[index+1];
			}else{//���һ��
				result=null;
			}
		}else{//��һ��
			if (index!=0) {//���ǵ�һ��
				result=arr[index-1];
			}else{//��һ��
				result=null;
			}
		}
		return result;
		}
	
	/** ͼ�Ļ��� */
	public static void showTxtImg(Context context,TextView tv,int mQuesId, String str) {
		String title = "";
		tv.setText(title);
		ArrayList<String> imgs = new ArrayList<String>();
		Map<String, ArrayList<String>> map = patternPic(str);
		if (null != map) {// ͼ�Ļ���
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
		} else {// ֻ�ǵ�������
			if (null!=str) {
				tv.append(str);
			}
		}
	}

	/** ������ͼƬ��Դ·�����ı�������ͼƬλ��ռλ��������ͼƬ��ԴURL */
	public static Map<String, ArrayList<String>> patternPic(String str) {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		if (null==str) {
			return null;
		}
		String[] arr = str.split("@");
		if (1 == arr.length) {// û��ͼƬ��Դ
			return null;
		}
		// for (int i = 0; i < arr.length; i++) {
		// System.out.println("arr[" + i + "]=" + arr[i]);
		// }
		// arr[1]��ͷȥβ
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
		} else {// SD���ϵ���Դ
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

	/** ����ͼƬ */
	public static ImageGetter imgGetter2 = new Html.ImageGetter() {
		@Override
		public Drawable getDrawable(String source) {
			Drawable drawable = null;
			URL url;
			if (!isSDResourse(source)) {// ����Sd���ϵ�
				
//				Log.i("DEBUG", source+" Do not eixts");
//                //�������߳�����
//                downLoadUtils.download(source, path+String.valueOf(source.hashCode()));
//                return drawable;
                //===================
					try {
						if (!isBmp(source)) {// ����bmpͼ��
						url = new URL(source);
						InputStream is = url.openStream();
						drawable = Drawable.createFromStream(is, "");
						} else {// ��bmpͼ��
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
			} else {// SD���ϵ�ͼƬ
				Bitmap bitmap = getLoacalBitmap(source); // �ӱ���ȡͼƬ(��sdcard�л�ȡ)
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
	

	/** �ж�ĳ����Դ·���ǲ���SD���ϵ���Դ��trueΪ sd���ϵ���Դ��false���� */
	private static boolean isSDResourse(String source) {
		boolean result = false;

		if (source.indexOf("/liren/") != -1) {// ����"/upload/"
												// ˵����������Դ������"/liren/"�Ǳ�����Դ
			result = true;
		}
		return result;
	}
	

	/**
	 * ���ر���ͼƬ
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis); // /����ת��ΪBitmapͼƬ

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** ��ȡ����ͼƬ */
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
    * �ӷ�����ȡͼƬ
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
	 * MD5 ת��
	 * 
	 * @param rawString
	 *            ��Ҫת����ַ���
	 * */
	public static String generateName(String rawString) {
		try {
			// �õ�MD5�㷨����
			MessageDigest md5 = MessageDigest.getInstance("md5");
			// update ���ü�������Դ
			md5.update(rawString.getBytes());
			// �õ���������16������
			return toHexString(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "default";
	}
	public static final byte[] HEX_CHAR = "0123456789ABCDEF".getBytes();
	/**
	 * @param digest
	 *            ��Ҫת�����ַ���
	 * @return ת��������ַ���
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
