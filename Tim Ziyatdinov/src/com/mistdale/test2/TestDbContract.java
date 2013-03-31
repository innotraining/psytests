package com.mistdale.test2;

import android.provider.BaseColumns;

public final class TestDbContract {
	public static abstract class TableUsers implements BaseColumns {
		 public static final String TABLE_NAME="Users";
		 public static final String COLUMN_NAME_USERNAME="UserName"; // PRIMARY KEY ID
	}
	
	public static abstract class TableResults implements BaseColumns {
		public static final String TABLE_NAME="Results";
		public static final String COLUMN_NAME_DATE="ResultsDate"; // PRIMARY KEY ID
		public static final String COLUMN_NAME_DATE_DAY="ResultsDateWithoutTime";
		public static final String COLUMN_NAME_PARAMS="ResultsParams";
		public static final String COLUMN_NAME_USER="ResultsUser";
	}
}
