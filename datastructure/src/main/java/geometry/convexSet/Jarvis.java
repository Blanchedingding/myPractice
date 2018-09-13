package geometry.convexSet;

import java.util.Arrays;

/**
 * 寻找点集的凸包
 * 也可称为卷包裹法，思路是先找到一个在凸包上的点，然后卷过去，
 * 由于每次确定凸包上的一个点需要遍历所有的点，因此时间复杂度为O（N*H），其中N为全部点的数目，H为凸包上的点数目；
 */
public class Jarvis {

    static int MAX = 100;
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

        @Override
        public String toString(){
            return "(" + this.x + ", " + this.y + ")";
        }
    }

    //(p1-p0)x(p2-p0),判断两直线的相对位置
    static double xmul(POINT p1,POINT p2,POINT p0){
        return (p1.x-p0.x)*(p2.y-p0.y) - (p2.x-p0.x)*(p1.y-p0.y);
    }

    static double distance(POINT p1,POINT p2){
        return (p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y);
    }

    //p0是基点，p2是目前已知极角最小的点，p1是带判断的点
    static boolean judgeOnLeft(POINT p0, POINT p1, POINT p2){
        double cross = xmul(p1, p2, p0);
        return ((cross < 0) || (cross == 0 && distance(p0, p1) >= distance(p0, p2)));
    }

    static void jarvis(){
        int first = 0;
        //找到y值最小的点，如果有多个选最左边的一个
        for(int i = 0;i < n;i++){
            if((point[i].y < point[first].y)||(point[i].y == point[first].y && point[i].x<point[first].x )){
                first = i;
            }
        }
        top = 0;
        stack[top] = first;
        do{
            int ep = 0;
            for(int i = 1; i < n; i++){
                if(judgeOnLeft(point[stack[top]], point[i], point[ep])){
                    ep = i;
                }
            }
            stack[++top] = ep;
        } while(stack[top] != first);

        System.out.println("凸点数量：" + top);
        for(int i = 0; i < top; i++){
            System.out.println(point[stack[i]]);
        }
    }

    public static void main(String[] args) {
        n = 9;
        point[0] = new POINT(5,5);
        point[1] = new POINT(1,4);
        point[2] = new POINT(0,3);
        point[3] = new POINT(2,2);
        point[4] = new POINT(3,2);
        point[5] = new POINT(4,2);
        point[6] = new POINT(1,1);
        point[7] = new POINT(2,0);
        point[8] = new POINT(4,0);

        jarvis();
    }
}
