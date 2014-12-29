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
 * ��TextView��ʾhtml
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
	 //��ʼ��������
	 mAsyncImageLoader=new AsyncImageLoader();
//     //��������������¼�
//     mAsyncImageLoader.setOnDownloadListener(onDownloadListener);
	 }

	// ��һ�֣�����ͼƬ

//	final String sText2 = "����ͼƬ��Ϣ��<img src=\"/mnt/sdcard/temp/1.jpg\" />";
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

	// �ڶ��֣���Ŀ��ԴͼƬ

	// final String sText1 =
	// "����ͼƬ��Ϣ��<img src=\""+R.drawable.market_none_image+"\" />";tView.setText(Html.fromHtml(sText1,
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

	// �����֣�����ͼƬ

	// final String sText =
	// "����ͼƬ��Ϣ��<br><img src=\"http://pic004.cnblogs.com/news/201211/20121108_091749_1.jpg\" />";
	// tView.setText(Html.fromHtml(sText, imageGetter, null));

	Drawable drawableNet = null;
	public   final Html.ImageGetter imageGetterNet = new Html.ImageGetter() {
	        public Drawable getDrawable(String source) {
	            Drawable drawable = null;
	            String name = MyTools.generateName(source);
	            String fileString = Constants.LXPRACTICE_PATH+mQuesId+"/"+name;
//	            String fileString=Constants.ERR_QUES_PATH+String.valueOf(source.hashCode());//�����ļ���·��
	            Log.i("DEBUG", fileString+"");
	            Log.i("DEBUG", source+"");
	            //�ж�SD�������Ƿ����ͼƬ�ļ�
	            if (new File(fileString).exists()) {
	                Log.i("DEBUG", fileString+"  eixts");
	                //��ȡ�����ļ�����Drawable
	                drawable=Drawable.createFromPath(fileString);
	                //����ͼƬ�߽�
	                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
	                return drawable;
	            }else {
	                LogWrapper.i("DEBUG", fileString+" Do not eixts");
	                //�������߳�����
//	                mAsyncImageLoader.download(source, Constants.ERR_QUES_PATH+String.valueOf(source.hashCode()));
//	                mAsyncImageLoader.Download(source.hashCode(), source, Constants.ERR_QUES_PATH+String.valueOf(source.hashCode()));
	                mAsyncImageLoader.LoadImage(source,mQuesId, new ImageCallBack() {

						@Override
						public void onImageLoaded(String url, Drawable d) {
							drawableNet = d;
							//XXX  ֪ͨ����
							 Intent intent = new Intent();
				        		intent.setAction(Constants.RECEIVER_GOT_QUES_IMG);// action���������ͬ,֪ͨMainactivity
				        		mContext.sendBroadcast(intent);
						}
					});
	                return drawableNet;
	            }
	        };
	    };
}
