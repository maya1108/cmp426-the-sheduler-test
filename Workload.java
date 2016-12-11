import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

// compares turnaround times and average turnaround times between first in first out and shortest job first 
public class Workload implements Comparable<Workload> {


	private int runtime; 
	private int arrivaltime; 
	private char jobName;
	int status;
	int turnaroundTime;
	float fifoAvgTurnaroundTime;
	float AvgTurnaroundTime;
	Random ran = new Random();
	String letters = "abcde";
	Workload[] schedulerWorkload;
ArrayList<Workload> sjf = new ArrayList<Workload>();
	
	public int getRuntime(){
		return runtime;	
	}
	
	public void setRuntime(int rtime){
		runtime = rtime ;
	}
	
	 public int getArrivaltime(){
		 return arrivaltime;
	 }
	
	 
	public void setArrivaltime( int atime){
		arrivaltime = atime;
	}

	
	public char getJobName(){
		return jobName;
	}
	
	public void setJobName(char jobname){
		this.jobName = jobname;
	}
	public int getStatus(){
		return status; 
	}
	public void setStatus(int status){
		status = status;
	}
	
	// create workload array 
	public void createWorkload(){
		schedulerWorkload = new Workload[5];
		for( int i = 0; i < schedulerWorkload.length; i ++){
			schedulerWorkload[i] = new Workload();
			schedulerWorkload[i].setArrivaltime(ran.nextInt(50) +0);
			schedulerWorkload[i].setRuntime(ran.nextInt(75) + 10);
			schedulerWorkload[i].setJobName(letters.charAt(i));
			schedulerWorkload[i].setStatus(0);
			
		}
	}
	
	// sorts array in terms of  arrival times 
	public void sort(){
		System.out.println("First In First Out");
		Arrays.sort(schedulerWorkload);
		display();
		
	}
	/*// sorts array in terms of runtimes 
	public void sort2(){
		System.out.println("SJF: sorted by RUNTIMES !!!!!");
		Arrays.sort(schedulerWorkload, Workload.runtimeComparator);
		display();
		
	}*/
	
	
	// prints tables / graphs of times 
	public void display(){
		System.out.println("wd at rt");
		for( int i = 0; i < schedulerWorkload.length; i ++){
			System.out.println(schedulerWorkload[i].getJobName() +" " + schedulerWorkload[i].getArrivaltime() 
					+" "+ schedulerWorkload[i].getRuntime());
		}
	}
	
	// represents first in frist out sheduler 
	public void Fifo(){
		sort();
		turnaroundTime();
		
	}
	
	// represents shortest job first scheduler 
public void SJF(){
		int time = schedulerWorkload[0].arrivaltime + schedulerWorkload[0].runtime;
		System.out.println(time);
		schedulerWorkload[0].status = 1;
		calcJobsToRun(time);
		
	}
// calculates which jobs can be run based on time 
public void calcJobsToRun(int time){
	System.out.println("Shortest Job First ");
	
	for(int i = schedulerWorkload.length-1 ; i >=1 ; i--){
			if ( schedulerWorkload[i].arrivaltime <= time && schedulerWorkload[i].status !=1){
				sjf.add(this. schedulerWorkload[i]);
				schedulerWorkload[i].status = 1;
		}
	}
	
	sjf.sort(runtimeComparator);
	//Collections.sort(sjf);
	for(int i=0; i<sjf.size(); i++)
	 toString();
	
	System.out.println(sjf);
	//System.out.println( sjf.get(i).jobName +sjf.get(i).arrivaltime + sjf.get(i).runtime )
Workload [] sjfArr = new Workload[sjf.size()];
	sjfArr = sjf.toArray(sjfArr);
	sjfTurnaround(sjfArr);


}
//calculates turnaround time for shortest job first 
public void sjfTurnaround(Workload[] sjfArr2){
	AvgTurnaroundTime = 0;
	int timer = schedulerWorkload[0].runtime + schedulerWorkload[0].arrivaltime;
	turnaroundTime = timer -schedulerWorkload[0].arrivaltime;
	System.out.println(schedulerWorkload[0].jobName + ":  turnaround time: "  + turnaroundTime );
	AvgTurnaroundTime += turnaroundTime;
	schedulerWorkload = sjfArr2;
	
	for(int i = 0 ; i< schedulerWorkload.length; i ++){
			timer += schedulerWorkload[i].runtime;
		turnaroundTime = timer - schedulerWorkload[i].arrivaltime;
		System.out.println(schedulerWorkload[i].jobName + ":  turnaround time: "  + turnaroundTime );
		AvgTurnaroundTime += turnaroundTime;
	}
	AvgTurnaroundTime /= schedulerWorkload.length +1;
	System.out.println(" Average Turnaround time : " + AvgTurnaroundTime);
	
}

	
	// calculates turnaround time of fifo 
	public void turnaroundTime(){
		int timer = schedulerWorkload[0].runtime + schedulerWorkload[0].arrivaltime;
		for(int i = 0 ; i< schedulerWorkload.length; i ++){
			if (i == 0){
				turnaroundTime = timer -schedulerWorkload[i].arrivaltime;	
			}else{
				timer += schedulerWorkload[i].runtime;
			turnaroundTime = timer - schedulerWorkload[i].arrivaltime;
			}
			System.out.println(schedulerWorkload[i].jobName + ":  turnaround time: "  + turnaroundTime );
			AvgTurnaroundTime += turnaroundTime;
		}
		AvgTurnaroundTime /= schedulerWorkload.length;
		System.out.println(" Average Turnaround time : " + AvgTurnaroundTime);
	}
	
	// aides in sorting the array based on runtimes 
	public static Comparator < Workload>runtimeComparator = new Comparator<Workload>(){
		@Override
		public int compare(Workload m, Workload n) {

	
		int runtime1 = m.runtime;
		int runtime2 = n.runtime;		
			return runtime1 -runtime2;
		}
		
	};


	@Override
	// aides in sorting array based on arrival times 
	public int compareTo(Workload o) {
		return this.arrivaltime-o.arrivaltime;		
	}
	
	public static void main(String[] args) {
		Workload m = new Workload();
		m.createWorkload();
		m.display();
		m.Fifo();
		m.SJF();

	
	}
	@Override
	public String toString() {
	     return ("jobname: "+this.getJobName()+
	                 " arrival time: "+ this.getArrivaltime() +
	                 " runtime: "+ this.getRuntime());
	}

}
