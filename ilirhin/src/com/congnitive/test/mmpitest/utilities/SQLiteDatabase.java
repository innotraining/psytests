package com.congnitive.test.mmpitest.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import biz.source_code.base64Coder.Base64Coder;

import com.congnitive.test.mmpitest.domainObjects.QuizResult;
import com.congnitive.test.mmpitest.domainObjects.User;

public class SQLiteDatabase implements DataBase {
	private Object deserializeObject(String s) {
		byte[] data = Base64Coder.decode(s);
		Object result = null;

		try {
			ObjectInputStream ois = new ObjectInputStream(
					new ByteArrayInputStream(data));
			result = ois.readObject();
			ois.close();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	private String serialaizeObject(Serializable o) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64Coder.encode(baos.toByteArray()));
	}

	@Override
	public int saveUser(User usr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIdByName(String userName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUserbyName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(int userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public User[] getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addTestToUser(int userId, QuizResult testResults, Date date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Date, List<QuizResult>> getAllTestsOfUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuizResult getTestResult(int userId, int testId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getTestDate(int userId, int testId) {
		// TODO Auto-generated method stub
		return null;
	}

}
