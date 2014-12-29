package org.sogrey.mytools.util;

import java.io.File;

import org.sogrey.mytools.download.AsyncImageLoader;
import org.sogrey.mytools.download.AsyncImageLoader.ImageCallBack;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;

/**
 * 让TextView显示html
 * 
 * @author Administrator
 * 
 */
public class TextViewShowHTML {
	 private Context mContext;
//	 private mAsyncImageLoader mAsyncImageLoader;
	 private AsyncImageLoader mAsyncImageLoader;
	int  mQuesId;
	 public TextViewShowHTML(Context context,int id){
	 mContext = context;
	 mQuesId= id;
	 //初始化下载类
	 mAsyncImageLoader=new AsyncImageLoader();
//     //设置下载类监听事件
//     mAsyncImageLoader.setOnDownloadListener(onDownloadListener);
	 }

	// 第一种：本地图片

//	final String sText2 = "测试图片信息：<img src=\"/mnt/sdcard/temp/1.jpg\" />";
	// tView.setText(Html.fromHtml(sText2, imageGetter, null));

	public final Html.ImageGetter imageGetterSD = new Html.ImageGetter() {

		public Drawable getDrawable(String source) {
			Drawable drawable = null;
			drawable = Drawable.createFromPath(source);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		};
	};

	// 第二种：项目资源图片

	// final String sText1 =
	// "测试图片信息：<img src=\""+R.drawable.market_none_image+"\" />";tView.setText(Html.fromHtml(sText1,
	// imageGetter, null));

	public final Html.ImageGetter imageGetterR = new Html.ImageGetter() {

		public Drawable getDrawable(String source) {
			Drawable drawable = null;
			int rId = Integer.parseInt(source);
			drawable = mContext.getResources().getDrawable(rId);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		};
	};

	// 第三种：网络图片

	// final String sText =
	// "测试图片信息：<br><img src=\"http://pic004.cnblogs.com/news/201211/20121108_091749_1.jpg\" />";
	// tView.setText(Html.fromHtml(sText, imageGetter, null));

	Drawable drawableNet = null;
	public   final Html.ImageGetter imageGetterNet = new Html.ImageGetter() {
	        public Drawable getDrawable(String source) {
	            Drawable drawable = null;
	            String name = MyTools.generateName(source);
	            String fileString = Constants.LXPRACTICE_PATH+mQuesId+"/"+name;
//	            String fileString=Constants.ERR_QUES_PATH+String.valueOf(source.hashCode());//保存文件的路径
	            Log.i("DEBUG", fileString+"");
	            Log.i("DEBUG", source+"");
	            //判断SD卡里面是否存在图片文件
	            if (new File(fileString).exists()) {
	                Log.i("DEBUG", fileString+"  eixts");
	                //获取本地文件返回Drawable
	                drawable=Drawable.createFromPath(fileString);
	                //设置图片边界
	                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
	                return drawable;
	            }else {
	                LogWrapper.i("DEBUG", fileString+" Do not eixts");
	                //启动新线程下载
//	                mAsyncImageLoader.download(source, Constants.ERR_QUES_PATH+String.valueOf(source.hashCode()));
//	                mAsyncImageLoader.Download(source.hashCode(), source, Constants.ERR_QUES_PATH+String.valueOf(source.hashCode()));
	                mAsyncImageLoader.LoadImage(source,mQuesId, new ImageCallBack() {

						@Override
						public void onImageLoaded(String url, Drawable d) {
							drawableNet = d;
							//XXX  通知不到
							 Intent intent = new Intent();
				        		intent.setAction(Constants.RECEIVER_GOT_QUES_IMG);// action与接收器相同,通知Mainactivity
				        		mContext.sendBroadcast(intent);
						}
					});
	                return drawableNet;
	            }
	        };
	    };
}
