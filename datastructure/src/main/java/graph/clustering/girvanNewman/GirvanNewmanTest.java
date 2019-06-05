package graph.clustering.girvanNewman;

import cz.cvut.fit.krizeji1.girvan_newman.GirvanNewmanClusterer;
import org.gephi.clustering.api.Cluster;
import org.gephi.graph.api.*;
import org.gephi.project.api.ProjectController;
import org.openide.util.Lookup;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GirvanNewmanTest {

    public static void main(String[] args) {
        // Given
        Lookup lookup = Lookup.getDefault();
        ProjectController pc = lookup.lookup(ProjectController.class);
        pc.newProject();
        @SuppressWarnings("unused")
//		Workspace workspace = pc.getCurrentWorkspace();
        GraphController controller = Lookup.getDefault().lookup(GraphController.class);
        GraphModel model = controller.getModel();
        UndirectedGraph graph = model.getUndirectedGraph();
        // Add nodes and edges
        double G[][] =
                {{0.0 ,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 12.0 ,12.0, 452.0, 0.0 ,0.0 ,0.0, 0.0 ,0.0 ,0.0 ,0.0, 0.0 ,0.0, 12.0, 12.0},
                        {0.0, 0.0, 20070.0, 1510.0, 150.0, 150.0 ,150.0 ,0.0 ,1834.0, 200.0, 200.0, 0.0 ,1100.0, 6550.0 ,18010.0, 2160.0, 400.0, 3200.0, 1100.0, 0.0, 0.0 ,0.0},
                        {0.0 ,20070.0, 0.0 ,1860.0 ,680.0 ,150.0, 290.0 ,0.0, 1344.0 ,200.0, 200.0, 0.0, 1100.0 ,7050.0, 17410.0 ,2160.0 ,400.0 ,50.0 ,50.0 ,140.0 ,0.0 ,0.0},
                        {0.0 ,1510.0 ,1860.0, 0.0 ,225.0, 345.0 ,225.0, 0.0 ,164.0, 164.0 ,1240.0 ,0.0 ,0.0 ,0.0 ,0.0, 0.0, 0.0, 0.0 ,0.0 ,0.0, 0.0, 0.0},
                        {0.0 ,150.0, 680.0 ,225.0, 0.0 ,150.0 ,290.0, 0.0 ,124.0 ,124.0, 904.0, 0.0 ,0.0 ,0.0, 0.0,0.0, 0.0, 0.0, 0.0, 140.0, 0.0 ,0.0},
                        {0.0, 150.0, 150.0, 345.0 ,150.0 ,0.0 ,150.0 ,0.0, 14.0 ,14.0 ,464.0, 0.0, 0.0, 0.0 ,0.0 ,0.0 ,0.0 ,0.0 ,0.0, 0.0 ,0.0, 0.0},
                        {0.0 ,150.0, 290.0, 225.0, 290.0 ,150.0 ,0.0 ,0.0 ,12.0, 12.0, 784.0 ,0.0 ,0.0 ,0.0, 0.0, 0.0, 0.0, 0.0 ,0.0,140.0 ,0.0 ,0.0},
                        {0.0, 0.0 ,0.0 ,0.0 ,0.0 ,0.0 ,0.0, 0.0 ,12.0 ,12.0, 354.0 ,0.0 ,0.0, 0.0 ,0.0, 0.0 ,0.0 ,0.0, 0.0, 0.0, 0.0, 0.0},
                        {12.0 ,1834.0, 1344.0 ,164.0, 124.0 ,14.0, 12.0, 12.0 ,0.0, 2170.0, 2584.0, 50.0 ,220.0 ,300.0 ,420.0 ,540.0 ,300.0 ,650.0, 50.0 ,12.0 ,0.0 ,0.0},
                        {12.0, 200.0, 200.0, 164.0 ,124.0 ,14.0 ,12.0, 12.0 ,2170.0 ,0.0 ,2584.0, 50.0 ,220.0 ,300.0 ,420.0, 540.0, 300.0 ,650.0, 50.0 ,12.0, 0.0 ,0.0},
                        {452.0 ,200.0, 200.0, 1240.0, 904.0 ,464.0, 784.0, 354.0 ,2584.0, 2584.0 ,0.0, 470.0, 220.0, 990.0, 1430.0 ,1210.0, 290.0, 650.0, 0.0 ,342.0 ,12.0, 12.0},
                        {0.0, 0.0 ,0.0, 0.0 ,0.0 ,0.0, 0.0, 0.0 ,50.0, 50.0 ,470.0, 0.0 ,0.0, 0.0, 0.0 ,0.0, 0.0, 0.0 ,0.0, 0.0 ,0.0 ,0.0},
                        {0.0 ,1100.0, 1100.0 ,0.0, 0.0 ,0.0, 0.0 ,0.0, 220.0 ,220.0, 220.0 ,0.0 ,0.0 ,0.0, 0.0 ,0.0 ,0.0 ,0.0 ,0.0 ,0.0 ,0.0, 0.0},
                        {0.0 ,6550.0, 7050.0 ,0.0 ,0.0 ,0.0 ,0.0, 0.0 ,300.0 ,300.0 ,990.0, 0.0, 0.0 ,0.0, 7050.0, 300.0, 150.0 ,50.0, 50.0 ,0.0, 0.0 ,0.0},
                        {0.0 ,18010.0 ,17410.0 ,0.0 ,0.0, 0.0 ,0.0, 0.0, 420.0 ,420.0, 1430.0 ,0.0 ,0.0, 7050.0 ,0.0 ,2160.0, 400.0 ,50.0 ,1100.0 ,0.0, 0.0 ,0.0},
                        {0.0, 2160.0 ,2160.0, 0.0, 0.0 ,0.0 ,0.0 ,0.0, 540.0 ,540.0 ,1210.0, 0.0 ,0.0 ,300.0 ,2160.0 ,0.0, 400.0, 50.0, 50.0 ,0.0, 0.0 ,0.0},
                        {0.0 ,400.0, 400.0, 0.0, 0.0, 0.0 ,0.0 ,0.0, 300.0, 300.0 ,290.0 ,0.0, 0.0 ,150.0 ,400.0 ,400.0 ,0.0 ,50.0, 50.0 ,0.0 ,0.0, 0.0},
                        {0.0, 3200.0 ,50.0 ,0.0 ,0.0 ,0.0, 0.0, 0.0, 650.0 ,650.0 ,650.0, 0.0, 0.0, 50.0, 50.0, 50.0 ,50.0 ,0.0 ,50.0 ,0.0 ,0.0, 0.0},
                        {0.0 ,1100.0 ,50.0, 0.0 ,0.0 ,0.0 ,0.0 ,0.0 ,50.0, 50.0 ,0.0, 0.0 ,0.0 ,50.0, 1100.0, 50.0 ,50.0, 50.0, 0.0 ,0.0 ,0.0, 0.0},
                        {0.0, 0.0 ,140.0, 0.0 ,140.0 ,0.0 ,140.0 ,0.0 ,12.0 ,12.0 ,342.0 ,0.0 ,0.0 ,0.0, 0.0 ,0.0, 0.0 ,0.0 ,0.0 ,0.0, 0.0 ,0.0},
                        {12.0, 0.0 ,0.0, 0.0 ,0.0, 0.0, 0.0, 0.0 ,0.0, 0.0, 12.0, 0.0 ,0.0, 0.0 ,0.0, 0.0, 0.0 ,0.0 ,0.0, 0.0, 0.0 ,12.0},
                        {12.0, 0.0 ,0.0 ,0.0 ,0.0, 0.0 ,0.0, 0.0, 0.0 ,0.0 ,12.0 ,0.0 ,0.0, 0.0 ,0.0, 0.0, 0.0, 0.0 ,0.0, 0.0, 12.0, 0.0}};
//        calculateAffinity(G);
        List<Node> nodeList = new LinkedList<>();
        for(int i = 0; i < G.length; i++){
            nodeList.add(createNode(model, graph));
        }
        for(int i = 0; i < G.length; i++){
            for(int j = 0; j < G.length; j++){
                if(i != j && G[i][j] >0){
                    createEdge(model, graph, nodeList.get(i), nodeList.get(j), (float) G[i][j]);
                }
            }
        }
//        Node a = createNode(model, graph);
//        Node b = createNode(model, graph);
//        Node c = createNode(model, graph);
//        Node d = createNode(model, graph);
//        Node e = createNode(model, graph);
//        Node f = createNode(model, graph);
//        createEdge(model, graph, d, b, 2f);
//        createEdge(model, graph, b, c, 2f);
//        createEdge(model, graph, d, c, 2f);
//        createEdge(model, graph, a, d, 1f);
//        createEdge(model, graph, e, a, 1f);
//        createEdge(model, graph, f, a, 3f);
//        createEdge(model, graph, f, e, 2f);
        // When
        GirvanNewmanClusterer clusterer = new GirvanNewmanClusterer();
        clusterer.setPreferredNumberOfClusters(7);
        clusterer.execute(model);
        // Then
        Cluster[] clusters = clusterer.getClusters();
        for(int i = 0; i < clusters.length; i++){
            System.out.print(i + ": ");
            Arrays.stream(clusters[i].getNodes()).forEach(n -> System.out.print(n.getId() + " "));
            System.out.println();
        }
    }

    private static void createEdge(GraphModel model, UndirectedGraph graph, Node a, Node b, float weight) {
        Edge edge = model.factory().newEdge(a, b, weight, false);
        graph.addEdge(edge);
    }

    private static Node createNode(GraphModel model, UndirectedGraph graph) {
        Node node = model.factory().newNode();
        graph.addNode(node);
        return node;
    }

    private static void calculateAffinity(double[][] G){
        for(int i = 0; i < G.length; i++){
            int sum = 0;
            for(int j = 0; j < G.length; j++){
                sum += G[i][j];
            }
            if(sum > 0){
                for(int j = 0; j < G.length; j++){
                    G[i][j] = G[i][j] / sum;
                }
            }
        }
    }

}
