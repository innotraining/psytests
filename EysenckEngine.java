package com.example.quizbook;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *   @Singletone
 */
public class EysenckEngine extends Engine {
	
	private ArrayList<String> questionDescriptions2;
	
	// init correct answers
	private List<Integer> theNumbersFalseYes = asList(6, 24, 36);
	private List<Integer> theNumbersFalseNo = asList(12, 18, 30, 42, 48, 54);
	private List<Integer> theNumbersNerotism = asList(2, 4, 7, 9, 11, 14, 16, 19, 21, 23, 26, 28, 31, 33, 35, 38, 40, 43, 45, 47, 50, 52, 55, 57);
	private List<Integer> theNumbersExtroIntroversionYes = asList(1, 3, 8, 10, 13, 17, 22, 25, 27, 39, 44, 46, 49, 53, 56);
	private List<Integer> theNumbersExtroIntroversionNo = asList(5, 15, 20, 29, 32, 34, 37, 41, 51); 
	
	private static EysenckEngine instance = null;
	public static EysenckEngine getInstance() {
		if (instance == null) {
			instance = new EysenckEngine();
		}
		return instance;
	}

	@Override
	public String getQuestionDescription(int qestionNumber) {
		// qestionNumber - 1 because array's enumeration starts with 0
		String randomQuestion;
		if ((new Random()).nextBoolean()) {
			randomQuestion = questionDescriptions.get(qestionNumber - 1);
		}
		else {
			randomQuestion = questionDescriptions2.get(qestionNumber - 1);
		}
		return "Вопрос " + qestionNumber + " из " + questionDescriptions.size() + "\n" + randomQuestion;
	}
	
	private EysenckEngine(){
		//init fields
		questionDescriptions = new ArrayList<String>();
		questionDescriptions2 = new ArrayList<String>();
		answersOnQusetions = new ArrayList<ArrayList<String>>();
		answerType = "YN";
		userAnswersOnQuestions = new ArrayList<ArrayList<Integer>>();
		refresh();
		// init questions
		questionDescriptions.add("Часто ли вы испытываете тягу к новым впечатлениям, к тому, чтобы отвлечься, испытывать сильные ощущения?");
		questionDescriptions.add("Часто ли вы чувствуете, что нуждаетесь в друзьях, которые могут вас понять, ободрить или посочувствовать?");
		questionDescriptions.add("Считаете ли вы себя беззаботным человеком?");
		questionDescriptions.add("Очень ли трудно вам отказываться от своих намерений?");
		questionDescriptions.add("Обдумываете ли вы свои дела не спеша и предпочитаете ли подождать, прежде чем действовать?");
		questionDescriptions.add("Всегда ли вы сдерживаете свои обещания, даже если это вам невыгодно?");
		questionDescriptions.add("Часто ли у вас бывают спады и подъемы настроения?");
		questionDescriptions.add("Быстро ли вы обычно действуете и говорите, не тратите ли много времени на обдумывание?");
		questionDescriptions.add("Возникало ли у вас когда-нибудь чувство, что вы несчастны, хотя никакой серьезной причины для этого не было?");
		questionDescriptions.add("Верно ли, что на спор вы способны решиться на все?");
		questionDescriptions.add("Смущаетесь ли вы, когда хотите познакомиться с человеком противоположного пола, который вам симпатичен?");
		questionDescriptions.add("Бывает ли когда-нибудь, что, разозлившись, вы выходите из себя?");
		questionDescriptions.add("Часто ли бывает, что вы действуете необдуманно, под влиянием момента?");
		questionDescriptions.add("Часто ли вас беспокоят мысли о том, что вам не следовало чего-либо делать или говорить?");
		questionDescriptions.add("Предпочитаете ли вы чтение книг встречам с людьми?");
		questionDescriptions.add("Верно ли, что вас легко задеть?");
		questionDescriptions.add("Любите ли вы часто бывать в компании?");
		questionDescriptions.add("Бывают ли иногда у вас такие мысли, которыми вам не хотелось бы делиться с другими людьми?");
		questionDescriptions.add("Верно ли, что иногда вы настолько полны энергии, что все горит в руках, а иногда чувствуете сильную вялость?");
		questionDescriptions.add("Стараетесь ли вы ограничить круг своих знакомых небольшим числом самых близких друзей?");
		questionDescriptions.add("Много ли вы мечтаете?");
		questionDescriptions.add("Когда на вас кричат, отвечаете ли вы тем же?");
		questionDescriptions.add("Считаете ли вы все свои привычки хорошими?");
		questionDescriptions.add("Часто ли у вас появляется чувство, что вы в чем-то виноваты?");
		questionDescriptions.add("Способны ли вы иногда дать волю своим чувствам и беззаботно развлечься с веселой компанией?");
		questionDescriptions.add("Можно ли сказать, что нервы у вас часто бывают натянуты до предела?");
		questionDescriptions.add("Слывете ли вы за человека живого и веселого?");
		questionDescriptions.add("После того как дело сделано, часто ли вы мысленно возвращаетесь к нему и думаете, что могли бы сделать лучше?");
		questionDescriptions.add("Чувствуете ли вы себя неспокойно, находясь в большой компании?");
		questionDescriptions.add("Бывает ли, что вы передаете слухи?");
		questionDescriptions.add("Бывает ли, что вам не спится из-за того, что в голову лезут разные мысли?");
		questionDescriptions.add("Что вы предпочитаете, если хотите узнать что-либо: найти в книге или спросить у друзей?");
		questionDescriptions.add("Бывают ли у вас сильные сердцебиения?");
		questionDescriptions.add("Нравится ли вам работа, требующая сосредоточения?");
		questionDescriptions.add("Бывают ли у вас приступы дрожи?");
		questionDescriptions.add("Всегда ли вы говорите только правду?");
		questionDescriptions.add("Бывает ли вам неприятно находиться в компании, где все подшучивают друг над другом");
		questionDescriptions.add("Раздражительны ли вы?");
		questionDescriptions.add("Нравится ли вам работа, требующая быстрого действия?");
		questionDescriptions.add("Верно ли, что вам часто не дают покоя мысли о разных неприятностях и «ужасах», которые могли бы произойти, хотя все кончилось благополучно?");
		questionDescriptions.add("Верно ли, что вы неторопливы в движениях и несколько медлительны?");
		questionDescriptions.add("Опаздывали ли вы когда-нибудь на работу или на встречу с кем-либо?");
		questionDescriptions.add("Часто ли вам снятся кошмары?");
		questionDescriptions.add("Верно ли, что вы так любите поговорить, что не упускаете любого удобного случая побеседовать с новым человеком?");
		questionDescriptions.add("Беспокоят ли вас какие-нибудь боли?");
		questionDescriptions.add("Огорчились бы вы, если бы долго не могли видеться со своими друзьями?");
		questionDescriptions.add("Можете ли вы назвать себя нервным человеком?");
		questionDescriptions.add("Есть ли среди ваших знакомых такие, которые вам явно не нравятся?");
		questionDescriptions.add("Могли бы вы сказать, что вы уверенный в себе человек?");
		questionDescriptions.add("Легко ли вас задевает критика ваших недостатков или вашей работы?");
		questionDescriptions.add("Трудно ли вам получить настоящее удовольствие от мероприятий, в которых участвует много народа?");
		questionDescriptions.add("Беспокоит ли вас чувство, что вы чем-то хуже других?");
		questionDescriptions.add("Сумели бы вы внести оживление в скучную компанию?");
		questionDescriptions.add("Бывает ли, что вы говорите о вещах, в которых совсем не разбираетесь?");
		questionDescriptions.add("Беспокоитесь ли вы о своем здоровье?");
		questionDescriptions.add("Любите ли вы подшутить над другими?");
		questionDescriptions.add("Страдаете ли вы бессонницей?");
		// second variant
		questionDescriptions2.add("Нравится ли вам оживление и суета вокруг вас?");
		questionDescriptions2.add("Часто ли у вас бывает беспокойное чувство, что вам что-нибудь хочется, а вы не знаете что?");
		questionDescriptions2.add("Вы из тех людей, которые не лезут за словом в карман?");
		questionDescriptions2.add("Чувствуете ли вы себя иногда счастливым, а иногда печальным без какой-либо причины?");
		questionDescriptions2.add("Держитесь ли вы обычно в тени на вечеринках или в компании?");
		questionDescriptions2.add("Всегда ли в детстве вы делали немедленно и безропотно то, что вам приказывали?");
		questionDescriptions2.add("Бывает ли у вас иногда дурное настроение?");
		questionDescriptions2.add("Когда вас втягивают в ссору, предпочитаете ли вы отмолчаться, надеясь, что все обойдется?");
		questionDescriptions2.add("Легко ли вы поддаетесь переменам настроения?");
		questionDescriptions2.add("Нравится ли вам находиться среди людей?");
		questionDescriptions2.add("Часто ли вы теряли сон из-за своих тревог?");
		questionDescriptions2.add("Упрямитесь ли вы иногда?");
		questionDescriptions2.add("Могли бы вы назвать себя бесчестным?");
		questionDescriptions2.add("Часто ли вам приходят хорошие мысли слишком поздно?");
		questionDescriptions2.add("Предпочитаете ли вы работать в одиночестве?");
		questionDescriptions2.add("Часто ли вы чувствуете себя апатичным и усталым без серьезной причины?");
		questionDescriptions2.add("Вы по натуре живой человек?");
		questionDescriptions2.add("Смеетесь ли вы иногда над неприличными шутками?");
		questionDescriptions2.add("Часто ли вам что-то так надоедает, что вы чувствуете себя «сытым по горло»?");
		questionDescriptions2.add("Чувствуете ли вы себя неловко в какой-либо одежде, кроме повседневной?");
		questionDescriptions2.add("Часто ли ваши мысли отвлекаются, когда вы пытаетесь сосредоточиться на чем-то?");
		questionDescriptions2.add("Можете ли вы быстро выразить ваши мысли словами?");
		questionDescriptions2.add("Часто ли вы бываете погружены в свои мысли?");
		questionDescriptions2.add("Полностью ли вы свободны от всяких предрассудков?");
		questionDescriptions2.add("Нравятся ли вам первоапрельские шутки?");
		questionDescriptions2.add("Часто ли вы думаете о своей работе?");
		questionDescriptions2.add("Очень ли вы любите вкусно поесть?");
		questionDescriptions2.add("Нуждаетесь ли вы в дружески расположенном человеке, чтобы выговориться, когда вы раздражены?");
		questionDescriptions2.add("Очень ли вам неприятно брать взаймы или продавать что-нибудь, когда вы нуждаетесь в деньгах?");
		questionDescriptions2.add("Хвастаетесь ли вы иногда?");
		questionDescriptions2.add("Очень ли вы чувствительны к некоторым вещам?");
		questionDescriptions2.add("Предпочли бы вы остаться в одиночестве дома, чем пойти на скучную вечеринку?");
		questionDescriptions2.add("Бываете ли вы иногда беспокойными настолько, что не можете долго усидеть на месте?");
		questionDescriptions2.add("Склонны ли вы планировать свои дела тщательно и раньше чем следовало бы?");
		questionDescriptions2.add("Бывают ли у вас головокружения?");
		questionDescriptions2.add("Всегда ли вы отвечаете на письма сразу после прочтения?");
		questionDescriptions2.add("Справляетесь ли вы с делом лучше, обдумав его самостоятельно, а не обсуждая с другими?");
		questionDescriptions2.add("Бывает ли у вас когда-либо одышка, даже если вы не делали никакой тяжелой работы?");
		questionDescriptions2.add("Можно ли сказать, что вы человек, которого не волнует, чтобы все было именно так, как нужно?");
		questionDescriptions2.add("Беспокоят ли вас ваши нервы?");
		questionDescriptions2.add("Предпочитаете ли вы больше строить планы, чем действовать?");
		questionDescriptions2.add("Откладываете ли вы иногда на завтра то, что должны сделать сегодня?");
		questionDescriptions2.add("Нервничаете ли вы в местах, подобных лифту, метро, туннелю?");
		questionDescriptions2.add("При знакомстве вы обычно первыми проявляете инициативу?");
		questionDescriptions2.add("Бывают ли у вас сильные головные боли?");
		questionDescriptions2.add("Считаете ли вы обычно, что все само собой уладится и придет в норму?");
		questionDescriptions2.add("Трудно ли вам заснуть ночью?");
		questionDescriptions2.add("Лгали ли вы когда-нибудь в своей жизни?");
		questionDescriptions2.add("Говорите ли вы иногда первое, что придет в голову?");
		questionDescriptions2.add("Долго ли вы переживаете после случившегося конфуза?");
		questionDescriptions2.add("Замкнуты ли вы обычно со всеми, кроме близких друзей?");
		questionDescriptions2.add("Часто ли с вами случаются неприятности?");
		questionDescriptions2.add("Любите ли вы рассказывать истории друзьям?");
		questionDescriptions2.add("Предпочитаете ли вы больше выигрывать, чем проигрывать?");
		questionDescriptions2.add("Часто ли вы чувствуете себя неловко в обществе людей выше вас по положению?");
		questionDescriptions2.add("Когда обстоятельства против вас, обычно вы думаете тем не менее, что стоит еще что-либо предпринять?");
		questionDescriptions2.add("Часто ли у вас сосет под ложечкой перед важным делом?");
	}

	@Override
	public Object getConclusion() {
		String result = "example: your answers are: ";
		for (int i = 0; i < userAnswersOnQuestions.size(); i++) {
			result += userAnswersOnQuestions.get(i).get(0) + "; ";
		}
		return new EysenckConclusion(result);
	}
	
}
