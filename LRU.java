import java.util.*;
class LruAlgo
{
int p[],n,fr[],m,fs[],index,k,l,flag1=0,flag2=0,pf=0,frsize=3,i,j;
Scanner src=new Scanner(System.in);
void read()
{
System.out.println(&quot;Enter page table size&quot;);
n=src.nextInt();
p=new int[n];
System.out.println(&quot;Enter element in page table&quot;);
for(int i=0;i&lt;n;i++)
p[i]=src.nextInt();
System.out.println(&quot;Enter page frame size&quot;);
m=src.nextInt();
fr=new int[m];
fs=new int[m];
}
void display()
{
System.out.println(&quot;\n&quot;);
for(i=0;i&lt;m;i++)
{
if(fr[i]==-1)
System.out.println(&quot;[ ]&quot;);
else
System.out.println(&quot;[&quot;+fr[i]+&quot;]&quot;);
}
}
void lru()
{
for(i=0;i&lt;m;i++)
 {
fr[i]=-1;
}

for(j=0;j&lt;n;j++)
 {
flag1=0;flag2=0;
for(i=0;i&lt;m;i++)
 {
if(fr[i]==p[j])
{
flag1=1;
flag2=1;
break;
}
}
if(flag1==0)
{
for(i=0;i&lt;m;i++)
 {
if(fr[i]==-1)
{
fr[i]=p[j];
flag2=1;
break;
}
}
}
if(flag2==0)
{
for(i=0;i&lt;3;i++)
fs[i]=0;
for(k=j-1,l=1;l&lt;=frsize-1;l++,k--)
{
for(i=0;i&lt;3;i++)
{
if(fr[i]==p[k])
fs[i]=1;
}
}
for(i=0;i&lt;3;i++)
{
if(fs[i]==0)
index=i;
}
fr[index]=p[j];
pf++;
}
System.out.print(&quot;Page : &quot;+p[j]);
display();

}
System.out.println(&quot;\n no of page faults :&quot;+pf);
}
public static void main(String args[])
{
LruAlgo a=new LruAlgo();
a.read();
a.lru();
a.display();
}
}

