package line_sweep;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Point
{
    int x;
    int segno;
    boolean st;

    static void SortX(Point point[],int N)
    {
        int flag = 1;
        Point temp;
        for(int i=0;i< N && flag==1;i++)
        {
            flag = 0;
            for (int j=0;j <N-1;j++)
            {
                if (point[j].x > point[j+1].x)
                {
                    temp = point[j];
                    point[j] = point[j+1];
                    point[j+1] = temp;
                    flag = 1;
                }
            }
         }
     }
    
     static void Range(Point point[],int N)
     {
        int range[]=new int[N];
        int count=0;
        int index=0;
        for(int i=0;i< N;i++)
        {
            if (point[i].st==true)
            {
                count++;
                if(count == 1) range[index++]=point[i].x;
            }
            else 
            {
                count--;
                if(count == 0) range[index++]=point[i].x;
            }
        }
        System.out.println("Range of x axis covered by the segments: ");
        for(int i=0;i< index;i=i+2)System.out.println(range[i]+"---"+range[i+1]);
     }
     
     static void Intersect(Point point[],int N)
     {
        int range[]=new int[N];
        int count=0;
        int index=0;
        for(int i=0;i< N;i++)
        {
            if (point[i].st==true)
            {
                count++;
                if(count == 2) range[index++]=point[i].x;
            }
            else 
            {
                count--;
                if(count == 1) range[index++]=point[i].x;
            }
        }
        System.out.println("Range in x axis where segments intersect: ");
        for(int i=0;i< index;i=i+2)System.out.println(range[i]+"---"+range[i+1]);
     }
     
     static void Intersect3(Point point[],int N)
     {
        int range[]=new int[N];
        int count=0;
        int index=0;
        for(int i=0;i< N;i++)
        {
            if (point[i].st==true)
            {
                count++;
                if(count == 3) range[index++]=point[i].x;
            }
            else 
            {
                count--;
                if(count == 2) range[index++]=point[i].x;
            }
        }
        System.out.println("Range in x axis where 3 segments intersect: ");
        for(int i=0;i< index;i=i+2)System.out.println(range[i]+"---"+range[i+1]);
     }
     
     
     static void Overlap(Point point[],int N)
     {
        int segment[][]=new int[N][N];
        int active[]=new int[N];
        int count=0;
        int index_row=0;
        int index_column=0;
        for(int i=0;i< N/2;i++) active[i]=-1;
        for(int i=0;i< N;i++)
            for(int j=0;j< N;j++)
                  segment[i][j]=-1;
        for(int i=0;i< N;i++)
        {
            if (point[i].st==true)
            {
                index_column=0;
                count++;
                active[point[i].segno]=1;
                if(count > 1) 
                {
                    for(int j=0;j<N;j++) 
                    {
                        if(active[j]==1) segment[index_row][index_column++]=j;
                    }
                    index_row++;
                }
            }
            else 
            {
                count--;
                active[point[i].segno]=0;
                index_column=0;
                if(count > 1) 
                {
                    for(int j=0;j<N;j++) 
                    {
                        if(active[j]==1) segment[index_row][index_column++]=j;
                    }
                    index_row++;
                }
            }
        }
        System.out.println("Segement List that overlaps : ");
        for(int i=0;i< index_row;i++)
        {
            for(int j=0;j< N;j++)
            {
                if(segment[i][j]!=-1) System.out.print(segment[i][j]+"  ");
            }
            System.out.println();
        }
     }
     
     static void OverlapRange(Point point[],int N)
     {
        int segment[][]=new int[N][N];
        int range[][]=new int[N][N];
        int active[]=new int[N];
        int count=0;
        int index_row=0;
        int index_column=0;
        for(int i=0;i< N/2;i++) active[i]=-1;
        for(int i=0;i< N;i++)
            for(int j=0;j< N;j++)
                  segment[i][j]=-1;
        for(int i=0;i< N;i++)
            for(int j=0;j< N;j++)
                  range[i][j]=-1;
        for(int i=0;i< N;i++)
        {
            if (point[i].st==true)
            {
                index_column=0;
                count++;
                active[point[i].segno]=1;
                if(count > 1) 
                {
                    for(int j=0;j<N;j++) 
                    {
                        if(active[j]==1) segment[index_row][index_column++]=j;
                    }
                    index_row++;
                }
            }
            else 
            {
                count--;
                active[point[i].segno]=0;
                index_column=0;
                if(count > 1) 
                {
                    for(int j=0;j<N;j++) 
                    {
                        if(active[j]==1) segment[index_row][index_column++]=j;
                    }
                    index_row++;
                }
            }
        }
        for(int i=0;i< N;i++)
        {
            int j;
            for(j=0;j< N;j++)
            {
                if(segment[i][j]==-1) break;
            }
            range[i][0]=start(segment[i],j,point,N);
            range[i][1]=finish(segment[i],j,point,N);
        }
        System.out.println("Segement List that overlaps With range : ");
        for(int i=0;i< index_row;i++)
        {
            for(int j=0;j< N;j++)
            {
                if(segment[i][j]!=-1) System.out.print(segment[i][j]+"  ");
            }
            System.out.print("    range : ");
            for(int j=0;j< 2;j++)
            {
                System.out.print(range[i][j]+"  ");
            }
            System.out.println();
        }
     }
     
     private static int finish(int[] a,int size,Point point[],int N) 
     {
	int b[]=new int[N];
        int k=0;
        int c[]=new int[N];
        int l=0;
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(a[i]==point[j].segno && point[j].st==false)
                {
                    b[k++]=point[j].x;
                }
            }
        }
	int min = b[0];
	for (int i = 0; i <k; i++) 
            if (b[i] < min) min = b[i];
	return min;
    }
     
    private static int start(int[] a,int size,Point point[],int N) 
    {
        int b[]=new int[N];
        int k=0;
        int c[]=new int[N];
        int l=0;
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(a[i]==point[j].segno && point[j].st==true)
                {
                    b[k++]=point[j].x;
                }
            }
        }
	int max = b[0];
	for (int i = 0; i <k; i++) 
            if (b[i] > max) max = b[i];
	return max;
    } 
}



public class Line_Sweep 
{
    public static Scanner in;
    public static void main(String[] args) throws IOException 
    {
        in = new Scanner(new File("input.txt"));
        int N=in.nextInt();
        Point point[]=new Point[2*N];
        int seg=0;
        for(int i=0;i<2*N;i++)
        {
            point[i]=new Point();
            point[i].x=in.nextInt();
            point[i].segno=seg;
            if(i%2==0) point[i].st=true;
            else 
            {
                point[i].st=false;
                seg++;
            }
        }
        System.out.println("Segemnt List :");
        for(int i=0;i<2*N;i=i+2) System.out.println(point[i].x+"---"+point[i+1].x+"   Seg no : "+point[i].segno);
        System.out.println();
        Point.SortX(point,2*N);
        Point.Range(point,2*N);
        System.out.println();
        Point.Intersect(point,2*N);
        System.out.println();
        Point.Intersect3(point,2*N);
        System.out.println();
        Point.Overlap(point,2*N);
        System.out.println();
        Point.OverlapRange(point,2*N);
        System.out.println();
    }
}
