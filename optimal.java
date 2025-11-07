import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class OptimalReplacement {
public static void main(String[] args) throws IOException
{
BufferedReader br = new BufferedReader(new
InputStreamReader(System.in));
int frames, pointer = 0, hit = 0, fault = 0,ref_len;
boolean isFull = false;
int buffer[];
int reference[];
int mem_layout[][];
System.out.println(&quot;Please enter the number of Frames: &quot;);
frames = Integer.parseInt(br.readLine());
System.out.println(&quot;Please enter the length of the Reference string:
&quot;);
ref_len = Integer.parseInt(br.readLine());
reference = new int[ref_len];
mem_layout = new int[ref_len][frames];
buffer = new int[frames];
for(int j = 0; j &lt; frames; j++)
buffer[j] = -1;
System.out.println(&quot;Please enter the reference string: &quot;);
for(int i = 0; i &lt; ref_len; i++)
{
reference[i] = Integer.parseInt(br.readLine());
}
System.out.println();
for(int i = 0; i &lt; ref_len; i++)
{
int search = -1;
for(int j = 0; j &lt; frames; j++)
{
if(buffer[j] == reference[i])
{
search = j;
hit++;
break;
}
}
if(search == -1)
{
if(isFull)
{
int index[] = new int[frames];
boolean index_flag[] = new boolean[frames];
for(int j = i + 1; j &lt; ref_len; j++)

{
for(int k = 0; k &lt; frames; k++)
{
if((reference[j] == buffer[k]) &amp;&amp; (index_flag[k] == false))
{
index[k] = j;
index_flag[k] = true;
break;
}
}
}
int max = index[0];
pointer = 0;
if(max == 0)
max = 200;
for(int j = 0; j &lt; frames; j++)
{
if(index[j] == 0)
index[j] = 200;
if(index[j] &gt; max)
{
max = index[j];
pointer = j;
}
}
}
buffer[pointer] = reference[i];
fault++;
if(!isFull)
{
pointer++;
if(pointer == frames)
{
pointer = 0;
isFull = true;
}
}
}
for(int j = 0; j &lt; frames; j++)
mem_layout[i][j] = buffer[j];
}
for(int i = 0; i &lt; frames; i++)
{
for(int j = 0; j &lt; ref_len; j++)
System.out.printf(&quot;%3d &quot;,mem_layout[j][i]);
System.out.println();
}
System.out.println(&quot;The number of Hits: &quot; + hit);
System.out.println(&quot;Hit Ratio: &quot; + (float)((float)hit/ref_len));
System.out.println(&quot;The number of Faults: &quot; + fault);
}
}
//explainaton
import java.io.BufferedReader;   // For reading input from user
import java.io.IOException;      // For handling input/output exceptions
import java.io.InputStreamReader; // Converts keyboard input into readable format

public class OptimalReplacement {

    public static void main(String[] args) throws IOException {

        // Create BufferedReader object for user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int frames;               // Number of frames in memory
        int pointer = 0;          // Used to point to which frame to replace
        int hit = 0;              // Count of page hits
        int fault = 0;            // Count of page faults
        int ref_len;              // Length of reference string
        boolean isFull = false;   // Marks whether memory frames are completely filled

        int buffer[];             // Array representing memory frames
        int reference[];          // Reference string array
        int mem_layout[][];       // Used to store memory layout step-by-step

        // ---- INPUT SECTION ----
        System.out.println("Please enter the number of Frames: ");
        frames = Integer.parseInt(br.readLine());     // Read number of frames

        System.out.println("Please enter the length of the Reference string:");
        ref_len = Integer.parseInt(br.readLine());    // Read reference string length

        reference = new int[ref_len];                 // Initialize reference array
        mem_layout = new int[ref_len][frames];        // Initialize memory layout table
        buffer = new int[frames];                     // Initialize frame buffer

        // Initially set all memory frames to -1 (empty)
        for (int j = 0; j < frames; j++) {
            buffer[j] = -1;
        }

        // Read reference string values
        System.out.println("Please enter the reference string: ");
        for (int i = 0; i < ref_len; i++) {
            reference[i] = Integer.parseInt(br.readLine());
        }

        System.out.println();

        // ---- MAIN PROCESSING LOOP ----
        // Loop through each page in the reference string
        for (int i = 0; i < ref_len; i++) {

            int search = -1;   // Used to check if page is already present in frames

            // ---- PAGE HIT CHECK ----
            // Check all frames to see if current page exists
            for (int j = 0; j < frames; j++) {
                if (buffer[j] == reference[i]) {   // If the page is found in memory
                    search = j;                    // Store index where found
                    hit++;                         // Increase hit counter
                    break;                         // Stop searching further
                }
            }

            // ---- PAGE FAULT CONDITION ----
            if (search == -1) {   // Page not found ⇒ Page Fault occurs

                // If all frames are full, apply Optimal Replacement logic
                if (isFull) {

                    int index[] = new int[frames];         // stores next use positions
                    boolean index_flag[] = new boolean[frames]; // marks if next use found

                    // ---- FIND NEXT USE OF EACH PAGE IN MEMORY ----
                    // Look ahead in reference string from (i+1) to end
                    for (int j = i + 1; j < ref_len; j++) {

                        // Check each frame for its next future use
                        for (int k = 0; k < frames; k++) {

                            // If future reference matches a page in buffer
                            // and next use not yet recorded...
                            if ((reference[j] == buffer[k]) && (index_flag[k] == false)) {

                                index[k] = j;          // Store next use index
                                index_flag[k] = true;  // Mark as recorded
                                break;                 // Stop checking this frame
                            }
                        }
                    }

                    // ---- FIND FRAME TO REPLACE ----
                    // The one that will not be used for the longest time (max index)

                    int max = index[0];  // assume first frame has max next use
                    pointer = 0;         // pointer → frame to be replaced

                    // If the first frame has no future use, assign large number
                    if (max == 0)
                        max = 200;

                    // Check all frames to find the one with farthest next use
                    for (int j = 0; j < frames; j++) {

                        // If page never appears again → treat as large value
                        if (index[j] == 0)
                            index[j] = 200;

                        // If this frame has farther next use than current max
                        if (index[j] > max) {
                            max = index[j];   // update max value
                            pointer = j;      // update frame to replace
                        }
                    }
                }

                // ---- REPLACE PAGE ----
                buffer[pointer] = reference[i];   // Replace selected frame with new page
                fault++;                          // Increase page fault count

                // If memory was not yet full, fill frames sequentially
                if (!isFull) {
                    pointer++;
                    if (pointer == frames) {
                        pointer = 0;      // reset pointer
                        isFull = true;    // Now all frames are full
                    }
                }
            }

            // ---- STORE CURRENT FRAME CONTENT FOR OUTPUT ----
            for (int j = 0; j < frames; j++) {
                mem_layout[i][j] = buffer[j];   // Store state after this reference
            }
        }

        // ---- PRINT MEMORY LAYOUT ----
        for (int i = 0; i < frames; i++) {
            for (int j = 0; j < ref_len; j++) {
                System.out.printf("%3d ", mem_layout[j][i]);
            }
            System.out.println();
        }

        // ---- PRINT FINAL RESULT ----
        System.out.println("The number of Hits: " + hit);                        // Total hits
        System.out.println("Hit Ratio: " + (float)((float)hit / ref_len));       // Hit ratio
        System.out.println("The number of Faults: " + fault);                    // Total faults
    }
}

