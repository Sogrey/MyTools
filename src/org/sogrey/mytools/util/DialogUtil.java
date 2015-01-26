package org.sogrey.mytools.util;

import org.sogrey.mytools.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * 对话框工具类
 * 
 * @author Administrator 
 */
public class DialogUtil {

	/** 对话框key */
	public static final String DIALOG_NAME_STRING = "dialog";
	/** 退出提示对话框 */
	public static final int DIALOG_EXIT = 0x400;
	/** 等待对话框 */
	public static final int DIALOG_WAITING = 0x500;
	private static Context mContext;

	public DialogUtil(Context context) {
		mContext = context;
	}

	public Dialog createDialog(int id) {

		switch (id) {
		case DIALOG_EXIT:// 退出对话框
			return createExitDialog();
		case DIALOG_WAITING:// 等待对话框
			return createWaitingDialog();

		default:
			return null;
		}
	}

	private Dialog createWaitingDialog() {
		ProgressDialog dialog = new ProgressDialog(mContext);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// dialog.setTitle(getString(R.string.title_dialog_waiting));
		dialog.setMessage("请等待");
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
//						EditApplication.getInstance().endApp();
					}
				});
		return builder.create();
	}
}
