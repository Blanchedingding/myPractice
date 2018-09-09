package graph.fordfulkerson;

public class Main {

    public static void main(String[] args){
        test();
    }

    private static void test(){
        Graph graph = new Graph(6);
        Edge[] edges = new Edge[9];

        edges[0] = new Edge(0,1,0,16);
        edges[1] = new Edge(0,2,0,13);
        edges[2] = new Edge(1,3,0,12);
        edges[3] = new Edge(2,1,0,4);
        edges[4] = new Edge(2,4,0,14);
        edges[5] = new Edge(3,2,0,9);
        edges[6] = new Edge(3,5,0,20);
        edges[7] = new Edge(4,3,0,7);
        edges[8] = new Edge(4,5,0,4);

        for(int i = 0;i<9;i++)
            graph.insertEdge(edges[i]);

        graph.MaxFlow();
        graph.showResult();
    }

    public static void test2(){
        Graph graph = new Graph(6);

        Edge[] edges = new Edge[9];
        edges[0] = new Edge(0,1,4,16);
        edges[1] = new Edge(0,2,0,13);
        edges[2] = new Edge(1,3,4,12);
        edges[3] = new Edge(2,1,0,4);
        edges[4] = new Edge(2,4,4,14);
        edges[5] = new Edge(3,2,4,9);
        edges[6] = new Edge(3,5,0,20);
        edges[7] = new Edge(4,3,0,7);
        edges[8] = new Edge(4,5,4,4);

        for(int i = 0;i<9;i++)
            graph.insertEdge(edges[i]);

        graph.bianli();

        graph.MaxFlow();
        graph.bianli();
    }
}

