import java.io.*;
class FIFO
{
        public static void main(String args[]) throws IOException
        {
                
                int n;
                int f;
                float rat;
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                System.out.println(&quot;Enter the number of FRAMES :&quot;);
                f=Integer.parseInt(br.readLine());
                int fifo[]=new int[f];
                System.out.println(&quot;Enter the number of INPUTS :&quot;);
                n=Integer.parseInt(br.readLine());
                int inp[]=new int[n];
                System.out.println(&quot;Enter INPUT:&quot;);
                for(int i=0;i&lt;n;i++)
                inp[i]=Integer.parseInt(br.readLine());
                System.out.println(&quot;----------------------&quot;);
                for(int i=0;i&lt;f;i++)
                        fifo[i]=-1;
                int Hit=0;
                int Fault=0;
                int j=0;
                boolean check;
                for(int i=0;i&lt;n;i++)
                {
                        check=false;

                                for(int k=0;k&lt;f;k++)
                                if(fifo[k]==inp[i])
                                {
                                        check=true;
                                        Hit=Hit+1;
                                }
                                if(check==false)
                                {
                                        fifo[j]=inp[i];
                                        j++;

                                        if(j&gt;=f)
                                        j=0;
                                        Fault=Fault+1;
                                }
                }
                rat = (float)Hit/(float)n;
                System.out.println(&quot;HIT:&quot;+Hit+&quot;  FAULT:&quot;+Fault+&quot;   HIT RATIO:&quot;+rat);
        }
}