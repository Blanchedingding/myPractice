package graph.clustering;

import smile.clustering.SpectralClustering;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixComparator {

    final int N = 22;

    public boolean readMatrix(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            double[][][] x = new double[2][N][N];
            int i = 0;
            int k = 0;
            while ((line = reader.readLine()) != null ) {
                if(line.trim().split(" ").length < 22) {
                    if(k == 0){
                        k ++;
                        i = 0;
                    }
                    continue;
                }
                String[] d = line.split(" ");
                for(int j = 0; j < 22; j++){
                    x[k][i][j] = Double.valueOf(d[j]);
                }
                i++;
            }
            printG(x[0]);
            cluster(x[0], 4);
            cluster(x[0], 4);
            System.out.println("-------------------------------------");
            printG(x[1]);
            return compare(x[0],x[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void cluster(double[][] G, int k){
        Map<Integer, List<Integer>> clusters = new HashMap<>();
        SpectralClustering sc = new SpectralClustering(G, k);
        for(int i = 0; i < sc.getNumClusters(); i ++){
            clusters.put(i, new ArrayList<>());
        }
        int[] labels = sc.getClusterLabel();
        for(int i = 0; i < labels.length; i++){
            clusters.get(labels[i]).add(i);
        }
        System.out.println("----clusters:----");
        System.out.println(clusters);
    }

    private void printG(double[][] G){
        int n = G.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n;j++){
                System.out.print(G[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean compare(double[][] a, double[][] b){
        for(int i = 0; i < N; i ++){
            for (int j = 0; j < N; j++){
                if(a[i][j] != b[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MatrixComparator m = new MatrixComparator();
        System.out.println(m.readMatrix("F:\\gitwork\\myPractice\\datastructure\\src\\main\\java\\graph\\clustering\\matrix.txt"));
    }
}
