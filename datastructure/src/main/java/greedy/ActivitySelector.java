package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 *  假定一个有n个活动(activity)的集合S={a1,a2,....,an}，这些活动使用同一个资源（例如同一个阶梯教室），
 *  而这个资源在某个时刻只能供一个活动使用。每个活动ai都有一个开始时间si和一个结束时间fi，其中0<=si<fi<正无穷。
 *  如果被选中，任务ai发生在半开时间区间[si,fi)期间。
 *  如果两个活动ai和aj满足[si,fi)和[sj,fj)不重叠，则称它们是兼容的。也就说，若si>=fj或sj>=fi，则ai和aj是兼容的。
 *  在活动选择问题中，我们希望选出一个最大兼容活动集。
 *  假定活动已按结束时间fi的单调递增顺序排序：f1<=f2<=f3<=f4<=...<=fn-1<=fn
 */
public class ActivitySelector {

    List<String> a1 = new ArrayList<String>();

    //递归贪心
    public void recursiveActivitySelector(double[] s, double[] f, int k, int n){
        int m = k+1;
        //找到开始时间在前一个活动之后且最早结束的活动序号
        while(m <= n && s[m] < f[k]){
            m ++;
        }
        if(m <= n){
            a1.add("a" + m);
            recursiveActivitySelector(s,f,m,n);
        }
    }


    //迭代贪心
    public List<String> greedyActivitySelector(double[] s, double[] f){
        List<String> a2 = new ArrayList<String>();
        int k = 0;
        int n = s.length -1;
        for(int m = 1; m <= n; m++){
            if(s[m] >= f[k]){
                a2.add("a" + m);
                k = m ;
            }
        }
        return a2;
    }

    public static void main(String[] args) {
        //开始和结束时间的数组，已经按结束时间排序
        //第一个为0，下标从1开始
        double[] s = new double[]{0, 1,3,0,5,3,5,6,8,8,2,12};
        double[] f = new double[]{0,4,5,6,7,9,9,10,11,12,14,16};

        ActivitySelector as = new ActivitySelector();
        as.recursiveActivitySelector(s,f,0,s.length-1);
        System.out.println("递归贪心选取的最大兼容活动子集为：");
        System.out.println(as.a1);

        List<String> a2 = as.greedyActivitySelector(s,f);
        System.out.println("迭代贪心选取的最大兼容活动子集为：");
        System.out.println(a2);


    }
}
