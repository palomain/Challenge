package com.instacart.challenge.model;

public class ApplicationStatistics {
	
	private int year;
	private int weekOfYear;
	private int hired;
	private int rejected;
	private int onBoardingCompleted;
	private int onBoardingRequested;
	private int quizStarted;
	private int quizFinished;
	private int applied;
	
	public ApplicationStatistics(int year, int weekOfYear, int hired, int rejected, int onBoardingCompleted,
			int onBoardingRequested, int quizStarted, int quizFinished, int applied) {
		super();
		this.year = year;
		this.weekOfYear = weekOfYear;
		this.hired = hired;
		this.rejected = rejected;
		this.onBoardingCompleted = onBoardingCompleted;
		this.onBoardingRequested = onBoardingRequested;
		this.quizStarted = quizStarted;
		this.quizFinished = quizFinished;
		this.applied = applied;
	}

	public int getYear() {
		return year;
	}

	public int getWeekOfYear() {
		return weekOfYear;
	}

	public int getHired() {
		return hired;
	}

	public int getRejected() {
		return rejected;
	}

	public int getOnBoardingCompleted() {
		return onBoardingCompleted;
	}

	public int getOnBoardingRequested() {
		return onBoardingRequested;
	}

	public int getQuizStarted() {
		return quizStarted;
	}

	public int getQuizFinished() {
		return quizFinished;
	}
	
	public int getApplied() {
		return applied;
	}
	
	
	

}
