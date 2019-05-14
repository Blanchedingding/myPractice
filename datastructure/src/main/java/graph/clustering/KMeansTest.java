package graph.clustering;

import smile.clustering.KMeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KMeansTest {

    //k:要分成多少个部分
    public void kMeans(double[][] G, int k){
        KMeans km = new KMeans(G, k);
        Map<Integer, List<Integer>> clusters = new HashMap<>();
        for(int i = 0; i < km.getNumClusters(); i ++){
            clusters.put(i, new ArrayList<>());
        }
        int[] lables = km.getClusterLabel();
        for(int i = 0; i < lables.length; i++){
            clusters.get(lables[i]).add(i);
        }
        System.out.println(clusters);
    }

    public static void main(String[] args) {
        double G[][] = new double[6][6];
        G[0][1] = 100;
        G[0][2] = 50;
        G[0][3] = 50;
        G[0][4] = 10;
        G[0][5] = 10;
        G[1][2] = 50;
        G[1][3] = 50;
        G[1][4] = 10;
        G[1][5] = 10;
        G[2][3] = 50;
        G[2][4] = 10;
        G[2][5] = 10;
        G[3][4] = 10;
        G[3][5] = 10;
        G[4][5] = 100;

        G[1][0] = 100;
        G[2][0] = 50;
        G[3][0] = 50;
        G[4][0] = 10;
        G[5][0] = 10;
        G[2][1] = 50;
        G[3][1] = 50;
        G[4][1] = 10;
        G[5][1] = 10;
        G[3][2] = 50;
        G[4][2] = 10;
        G[5][2] = 10;
        G[4][3] = 10;
        G[5][3] = 10;
        G[5][4] = 100;
        KMeansTest sw = new KMeansTest();
        sw.kMeans(G, 2);
    }
}
