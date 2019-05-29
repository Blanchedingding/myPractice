package graph.clustering.MarkovChainClustering;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.Map.Entry;

/**
 * Created by VenkataRamesh on 11/28/2016.
 */
public class MCLClustering {

    private String fileName;
    private double[][] transitionMatrix;
    private int expansionParam;
    private double inflationParam;
    private Map<String, Integer> nodeMap;
    private Map<Integer, String> reverseMap;
    private static final int PRECISION = 5;


    public MCLClustering(String fileName, int expansionParm, double inflationParam) throws Exception {
        this.fileName = fileName;
        this.expansionParam = expansionParm;
        this.inflationParam = inflationParam;
    }


    public void generateTransitionMatrix() throws Exception {
        Path filePath = Paths.get("data/", fileName);
        Stream<String> rowList = Files.lines(filePath, StandardCharsets.UTF_8);
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        nodeMap = new HashMap<String, Integer>();
        reverseMap = new HashMap<Integer, String>();
        int rows = lines.size();
        int nodeId = 0;
        for (int i = 0; i < rows; i++) {
            String[] nodes = lines.get(i).split("\\s+");
            if (!nodeMap.containsKey(nodes[0])) {
                nodeMap.put(nodes[0], nodeId);
                reverseMap.put(nodeId, nodes[0]);
                nodeId++;
            }
            if (!nodeMap.containsKey(nodes[1])) {
                nodeMap.put(nodes[1], nodeId);
                reverseMap.put(nodeId, nodes[1]);
                nodeId++;
            }
        }
        //System.out.println(nodeMap);
        this.transitionMatrix = new double[nodeMap.size()][nodeMap.size()];
        for (int i = 0; i < rows; i++) {
            String[] nodes = lines.get(i).split("\\s+");
            int rowIdx = nodeMap.get(nodes[0]);
            int colIdx = nodeMap.get(nodes[1]);
            transitionMatrix[rowIdx][colIdx] = 1;
            transitionMatrix[colIdx][rowIdx] = 1;
        }
    }


    public void runMCL() throws Exception {
        generateTransitionMatrix();
        addSelfLoops();
        normalizeMatrix(transitionMatrix);
        int iterations = 1;
        while (!checkConvergernce(transitionMatrix)) {
            System.out.println("Running MCL iteration: " + iterations);
            double[][] expandedMatrix = expandMatrix(transitionMatrix);
            double[][] inflatedMatrix = inflateMatrix(expandedMatrix);
            pruneMatrix(inflatedMatrix);
            transitionMatrix = inflatedMatrix;
            iterations++;
        }
        //printMatrix(transitionMatrix);
        System.out.println("Markov Chain Clustering converged after: " + (iterations - 1) + " iterations");
        System.out.println("Analyzing clusters and generating file...");
        //generateCLUFile(transitionMatrix, fileName);
        generateClustersAndWriteToFile(transitionMatrix, fileName);
        //printMatrix(transitionMatrix);
    }

    private void addSelfLoops() {
        for (int i = 0; i < transitionMatrix.length; i++) {
            for (int j = 0; j < transitionMatrix.length; j++) {
                if (i == j) {
                    transitionMatrix[i][j] = 1;
                }
            }
        }
    }

    private boolean checkConvergernce(double[][] inputMatrix) {
        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix.length; j++) {
                inputMatrix[i][j] = new BigDecimal(inputMatrix[i][j]).setScale(PRECISION, RoundingMode.HALF_UP).doubleValue();
            }
        }
        int matLen = inputMatrix.length;
        double[][] convergedMatrix = multiplyMatrices(inputMatrix, inputMatrix);
        for (int i = 0; i < convergedMatrix.length; i++) {
            for (int j = 0; j < inputMatrix.length; j++) {
                convergedMatrix[i][j] = new BigDecimal(convergedMatrix[i][j]).setScale(PRECISION, RoundingMode.HALF_UP).doubleValue();
            }
        }
        return Arrays.deepEquals(inputMatrix, convergedMatrix);
    }

    public void pruneMatrix(double[][] inputMatrix) {
        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix.length; j++) {
                if (inputMatrix[i][j] < Math.pow(10, -PRECISION)) {
                    inputMatrix[i][j] = 0.0;
                }
            }
        }
    }

    public void generateClustersAndWriteToFile(double[][] transitionMatrix, String fileName) throws Exception {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("output/" + fileName.split("\\.")[0] + ".clu"));
            fw.write("*Vertices " + String.valueOf(transitionMatrix.length));
            HashMap<String, Integer> clusterMap = new HashMap<String, Integer>();
            List<String> clusterList = new ArrayList<String>();
            int clusterId = 0;
            for (int i = 0; i < transitionMatrix.length; i++) {
                List<Integer> verticesList = new ArrayList<Integer>();
                for (int j = 0; j < transitionMatrix.length; j++) {
                    if (transitionMatrix[i][j] > 0) {
                        verticesList.add(j);
                    }
                }
                if (verticesList.size() > 0) {
                    String vertexIds = verticesList.stream().map(x -> x.toString()).collect(Collectors.joining());
                    if (!clusterList.contains(vertexIds)) {
                        clusterList.add(vertexIds);
                        clusterId++;
                        for (Integer index : verticesList) {
                            if (!clusterMap.containsKey(reverseMap.get(index))) {
                                clusterMap.put(reverseMap.get(index), clusterId);
                            }
                        }
                    }
                }
            }
            System.out.println("No. of Clusters generated: " + clusterId);
            Path filePath = Paths.get("data/files_for_pajek/", fileName.split("\\.")[0] + ".net");
            Stream<String> rowList = Files.lines(filePath, StandardCharsets.UTF_8);
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String singleLine : lines) {
                if (singleLine.contains("\"")) {
                    int startIndex = singleLine.indexOf("\"");
                    int endIndex = singleLine.lastIndexOf("\"");
                    String vertexId = singleLine.substring(startIndex + 1, endIndex);
                    fw.write(System.lineSeparator());
                    fw.write(clusterMap.get(vertexId).toString());
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            fw.close();
        }


    }

    public double[][] expandMatrix(double[][] inputMatrix) {
        int matLen = inputMatrix.length;
        double[][] expandedMatrix = new double[inputMatrix.length][inputMatrix.length];
        for (int i = 0; i < matLen; i++) {
            for (int j = 0; j < matLen; j++) {
                expandedMatrix[i][j] = inputMatrix[i][j];
            }
        }
        for (int i = 1; i < expansionParam; i++) {
            expandedMatrix = multiplyMatrices(expandedMatrix, inputMatrix);
        }
        return expandedMatrix;
    }

    public double[][] multiplyMatrices(double[][] mat1, double[][] mat2) {
        double[][] newMat = new double[mat1.length][mat1.length];
        int matLen = newMat.length;
        for (int i = 0; i < matLen; i++) {
            for (int j = 0; j < matLen; j++) {
                for (int l = 0; l < matLen; l++) {
                    newMat[i][j] += (mat1[i][l]) * (mat2[l][j]);
                }
            }
        }
        return newMat;
    }


    public double[][] inflateMatrix(double[][] expandedMatrix) {
        int matLen = expandedMatrix.length;
        double[][] inflatedMatrix = new double[matLen][matLen];
        for (int i = 0; i < matLen; i++) {
            for (int j = 0; j < matLen; j++) {
                inflatedMatrix[i][j] = Math.pow(expandedMatrix[i][j], inflationParam);
            }
        }
        normalizeMatrix(inflatedMatrix);
        return inflatedMatrix;
    }

    public void normalizeMatrix(double[][] inputMatrix) {
        for (int j = 0; j < inputMatrix.length; j++) {
            double colSum = 0;
            for (int i = 0; i < inputMatrix.length; i++) {
                colSum += inputMatrix[i][j];
            }
            for (int i = 0; i < inputMatrix.length; i++) {
                inputMatrix[i][j] = inputMatrix[i][j] / colSum;
            }
        }
    }

    public void printMatrix(double[][] inMatrix) {
        int matLen = inMatrix.length;
        for (int i = 0; i < matLen; i++) {
            for (int j = 0; j < matLen; j++) {
                System.out.print(inMatrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}

