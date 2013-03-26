package ru.yandex.quiz.mkhaikin.frontend;

import ru.yandex.quiz.mkhaikin.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DialogWrapper {
	
	private AlertDialog.Builder builder;
	
	public DialogWrapper(final Activity activity, int messageId, final Runnable onYes, final Runnable onNo){
		builder = new AlertDialog.Builder(activity);
		
		builder.setMessage(messageId);
		
		builder.setPositiveButton(R.string.Yes_Button, 
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				onYes.run();
			}
		});
		builder.setNegativeButton(R.string.No_Button, 
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				onNo.run();
			}
		});
	}
	public void show() {
		builder.show();
	}
}
