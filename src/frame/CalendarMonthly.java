package frame;

public class CalendarMonthly {
	
	private double totalDistane; //ÇÑ ´Þµ¿¾ÈÀÇ ÃÑ°Å¸®
	private double totalTime;//ÇÑ ´Þµ¿¾ÈÀÇ ÃÑ ½Ã°£
	private double totalCalories;//ÇÑ ´Þµ¿¾ÈÀÇ ÃÑ Ä®·Î¸®
	
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
		return String.format("ÃÑ °Å¸®: %.2f km \nÃÑ ½Ã°£: %2f ºÐ \nÃÑ Ä®·Î¸®: %.2f kcal",
				totalDistane,totalTime,totalCalories);
	}
	
	

}
