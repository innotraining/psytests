package com.vk.hpotter.gottshaldtquiz.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;


public class SimpleListDialog {
	private AlertDialog.Builder builder;
	
	public SimpleListDialog(Activity activity, @SuppressWarnings("rawtypes") ArrayAdapter adapter, DialogInterface.OnClickListener listener) {
		builder = new AlertDialog.Builder(activity);
		builder.setSingleChoiceItems(adapter, 0, listener);
	}
	
	public void show() {
		builder.show();
	}
}
