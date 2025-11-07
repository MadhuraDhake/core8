import java.util.*;
import java.io.*;

public class robbin
{

    public static void main(String args[])
    {
        int n,sum=0;
        float total_tt=0,total_waiting=0;
        
          Scanner s=new Scanner(System.in);
          System.out.println("Enter Number Of Process U want 2 Execute---"); 
          n=s.nextInt();
          int arrival[]=new int[n];
          int cpu[]=new int[n];
          int ncpu[]=new int[n];
          int pri[]=new int[n];
          int finish[]=new int[100];
          int turntt[]=new int[n];
          int wait[]=new int[n];
          int process[]=new int[n];
          int t_quantum,difference,temp_sum=0,k=0;
          int seq[]=new int[100];
          
         // int pro[][]=new int[3][3];
          for(int i=0;i<n;i++)
          {
                System.out.println("Enter arrival time of "+(i+1)+" Process : ");
                arrival[i]=s.nextInt();
                System.out.println("Enter CPU time of "+(i+1)+" Process : ");
                ncpu[i]=cpu[i]=s.nextInt();
                
                
                
                process[i]=i+1;
          }
          
         System.out.println("Enter time quantum : ");
         t_quantum = s.nextInt();
          
          int tv=0;
          for(int i=0;i<n;i++){temp_sum=temp_sum+cpu[i];}
          //System.out.println(temp_sum);
          
          
          System.out.println("Process execution sequence : ");
          while(sum!=temp_sum){
		  for(int i=0;i<n;i++)
		  {
		  	if(ncpu[i]<t_quantum)
		  		{
		  			difference=ncpu[i];
		  			tv=ncpu[i];
		  			ncpu[i]=0;
		  		}
		  	else
		  		{
		  			difference = ncpu[i]-t_quantum;
		  			tv=t_quantum;
		  			ncpu[i]=difference;
		  		}
		  	if(tv > 0)
		  	{	
		        sum=sum+tv;
		        finish[k]=sum;
		        seq[k]=i;
		        System.out.print(seq[k]+1+" ");
		        
		        k++;
		        
		        }
		  }
          }
          System.out.println();
          
          for(int i=0;i<n;i++)
          {	
          	int carr=0,tt=0;
          	carr=arrival[i];
          	
          	for(int j=0;j<k;j++)
          	{
                	if(seq[j]==i)
                	{
                		tt=tt+(finish[j]-carr);
                		carr=finish[j];
                	}
                }
                
                turntt[i]=tt;
                System.out.println("Turn around time for "+(i+1)+" process : "+turntt[i]);
                total_tt=total_tt+turntt[i];
                
                wait[i]=turntt[i]-cpu[i];
                
                System.out.println("Waiting time for "+(i+1)+" process : "+wait[i]);
                
                total_waiting+=wait[i];
          }
          
          System.out.println("\n\nProcess\t\tAT\tCPU_T");
          for(int i=0;i<n;i++)
          {
                System.out.println(process[i]+"\t\t"+arrival[i]+"\t"+cpu[i]);
          }
          
          System.out.println("\n\n");
          System.out.println("Total turn around time is : "+(total_tt/n));
          System.out.println("Total waiting time is : "+(total_waiting/n));
          
                                    
    }

}
/*
    ------------------------------- ROUND ROBIN SCHEDULING -------------------------------

    Round Robin (RR) is a CPU Scheduling algorithm where:
    - Each process gets a fixed amount of CPU time, called TIME QUANTUM.
    - If the process finishes before quantum → it is completed.
    - If not → it is paused and moved to the BACK of the queue.
    - CPU keeps cycling between processes in a circular manner.
    - Ensures fairness; good for time-sharing systems.

    --------------------------------------------------------------------------
    VARIABLES USED IN THIS PROGRAM:
    --------------------------------------------------------------------------

    n           → Number of processes
    arrival[]   → Arrival time of each process
    cpu[]       → Original burst time required by each process
    ncpu[]      → Remaining burst time (updated after each quantum)
    finish[]    → Stores finish time after each execution slice
    seq[]       → Stores which process executed at each CPU slice
    sum         → Total time elapsed (GLOBAL CPU clock)
    temp_sum    → Total CPU time required by all processes (sum of burst times)
    t_quantum   → Time quantum given to each process per turn
    turntt[]    → Turnaround time for each process
    wait[]      → Waiting time for each process
    k           → Index used for filling seq[] and finish[]

    --------------------------------------------------------------------------
    ROUND ROBIN LOGIC SUMMARY:
    --------------------------------------------------------------------------
    - Keep looping until total time executed (sum) matches total burst time (temp_sum)
    - For each process:
         → If remaining time < quantum, finish it
         → Else run it for quantum and reduce its remaining time
    - Track:
         → which process executed
         → how long it executed
         → when it finished
    - After finishing all slices:
         → Calculate TAT and WT using finish[] and seq[]

    --------------------------------------------------------------------------
    DETAILED COMMENTED EXPLANATION PER LINE OF ACTUAL IMPLEMENTATION:
    --------------------------------------------------------------------------
*/

System.out.println("Enter Number Of Process U want 2 Execute---");
n = s.nextInt();  
// Input number of processes

arrival = new int[n];
cpu = new int[n];
ncpu = new int[n];
process = new int[n];
// Arrays created to store arrival time, burst time, remaining time, process ID

for (int i = 0; i < n; i++) {
    // Loop through each process

    System.out.println("Enter arrival time of Process " + (i+1));
    arrival[i] = s.nextInt();
    // Store arrival time

    System.out.println("Enter CPU burst time of Process " + (i+1));
    cpu[i] = ncpu[i] = s.nextInt();
    // cpu[i] stores original burst time
    // ncpu[i] stores remaining burst time (will keep reducing)

    process[i] = i + 1;
    // Store process number (1,2,3,...)
}

System.out.println("Enter time quantum:");
t_quantum = s.nextInt();
// Input time quantum of Round Robin

// Calculate total CPU burst time needed
temp_sum = 0;
for(int i = 0; i < n; i++) {
    temp_sum += cpu[i];
}
// temp_sum helps the while loop know when all processes are finished

System.out.println("Process execution sequence:");

sum = 0; // CPU global clock
k = 0;   // index for seq[] and finish[]

// MAIN ROUND ROBIN LOOP
while(sum != temp_sum) {
    // Loop until all burst time has been executed

    for(int i = 0; i < n; i++) {
        // Visit each process in order

        int tv = 0;        // Time actually given in this turn
        int difference = 0; // Remaining time after execution

        if (ncpu[i] < t_quantum) {
            // If remaining time is less than the quantum,
            // process will finish in this turn

            difference = ncpu[i];  
            tv = ncpu[i];          // Process uses exactly its remaining time
            ncpu[i] = 0;           // It finishes here
        } 
        else {
            // If remaining time >= quantum
            // Process uses only the quantum

            difference = ncpu[i] - t_quantum;
            tv = t_quantum;       // It uses full quantum
            ncpu[i] = difference; // Update remaining time
        }

        if(tv > 0) {
            // If the process actually executed something:

            sum += tv;
            // Increase global CPU clock

            finish[k] = sum;
            // Store finish time of this execution slice

            seq[k] = i;
            // Store which process got CPU this slice

            System.out.print((seq[k] + 1) + " ");
            // Print the process number (adding +1 for human readability)

            k++;
            // Move to next slot for next execution slice
        }
    }
}

System.out.println();
// Done with execution scheduling — now calculate TAT and Waiting Time

for(int i = 0; i < n; i++) {
    int carr = arrival[i];
    // 'carr' tracks the last completion time for this process

    int tt = 0;
    // 'tt' stores total turnaround time for process i

    for(int j = 0; j < k; j++) {
        if(seq[j] == i) {
            // If at slice j, process i was executed:

            tt += (finish[j] - carr);
            // Add time between last event (arrival or previous finish) and this finish

            carr = finish[j];
            // Update last finish time for next calculation
        }
    }

    turntt[i] = tt;
    // Store turnaround time (finish time - arrival time)

    System.out.println("Turnaround time of Process " + (i+1) + " : " + turntt[i]);

    wait[i] = turntt[i] - cpu[i];
    // WT = TAT - Burst Time

    System.out.println("Waiting time of Process " + (i+1) + " : " + wait[i]);
}

/*
    --------------------------------------------------------------------------
    TURNAROUND TIME (TAT):
           = Total time from arrival to final completion

    WAITING TIME (WT):
           = Time spent waiting in the ready queue
           = TAT - Burst Time

    After execution:
    - finish[] array has the end times of each CPU slice
    - seq[] array has the order of execution
    - turntt[] stores individual TATs
    - wait[] stores WT for each process
    --------------------------------------------------------------------------
*/

