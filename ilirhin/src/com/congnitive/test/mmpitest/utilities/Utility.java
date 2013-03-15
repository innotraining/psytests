package com.congnitive.test.mmpitest.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.content.res.Resources;

import com.congnitive.test.mmpitest.domainObjects.PsychologyTextedTest;
import com.congnitive.test.mmpitest.domainObjects.QuizResult;
import com.congnitive.test.mmpitest.domainObjects.User;

public class Utility {
	static public DataBase database = new RAMDatabase();

	static public PsychologyTextedTest getTest(boolean gender, Resources res) {
		return new MMPITest(gender, res);
	}

	static public final String USER_ID_TAG = "Utility.userIdTag";
}

class RAMDatabase implements DataBase {
	private Map<Integer, User> map = new TreeMap<Integer, User>();
	private Map<String, Integer> mapId = new TreeMap<String, Integer>();
	private Map<String, User> mapNames = new TreeMap<String, User>();
	private Map<Integer, Map<Date, List<QuizResult>>> usrTests = new TreeMap<Integer, Map<Date, List<QuizResult>>>();
	private int amount = 0;

	public RAMDatabase() {
		saveUser(new User("Rurik", true));
		saveUser(new User("Varvara", false));
	}

	@Override
	public int saveUser(User usr) {
		if (!map.containsValue(usr)) {
			map.put(amount, usr);
			mapNames.put(usr.getName(), usr);
			mapId.put(usr.getName(), amount);
			return amount++;
		} else {
			return -1;
		}
	}

	@Override
	public User getUserById(int id) {
		return map.get(id);
	}

	@Override
	public void removeUser(int id) {
		User usr = getUserById(id);
		if (usr != null) {
			map.remove(id);
			mapNames.remove(usr.getName());
			mapId.remove(usr.getName());
		}
	}

	@Override
	public User[] getAllUsers() {
		Collection<User> tempCol = map.values();
		return tempCol.toArray(new User[tempCol.size()]);
	}

	@Override
	public int addTestToUser(int userId, QuizResult testResults, Date date) {

		if (map.containsKey(userId)) {
			if (!usrTests.containsKey(userId)) {
				usrTests.put(userId, new TreeMap<Date, List<QuizResult>>());
			}
			if (!usrTests.get(userId).containsKey(date)) {
				usrTests.get(userId).put(date, new ArrayList<QuizResult>());
			}
			List<QuizResult> dateTestsList = usrTests.get(userId).get(date);
			dateTestsList.add(testResults);
			usrTests.get(userId).put(date, dateTestsList);
		}
		return 0;
	}

	@Override
	public QuizResult getTestResult(int userId, int testId) {
		return null;
	}

	@Override
	public Date getTestDate(int userId, int testId) {
		return null;
	}

	@Override
	public User getUserbyName(String userName) {
		return mapNames.get(userName);
	}

	@Override
	public int getIdByName(String userName) {
		return mapId.get(userName);
	}

	@Override
	public Map<Date, List<QuizResult>> getAllTestsOfUser(int userId) {
		return usrTests.get(userId);
	}

}