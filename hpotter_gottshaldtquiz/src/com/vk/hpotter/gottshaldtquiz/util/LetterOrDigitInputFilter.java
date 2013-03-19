package com.vk.hpotter.gottshaldtquiz.util;

import android.text.InputFilter;
import android.text.Spanned;

public class LetterOrDigitInputFilter implements InputFilter {

	@Override
	public CharSequence filter(CharSequence source, int start, int end,
			Spanned dest, int dstart, int dend) {
		for (int i = start; i < end; i++) {
			if (!Character.isLetterOrDigit(source.charAt(i))) {
				return "";
			}
		}
		return null;
	}

}
