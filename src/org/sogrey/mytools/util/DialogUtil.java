package org.sogrey.mytools.util;

import org.sogrey.mytools.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * �Ի��򹤾���
 * 
 * @author Administrator 
 */
public class DialogUtil {

	/** �Ի���key */
	public static final String DIALOG_NAME_STRING = "dialog";
	/** �˳���ʾ�Ի��� */
	public static final int DIALOG_EXIT = 0x400;
	/** �ȴ��Ի��� */
	public static final int DIALOG_WAITING = 0x500;
	private static Context mContext;

	public DialogUtil(Context context) {
		mContext = context;
	}

	public Dialog createDialog(int id) {

		switch (id) {
		case DIALOG_EXIT:// �˳��Ի���
			return createExitDialog();
		case DIALOG_WAITING:// �ȴ��Ի���
			return createWaitingDialog();

		default:
			return null;
		}
	}

	private Dialog createWaitingDialog() {
		ProgressDialog dialog = new ProgressDialog(mContext);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// dialog.setTitle(getString(R.string.title_dialog_waiting));
		dialog.setMessage("��ȴ�");
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
