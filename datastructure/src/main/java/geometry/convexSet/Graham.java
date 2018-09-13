package geometry.convexSet;

import java.util.Set;
import java.util.Stack;

/**
 * 寻找点集的凸包
 * 先找到y最小的点O，以O建立极坐标，其它点按极角排序后再从头开始扫描（配合stack实现）。
 * O(nlgn)
 */
import java.util.*;
public class Graham {
    static int MAX = 1000;
    static POINT[] point = new POINT[MAX];
    static int[] stack = new int[MAX];
    static int n;
    static int top;

    static class POINT {
        public double x,y;
        public POINT(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    //(p1-p0)x(p2-p0),判断两直线的相对位置
    static double xmul(POINT p1,POINT p2,POINT p0){
        return (p1.x-p0.x)*(p2.y-p0.y) - (p2.x-p0.x)*(p1.y-p0.y);
    }

    static double distance(POINT p1,POINT p2){
        return (p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y);
    }

    static void swap(int i,int j){
        POINT tmp = point[i];
        point[i] = point[j];
        point[j] = tmp;
    }

    static void grahamscan(int n){
        int u = 0,i = 0;
        //找到y值最小的点，换到第一个，如果有多个选最左边的一个
        for(;i < n;i++){
            if((point[i].y < point[u].y)||(point[i].y == point[u].y && point[i].x<point[u].x )){
                u = i;
            }
        }
        swap(u,0);

        //以其它点相对于这个点的极角按0到pi逆时针排序
        //这里排序的时候直接调换了位置，最终输出应与排序后的数组相对应
        Arrays.sort(point,0,n,new MyComp());

        //point0 point1 point2入栈
        for(i=0;i<3;i++) stack[i] = i;
        top = 2;
        for(i = 3;i < n;i++){
            while(xmul(point[i],point[stack[top]],point[stack[top-1]]) >= 0){//弹出非左转的点
                if(top == 0) break;
                top--;
            }
            stack[++top] = i;
        }
    }

    static class MyComp implements Comparator<POINT> {
        public int compare(POINT p1,POINT p2){
            double xm = xmul(p1,p2,point[0]);
            if(xm < 0) return 1;
            else if(xm == 0 && distance(p1,point[0])>distance(p2,point[0])) return 1;
            else return -1;
        }
    }

    static double area(int n){
        double area = 0;
        for(int i = 2;i <= n-1;i++){
            area += xmul(point[stack[i]],point[stack[i-1]],point[0]);
        }
        return Math.abs(area)/2;
    }


    public static void main(String[] args){
        n = 4;
        point[0] = new POINT(0,0);
        point[1] = new POINT(1,0);
        point[2] = new POINT(0,1);
        point[3] = new POINT(1,1);

        grahamscan(n);
        System.out.println("area: "+area(n));
        print();
    }

    static void print(){
        System.out.println("凸包:");
        for(int i=0;i<=top;i++){
            System.out.print(stack[i]+"-");
        }
        System.out.println("0");
    }

}
/*
请输入点数
4
请输入x,y
0 0
1 0
0 1
1 1
area: 1.0
0-1-2-3-0
*/
