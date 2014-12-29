package org.sogrey.mytools.util;

import com.ant.liao.GifView;
import com.lr.lxpractice.R;
import com.lr.lxpractice.activity.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * 对话框工具类
 * 
 * @author Administrator extends BaseActivity
 */
public class DialogUtil {

	/** 对话框key */
	public static final String DIALOG_NAME_STRING = "dialog";
	/** 退出提示对话框 */
	public static final int DIALOG_EXIT = 0x400;
	/** 考试退出提示对话框 */
	public static final int DIALOG_EXAM_EXIT = 0x401;
	/** 等待对话框 */
	public static final int DIALOG_WAITING = 0x500;
	/** 提交等待对话框 */
	public static final int DIALOG_COMMIT_WAITING = 0x600;
	/** 提交成功对话框 */
	public static final int DIALOG_COMMIT_SUCCESS = 0x601;
	private static Context mContext;

	public DialogUtil(Context context) {
		mContext = context;
	}

	// @Override
	// protected Dialog onCreateDialog(int id) {
	//
	// switch (id) {
	// case DIALOG_EXIT:// 退出对话框
	// return createExitDialog();
	//
	// default:
	// return null;
	// }
	// }
	//
	// public void showDialogById(int id){
	// showDialog(id);
	// }

	public Dialog createDialog(int id) {

		switch (id) {
		case DIALOG_EXIT:// 退出对话框
			return createExitDialog();
		case DIALOG_EXAM_EXIT:// 考试退出对话框
			return createExamExitDialog();
		case DIALOG_WAITING:// 等待对话框
			return createWaitingDialog();
		case DIALOG_COMMIT_WAITING:// 提交等待对话框
//				Dialog commitWaiting=	createCommitWaitingDialog();
//			Window window = commitWaiting.getWindow();
//	        WindowManager.LayoutParams layoutParams = window.getAttributes();
////	        Display display = ((Activity)mContext).getWindowManager().getDefaultDisplay();
////	        layoutParams.width = display.getWidth()*2/3;
////	        layoutParams.height = display.getHeight()*2/3;
//	        layoutParams.width = 180;
//	        layoutParams.height = 180;
//	        window.setAttributes(layoutParams);
//	        return commitWaiting;
	        return createCommitWaitingDialog();
		case DIALOG_COMMIT_SUCCESS:// 提交成功对话框
			return createCommitSuccessDialog();

		default:
			return null;
		}
	}

	private Dialog createCommitSuccessDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		View view = ((Activity) mContext).getLayoutInflater().inflate(
				R.layout.dialog_commit_success, null);
		builder.setView(view);
		builder.setCancelable(false);
		return builder.create();
	}

	private Dialog createCommitWaitingDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//		ProgressDialog dialog = new ProgressDialog(mContext);
//		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// dialog.setTitle(getString(R.string.title_dialog_waiting));
		View view = ((Activity) mContext).getLayoutInflater().inflate(
				R.layout.dialog_commit_gif, null);
		GifView gif = (GifView)view.findViewById(R.id.gif_commit_loading);
		gif.setGifImage(R.drawable.loading);
//		builder.setMessage(mContext.getString(R.string.hint_dialog_commit_waiting));
		builder.setView(view);
		builder.setCancelable(false);
		return builder.create();
	}

	private Dialog createExamExitDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(R.string.exit);
		builder.setMessage(R.string.exit_sure_exam);
		builder.setCancelable(false);
		builder.setNegativeButton(R.string.no, null);
		builder.setPositiveButton(R.string.yes,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
//						EditApplication.getInstance().endApp();
						Intent intent =new Intent(mContext, MainActivity.class);
						mContext.startActivity(intent);
						((Activity)mContext).finish();
					}
				});
		return builder.create();
	}

	private Dialog createWaitingDialog() {
		ProgressDialog dialog = new ProgressDialog(mContext);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// dialog.setTitle(getString(R.string.title_dialog_waiting));
		dialog.setMessage(mContext.getString(R.string.hint_dialog_waiting));
		dialog.setCancelable(false);
		return dialog;
	}

	private Dialog createExitDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(R.string.exit);
		builder.setMessage(R.string.exit_sure);
		builder.setCancelable(false);
		builder.setNegativeButton(R.string.no, null);
		builder.setPositiveButton(R.string.yes,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditApplication.getInstance().endApp();
					}
				});
		return builder.create();
	}
}
