package com.example.quizbook;

import java.util.HashMap;
import java.util.Map;

public class QuizFactory {

  private static final Map<String, IEngine> testnameToEngine = new HashMap<String, IEngine>() {{
		put("Eysenck", EysenckEngine.getInstance());
		//put("Rusalov", RusalovEngine.getInstance());
		//put("Shmisheck", ShmisheckEngine.getInstance());
		//put("MMPI", MMPIEngine.getInstance());
		//put("BassDarki", BassDarkiEngine.getInstance());
	}};
	
	public static IEngine getEngineInstance(String testname) {
		return testnameToEngine.get(testname);		
	}
	
}
