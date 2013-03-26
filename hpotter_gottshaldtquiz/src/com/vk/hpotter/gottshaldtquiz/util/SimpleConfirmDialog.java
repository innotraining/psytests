package com.vk.hpotter.gottshaldtquiz.util;

import com.vk.hpotter.gottshaldtquiz.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class SimpleConfirmDialog {

//	private Context context;
	private AlertDialog.Builder builder;
	
	public SimpleConfirmDialog(Activity activity, int messageId, final Runnable yes, final Runnable no) {
		builder = new AlertDialog.Builder(activity);
		builder.setMessage(messageId)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								yes.run();
							}
						})
				.setNegativeButton(R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
								no.run();
							}
						});
	}
	
	public SimpleConfirmDialog(Activity activity, CharSequence message, final Runnable yes, final Runnable no) {
		builder = new AlertDialog.Builder(activity);
		builder.setMessage(message)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
//								activity.finish();
								yes.run();
							}
						})
				.setNegativeButton(R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
								no.run();
							}
						});
	}
	
	public void show() {
		builder.show();
	}
}
