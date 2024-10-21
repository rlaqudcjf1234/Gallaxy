package dto;

public class HealthDTO {
	
	private int healthYyyy;
	private int healthMm;
	private int healthDd;
	private int healthDistance; // 거리
	private int healthTime; // 시간
	private double healthSpeed;//평균속도
	private double healthCalories; //칼로리
	private String userId;
	
	public int getHealthYyyy() {
		return healthYyyy;
	}
	public void setHealthYyyy(int healthYyyy) {
		this.healthYyyy = healthYyyy;
	}
	public int getHealthMm() {
		return healthMm;
	}
	public void setHealthMm(int healthMm) {
		this.healthMm = healthMm;
	}
	public int getHealthDd() {
		return healthDd;
	}
	public void setHealthDd(int healthDd) {
		this.healthDd = healthDd;
	}
	public int getHealthDistance() {
		return healthDistance;
	}
	public void setHealthDistance(int healthDistance) {
		this.healthDistance = healthDistance;
	}
	public int getHealthTime() {
		return healthTime;
	}
	public void setHealthTime(int healthTime) {
		this.healthTime = healthTime;
	}
	public double getHealthSpeed() {
		return healthSpeed;
	}
	public void setHealthSpeed(double healthSpeed) {
		this.healthSpeed = healthSpeed;
	}
	public double getHealthCalories() {
		return healthCalories;
	}
	public void setHealthCalories(double healthCalories) {
		this.healthCalories = healthCalories;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "HealthDTO [userId=" + userId + "]";
	}
	
	
}