package com.congnitive.test.mmpitest.utilities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import android.util.Pair;

import com.congnitive.test.mmpitest.domainObjects.User;

public interface DataBase {
	public int saveUser(User usr);

	public User getUserById(int id);

	public int getIdByName(String userName);

	public User getUserbyName(String userName);

	public void removeUser(int userId);

	public User[] getAllUsers();

	public int addTestToUser(int userId,
			List<Pair<String, Integer>> testResults, Date date);

	public Map<Date, List<List<Pair<String, Integer>>>> getAllTestsOfUser(
			int userId);

	public List<Pair<String, Integer>> getTestResult(int userId, int testId);

	public Date getTestDate(int userId, int testId);

}
