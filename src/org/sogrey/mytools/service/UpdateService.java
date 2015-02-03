//package org.sogrey.mytools.service;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import org.sogrey.mytools.R;
//import org.sogrey.mytools.util.Constants;
//import org.sogrey.mytools.util.FileUtil;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.widget.RemoteViews;
//
//
///**
// * �汾�������ط�����
// * @author Administrator
// *
// */
//public class UpdateService extends Service{
//
//	protected static final int DOWN_OK = 200;
//	protected static final int DOWN_ERROR = 400;
//	private String app_name;//Ӧ����
//
//	@Override
//	public IBinder onBind(Intent intent) {
//		return null;
//	}
//
//	@Override  
//    public int onStartCommand(Intent intent, int flags, int startId) {  
//  
//        app_name = intent.getStringExtra("app_name");  
//        // �����ļ�  
//        try {
//			FileUtil.createFile(app_name);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}// �����ļ�  
//        createNotification();// �״δ���  
//        createThread();// �߳�����  
//        return super.onStartCommand(intent, flags, startId);  
//    } 
//	
//    /*** 
//     * ����֪ͨ�� 
//     */  
//    RemoteViews contentView;
//	private NotificationManager notificationManager;
//	private Notification notification;
//	private Intent updateIntent;
//	private PendingIntent pendingIntent;
//	private int TIMEOUT=2000;  
//    public void createNotification() {  
//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);  
//        notification = new Notification();  
//        notification.icon = R.drawable.ic_launcher;// ���ͼ�����Ҫ���ã���Ȼ�����Ǹ�RemoteViews��������.  
//        // ���������֪ͨ��ʾ��������ֵ.  
//        notification.tickerText = "��ʼ����";  
//        //  
//        // updateIntent = new Intent(this, MainActivity.class);  
//        // pendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);  
//        //  
//        // // ������Ĳ�����֪ͨ��view��ʾ������  
//        // notification.setLatestEventInfo(this, app_name, "���أ�0%",  
//        // pendingIntent);  
//        //  
//        // notificationManager.notify(notification_id, notification);  
//  
//        /*** 
//         * �������������Զ���view����ʾNotification 
//         */  
//        contentView = new RemoteViews(getPackageName(),  
//                R.layout.notification_item);  
//        contentView.setTextViewText(R.id.notificationTitle, "��������");  
//        contentView.setTextViewText(R.id.notificationPercent, "0%");  
//        contentView.setProgressBar(R.id.notificationProgress, 100, 0, false);  
//  
//        notification.contentView = contentView;  
//  
//        updateIntent = new Intent(this, MainActivity.class);  
//        updateIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);  
//        pendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);  
//        notification.contentIntent = pendingIntent;  
//        notificationManager.notify(notification_id, notification);  
//    }  
//    
//    /***
//	 * ���߳�����
//	 */
//	public void createThread() {
//		/***
//		 * ����UI
//		 */
//		final Handler handler = new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				switch (msg.what) {
//				case DOWN_OK:
//					// ������ɣ������װ
//					Uri uri = Uri.fromFile(FileUtil.updateFile);
//					Intent intent = new Intent(Intent.ACTION_VIEW);
//					intent.setDataAndType(uri,
//							"application/vnd.android.package-archive");
//
//					pendingIntent = PendingIntent.getActivity(
//							UpdateService.this, 0, intent, 0);
//
//					notification.setLatestEventInfo(UpdateService.this,
//							app_name, "���سɹ��������װ", pendingIntent);
//
//					notificationManager.notify(notification_id, notification);
//
//					stopSelf();
//					break;
//				case DOWN_ERROR:
//					notification.setLatestEventInfo(UpdateService.this,
//							app_name, "����ʧ��", pendingIntent);
//					break;
//
//				default:
//					stopSelf();
//					break;
//				}
//
//			}
//
//		};
//
//		final Message message = new Message();
//
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//
//				try {
//					long downloadSize = downloadUpdateFile(down_url,
//							FileUtil.updateFile.toString());
//					if (downloadSize > 0) {
//						// ���سɹ�
//						message.what = DOWN_OK;
//						handler.sendMessage(message);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					message.what = DOWN_ERROR;
//					handler.sendMessage(message);
//				}
//			}
//		}).start();
//	}
//	
//	/*** 
//     * �����ļ� 
//     *  
//     * @return 
//     * @throws MalformedURLException 
//     */  
//    public long downloadUpdateFile(String down_url, String file)  
//            throws Exception {  
//        int down_step = 5;// ��ʾstep  
//        int totalSize;// �ļ��ܴ�С  
//        int downloadCount = 0;// �Ѿ����غõĴ�С  
//        int updateCount = 0;// �Ѿ��ϴ����ļ���С  
//        InputStream inputStream;  
//        OutputStream outputStream;  
//  
//        URL url = new URL(down_url);  
//        HttpURLConnection httpURLConnection = (HttpURLConnection) url  
//                .openConnection();  
//		httpURLConnection.setConnectTimeout(TIMEOUT);  
//        httpURLConnection.setReadTimeout(TIMEOUT);  
//        // ��ȡ�����ļ���size  
//        totalSize = httpURLConnection.getContentLength();  
//        if (404==httpURLConnection.getResponseCode()) {  
//            throw new Exception("fail!");  
//        }  
//        inputStream = httpURLConnection.getInputStream();  
//        outputStream = new FileOutputStream(file, false);// �ļ������򸲸ǵ�  
//        byte buffer[] = new byte[Constants.SIZE_BUFFER];  
//        int readsize = 0;  
//        while ((readsize = inputStream.read(buffer)) != -1) {  
//            outputStream.write(buffer, 0, readsize);  
//            downloadCount += readsize;// ʱʱ��ȡ���ص��Ĵ�С  
//            /** 
//             * ÿ������5% 
//             */  
//            if (updateCount == 0  
//                    || (downloadCount * 100 / totalSize - down_step) >= updateCount) {  
//                updateCount += down_step;  
//                // �ı�֪ͨ��  
//                // notification.setLatestEventInfo(this, "��������...", updateCount  
//                // + "%" + "", pendingIntent);  
//                contentView.setTextViewText(R.id.notificationPercent,  
//                        updateCount + "%");  
//                contentView.setProgressBar(R.id.notificationProgress, 100,  
//                        updateCount, false);  
//                // show_view  
//                notificationManager.notify(notification_id, notification);  
//            }  
//        }  
//        if (httpURLConnection != null) {  
//            httpURLConnection.disconnect();  
//        }  
//        inputStream.close();  
//        outputStream.close();  
//        return downloadCount;  
//    }
//}
