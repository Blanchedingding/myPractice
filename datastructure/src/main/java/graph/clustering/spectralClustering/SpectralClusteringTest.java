package graph.clustering.spectralClustering;

import smile.clustering.SpectralClustering;

import java.util.*;

public class SpectralClusteringTest {

    //k:要分成多少个部分
    public void spectralClustering(double[][] G, int k){
        SpectralClustering sc = new SpectralClustering(G, k);
        Map<Integer, List<Integer>> clusters = new HashMap<>();
        for(int i = 0; i < sc.getNumClusters(); i ++){
            clusters.put(i, new ArrayList<>());
        }
        int[] lables = sc.getClusterLabel();
        for(int i = 0; i < lables.length; i++){
            clusters.get(lables[i]).add(i);
        }
        System.out.println(clusters);
    }

    public static void main(String[] args) {
//        double G[][] = new double[8][8];
//        G[0][1] = 3;
//        G[0][2] = 3;
//        G[0][3] = 3;
//        G[1][2] = 3;
//        G[1][3] = 3;
//        G[1][7] = 1;
//        G[2][3] = 3;
//        G[4][5] = 3;
//        G[4][6] = 2;
//        G[4][7] = 2;
//        G[5][6] = 2;
//        G[5][7] = 2;
//
//        G[1][0] = 3;
//        G[2][0] = 3;
//        G[3][0] = 3;
//        G[2][1] = 3;
//        G[3][1] = 3;
//        G[7][1] = 1;
//        G[3][2] = 3;
//        G[5][4] = 3;
//        G[6][4] = 2;
//        G[7][4] = 2;
//        G[6][5] = 2;
//        G[7][5] = 2;

        long begin = System.currentTimeMillis();
        int size = 100;
        double[][] G = new double[size][size];
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                G[i][j] = Math.random ();
                G[j][i] = G[i][j];
//				double random = Math.random();
//				if(random > 0.5){
//					G[i][j] = Math.random ();
//				}
            }
        }

        SpectralClusteringTest sw = new SpectralClusteringTest();
        sw.spectralClustering(G, 10);

        long end = System.currentTimeMillis();
        System.out.println("cost " + (end - begin) + " ms");
    }


}
