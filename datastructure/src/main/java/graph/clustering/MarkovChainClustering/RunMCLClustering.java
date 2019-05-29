package graph.clustering.MarkovChainClustering;

import java.util.Scanner;

/**
 * Created by VenkataRamesh on 11/28/2016.
 */
public class RunMCLClustering {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Select dataset from the options below: ");
        System.out.println("Type '1' for : attweb_net.txt");
        System.out.println("Type '2' for : physics_collaboration_net.txt");
        System.out.println("Type '3' for : yeast_undirected_metabolic.txt");
        int option  = 0;
        while(true)
        try{
            option = sc.nextInt();
            if(option<1||option>3){
                System.out.println("Enter valid option");
            }
            else{
                break;
            }
        }
        catch(Exception ex){
            System.out.println("Enter valid option");
        }
      String fileName = null;
        if(option==1){
            fileName = "attweb_net.txt";
        }
        else if(option==2){
            fileName = "physics_collaboration_net.txt";
        }
        else{
            fileName = "yeast_undirected_metabolic.txt";
        }
        if (fileName == null || fileName.length() == 0) fileName = "attweb_net.txt";
        System.out.println("Enter expansion parameter, e: ");
        int e = sc.nextInt();
        System.out.println("Enter inflation parameter, r: ");
        double r = sc.nextDouble();
        String filePath = "data/";
        MCLClustering mcl = new MCLClustering(fileName, e, r);
        mcl.runMCL();
    }
}
