package frame;

public class CalendarMonthly {
	
	private double totalDistane; //�� �޵����� �ѰŸ�
	private double totalTime;//�� �޵����� �� �ð�
	private double totalCalories;//�� �޵����� �� Į�θ�
	
	public CalendarMonthly() {
		totalDistane=0;
		totalTime=0;
		totalCalories=0;
	}
	
	public void addEntry(double distance, double time) {
		totalDistane+=distance;
		totalTime+=time;		
		totalCalories+=time*7.33;
	}
	
	public double getTotalDistane() {
		return totalDistane;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public double getTotalCalories() {
		return totalCalories;
	}

	public String getstats() {
		return String.format("�� �Ÿ�: %.2f km \n�� �ð�: %2f �� \n�� Į�θ�: %.2f kcal",
				totalDistane,totalTime,totalCalories);
	}
	
	

}
