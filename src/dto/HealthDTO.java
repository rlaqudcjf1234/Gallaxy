package dto;

public class CalendarDTO {
	
	private String date; //�޸𳻿�
	private double distace; // �Ÿ�
	private double time; // �ð�
	private double averageSpeed;//��ռӵ�
	private double calories; //Į�θ�
	private double totaldistace;//�� �Ÿ�
	private double totaltime;//�� �ð�
	private double totalCalories;//�� Į�θ�
			
	public CalendarDTO(String date, double distace, double time, double averageSpeed, double calories,
			double totaldistace, double totaltime, double totalCalories) {
		this.date = date;
		this.distace = distace;
		this.time = time;
		this.averageSpeed = averageSpeed;
		this.calories = calories;
		this.totaldistace = totaldistace;
		this.totaltime = totaltime;
		this.totalCalories = totalCalories;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getDistace() {
		return distace;
	}
	public void setDistace(double distace) {
		this.distace = distace;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getAverageSpeed() {
		return averageSpeed;
	}
	public void setAverageSpeed(double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	public double getTotaldistace() {
		return totaldistace;
	}
	public void setTotaldistace(double totaldistace) {
		this.totaldistace = totaldistace;
	}
	public double getTotaltime() {
		return totaltime;
	}
	public void setTotaltime(double totaltime) {
		this.totaltime = totaltime;
	}
	public double getTotalCalories() {
		return totalCalories;
	}
	public void setTotalCalories(double totalCalories) {
		this.totalCalories = totalCalories;
	}
	
	
	
	
}