package com.congnitive.test.mmpitest.domainObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 510954693657235175L;

	public class QuizEnty implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 904797618126767321L;
		private String skillName;
		private String describtion;
		private int skilLevel;
		private String shortName;

		public QuizEnty(String skillName, String describtion, String shortName,
				int skilLevel) {
			super();
			this.skillName = skillName;
			this.describtion = describtion;
			this.skilLevel = skilLevel;
			this.shortName = shortName;
		}

		public String getSkillName() {
			return skillName;
		}

		public void setSkillName(String skillName) {
			this.skillName = skillName;
		}

		public String getDescribtion() {
			return describtion;
		}

		public void setDescribtion(String describtion) {
			this.describtion = describtion;
		}

		public int getSkilLevel() {
			return skilLevel;
		}

		public void setSkilLevel(int skilLevel) {
			this.skilLevel = skilLevel;
		}

		public String getShortName() {
			return shortName;
		}

		public void setShortName(String shortName) {
			this.shortName = shortName;
		}
	}

	private ArrayList<QuizEnty> results = new ArrayList<QuizResult.QuizEnty>();

	public ArrayList<QuizEnty> getResults() {
		return results;
	}

	public void setResults(ArrayList<QuizEnty> results) {
		this.results = results;
	}

	public void addSkill(QuizEnty obj) {
		results.add(obj);
	}
}
