package ru.yandex.quiz.mkhaikin.quizgenerator;

import java.io.*;
import java.util.ArrayList;

import ru.yandex.quiz.mkhaikin.quizgenerator.data.Answer;
import ru.yandex.quiz.mkhaikin.quizgenerator.data.Question;
import ru.yandex.quiz.mkhaikin.quizgenerator.data.QuestionHeader;
import ru.yandex.quiz.mkhaikin.quizgenerator.data.Quiz;
import ru.yandex.quiz.mkhaikin.quizgenerator.data.QuizHeader;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

public class QuizGenerator {
	public Quiz generateQuiz(Context context){
		Quiz t = new Quiz();
		try {
			AssetManager assetManager = context.getAssets();
			InputStream is = assetManager.open("content.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String processed = br.readLine();
			Gson gson = new Gson();
			QuizHeader th = gson.fromJson(processed, QuizHeader.class);
			
			t.questions = new ArrayList<Question>(th.numberOfQuestions);
			t.container.statistics = new ArrayList<Integer>(th.numberOfStats);
			for (int i = 0; i < th.numberOfQuestions; ++i){
				processed = br.readLine();
				QuestionHeader q = gson.fromJson(processed, QuestionHeader.class);
				Question question = new Question(q, new ArrayList<Answer>(q.numberOfAnswers));
				for (int j = 0; j < q.numberOfAnswers; ++j){
					processed = br.readLine();
					Answer a = gson.fromJson(processed, Answer.class);
					question.answers.add(a);
				}
				t.questions.add(question);				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		return t;
	}
}