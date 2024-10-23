package frame;

import java.util.HashMap;
import java.util.Map;

public class CalendarMonthly {
	
	private double totalDistance; //한 달동안의 총거리
	private double totalTime;//한 달동안의 총 시간
	private double totalCalories;//한 달동안의 총 칼로리
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
	        totalDistance -= entry.getDistance(); // 기존 거리 차감
	        totalTime -= entry.getTime(); // 기존 시간 차감
	        totalCalories -= entry.getTime() * 7.33; // 기존 칼로리 차감
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

	public String getStats() {
		return String.format("총 거리: %.2f km \n총 시간: %2f 분 \n총 칼로리: %.2f kcal",
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
