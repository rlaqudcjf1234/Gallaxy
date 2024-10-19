package frame;

import java.util.HashMap;
import java.util.Map;

public class CalendarMonthly {
	
	private double totalDistance; //�� �޵����� �ѰŸ�
	private double totalTime;//�� �޵����� �� �ð�
	private double totalCalories;//�� �޵����� �� Į�θ�
	private Map<String,Entry> entries;
	
	private class Entry{
		private double distance;
		private double time;
		
		public Entry(double distance, double time) {
			this.distance = distance;
			this.time = time;
		}

		public double getDistance() {
			return distance;
		}

		public double getTime() {
			return time;
		}
		
	}
	
	
	public CalendarMonthly() {
		totalDistance=0;
		totalTime=0;
		totalCalories=0;
		entries=new HashMap<>();
	}
	
	public void addEntry(String date,double distance, double time) {
		if (entries.containsKey(date)) {
	        Entry entry = entries.get(date);
	        totalDistance -= entry.getDistance(); // ���� �Ÿ� ����
	        totalTime -= entry.getTime(); // ���� �ð� ����
	        totalCalories -= entry.getTime() * 7.33; // ���� Į�θ� ����
	    }
		
		entries.put(date, new Entry(distance,time));
		totalDistance+=distance;
		totalTime+=time;		
		totalCalories+=time*7.33;
	}
	
	public double getTotalDistance() {
		return totalDistance;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public double getTotalCalories() {
		return totalCalories;
	}

	public String getstats() {
		return String.format("�� �Ÿ�: %.2f km \n�� �ð�: %2f �� \n�� Į�θ�: %.2f kcal",
				totalDistance,totalTime,totalCalories);
	}

	public void removeEntry(String date) {
		if(entries.containsKey(date)) {
			Entry entry=entries.get(date);
			totalDistance-=entry.getDistance();
			totalTime-=entry.getTime();
			totalCalories-=entry.getTime()*7.33;
			
			entries.remove(date);
		}
		
	}
	
	

}
