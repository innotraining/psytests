package com.congnitive.test.mmpitest.utilities;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.content.Context;

import com.congnitive.test.mmpitest.domainObjects.QuizResult;
import com.congnitive.test.mmpitest.domainObjects.User;

public interface DataBase {
	public UUID saveUser(Context context, User usr);

	public User getUserById(Context context, UUID id);

	public UUID getIdByUser(Context context, User usr);

	public void removeUser(Context context, UUID userId);

	public User[] getAllUsers(Context context);

	public UUID addTestToUser(Context context, UUID userId,
			QuizResult testResults, Date date);

	public Map<Date, List<QuizResult>> getAllTestsOfUser(Context context,
			UUID userId);

	public QuizResult getTestResult(Context context, UUID userId, UUID testId);

	public Date getTestDate(Context context, UUID userId, UUID testId);

}
