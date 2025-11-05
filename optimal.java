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

