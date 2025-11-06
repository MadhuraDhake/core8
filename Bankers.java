import java.util.Scanner;
public class Bankers{
    private int need[][],allocate[][],max[][],avail[][],np,nr;
    
    private void input(){
     Scanner sc=new Scanner(System.in);
     System.out.print("Enter no. of processes and resources : ");
     np=sc.nextInt();  //no. of process
     nr=sc.nextInt();  //no. of resources
     need=new int[np][nr];  //initializing arrays
     max=new int[np][nr];
     allocate=new int[np][nr];
     avail=new int[1][nr];
     
     System.out.println("Enter allocation matrix -->");
     for(int i=0;i<np;i++)
          for(int j=0;j<nr;j++)
         allocate[i][j]=sc.nextInt();  //allocation matrix
      
     System.out.println("Enter max matrix -->");
     for(int i=0;i<np;i++)
          for(int j=0;j<nr;j++)
         max[i][j]=sc.nextInt();  //max matrix
      
        System.out.println("Enter available matrix -->");
        for(int j=0;j<nr;j++)
         avail[0][j]=sc.nextInt();  //available matrix
        
        sc.close();
    }
    
    private int[][] calc_need(){
       for(int i=0;i<np;i++)
         for(int j=0;j<nr;j++)  //calculating need matrix
          need[i][j]=max[i][j]-allocate[i][j];
       
       return need;
    }
 
    private boolean check(int i){
       //checking if all resources for ith process can be allocated
       for(int j=0;j<nr;j++) 
       if(avail[0][j]<need[i][j])
          return false;
   
    return true;
    }

    public void isSafe(){
       input();
       calc_need();
       boolean done[]=new boolean[np];
       int j=0;

       while(j<np){  //until all process allocated
       boolean allocated=false;
       for(int i=0;i<np;i++)
        if(!done[i] && check(i)){  //trying to allocate
            for(int k=0;k<nr;k++)
            avail[0][k]=avail[0][k]-need[i][k]+max[i][k];
         System.out.println("Allocated process : "+i);
         allocated=done[i]=true;
               j++;
             }
          if(!allocated) break;  //if no allocation
       }
       if(j==np)  //if all processes are allocated
        System.out.println("\nSafely allocated");
       else
        System.out.println("All proceess cant be allocated safely");
    }
    
    public static void main(String[] args) {
       new Bankers().isSafe();
    }
}
// ---------------------------------------------------------------
// ---------------- BANKER'S ALGORITHM - FULL EXPLANATION --------
// ---------------------------------------------------------------
//
// np = number of processes in the system
// nr = number of resource types available for allocation
//
// We use four main matrices:
//
// 1) allocate[np][nr]
//    - allocate[i][j] = number of instances of resource j
//      currently allocated to process i
//
// 2) max[np][nr]
//    - max[i][j] = maximum demand of process i
//      for resource j during its execution
//
// 3) need[np][nr]
//    - need[i][j] = max[i][j] - allocate[i][j]
//    - tells how many more resources process i must receive
//      before it can finish
//
// 4) avail[1][nr]
//    - avail[0][j] = number of free instances of resource j
//    - these can be allocated to any process
//
// ---------------------------------------------------------------
// ---------------------- INPUT PHASE ----------------------------
// ---------------------------------------------------------------
// User enters:
// - np (no. of processes)
// - nr (no. of resources)
// - allocation matrix
// - maximum matrix
// - available matrix
//
// All matrices are stored and used for safety checking.
//
// ---------------------------------------------------------------
// ------------------ NEED MATRIX CALCULATION --------------------
// ---------------------------------------------------------------
// For every process i and resource j:
// need[i][j] = max[i][j] - allocate[i][j]
//
// This shows how many additional resources each process needs
// to complete its execution.
//
// ---------------------------------------------------------------
// ------------------ CHECK() FUNCTION ---------------------------
// ---------------------------------------------------------------
// check(i) verifies if process i can be executed safely:
//
// For every resource j:
//    if (avail[0][j] < need[i][j]) → process i cannot run now
//
// If all needed resources are available, process i is safe to execute.
//
// check(i) returns:
// - true  → process i can run
// - false → process i cannot run yet
//
// ---------------------------------------------------------------
// ------------------ SAFETY ALGORITHM (isSafe) ------------------
// ---------------------------------------------------------------
// Goal: Determine whether the system is in a SAFE or UNSAFE state.
//
// Logic:
// 1) Mark all processes as "not done"
// 2) Repeatedly try to allocate a process that:
//       (not done) AND (check(i) == true)
//
// 3) If process i can be allocated:
//       - It runs and completes
//       - It releases all its resources back to the system
//
//    This is done by the formula:
//       avail = avail - need + max
//
//    This effectively means:
//       avail = avail + allocate
//
//    Because finishing a process returns all allocated resources.
//
// 4) Mark process i as "done"
// 5) Count how many processes finished
//
// If at least one process is allocated in a cycle,
// the algorithm continues.
//
// If in any cycle *no* process can be allocated,
// the system is UNSAFE and may go into deadlock.
//
// ---------------------------------------------------------------
// ------------------------- SAFE STATE ---------------------------
// ---------------------------------------------------------------
// If all np processes become done (completed),
// the system is in a SAFE state.
//
// SAFE STATE:
//   - All processes can complete
//   - No deadlock will occur
//
// UNSAFE STATE:
//   - There exists at least one process whose needs
//     can never be satisfied with current resources
//   - Risk of deadlock is present
//
// ---------------------------------------------------------------
// ------------------ OUTPUT OF THE ALGORITHM --------------------
// ---------------------------------------------------------------
// While running, algorithm prints each process that is allocated:
//     "Allocated process : i"
//
// After all successful allocations:
//     "Safely allocated"
//
// If some processes cannot be completed:
//     "All processes can't be allocated safely"
//
// ---------------------------------------------------------------
// ---------------------- SUMMARY --------------------------------
// ---------------------------------------------------------------
// The Banker's Algorithm checks if resource allocation is safe.
// It prevents deadlock by ensuring that processes receive resources
// only if they can eventually complete and release them.
// It uses four main matrices:
//   allocate, max, need, and avail
//
// np = number of processes
// nr = number of resource types
//
// Safety check proceeds by simulating execution without actually
// allocating resources permanently.
//
// ---------------------------------------------------------------
// ---------------- END OF EXPLANATION ---------------------------
// ---------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------
Output
--------------------------------------------------------------------------------------------------------------------------
Enter no. of processes and resources : 3 4
Enter allocation matrix -->
1 2 2 1
1 0 3 3
1 2 1 0
Enter max matrix -->
3 3 2 2
1 1 3 4
1 3 5 0
Enter available matrix -->
3 1 1 2
Allocated process : 0
Allocated process : 1
Allocated process : 2
Safely allocated
