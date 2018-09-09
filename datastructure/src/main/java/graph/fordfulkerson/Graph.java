package graph.fordfulkerson;

import java.util.LinkedList;

public class Graph {

    private int vNum;
    private int eNum;
    private Gf gf;
    private LinkedList<Edge>[] GLists;

    public Graph(int n){
        vNum = n;
        eNum = 0;
        GLists = new LinkedList[n];

        for(int i = 0;i<n;i++)
            GLists[i] = new LinkedList<>();
    }

    public void insertEdge(Edge e){
        int v1 = e.getV1();
        GLists[v1].add(e);
        eNum++;
    }

//	public void deleteEdge(Edge e){
//		int v1 = e.getV1();
//		int v2 = e.getV1();
//
//	}

    public void produceGf(){
        gf = new Gf(vNum);

        for(int i = 0;i<vNum;i++){
            LinkedList<Edge> list = (LinkedList<Edge>) GLists[i].clone();

            while(!list.isEmpty()){
                Edge edge = list.pop();
                int v1 = edge.getV1();
                int v2 = edge.getV2();
                int flow = edge.getFlow();
                int capacity = edge.getCapacity();

                if(flow==0){
                    gf.insertEdge(new Edge2(v1,v2,capacity));
                }else{
                    if(flow==capacity){
                        gf.insertEdge(new Edge2(v2,v1,capacity));
                    }else if(flow<capacity){
                        gf.insertEdge(new Edge2(v1,v2,capacity-flow));
                        gf.insertEdge(new Edge2(v2,v1,flow));
                    }
                }
            }
        }
    }

    public Gf getGf(){
        return gf;
    }

    private LinkedList<Integer> augmentingPath(){
        return gf.augmentingPath();
    }

    private int changeNum(LinkedList<Integer> list){
        return gf.changeNum(list);
    }

    /**
     * 最大流
     */
    public void MaxFlow(){
        produceGf();
        gf.bianli();
        LinkedList<Integer> list = augmentingPath();

        while(list.size()>0){
            int changenum = changeNum(list);
            LinkedList<Integer> copylist = (LinkedList<Integer>) list.clone();//调试
            System.out.println("list:");
            while(!copylist.isEmpty()){
                System.out.print(copylist.pop()+"  ");
            }
            System.out.println();
            System.out.println("changenum: "+changenum);

            int v1 = 0;
            for(int i = 1;i<list.size();i++){
                int v2 = list.get(i);
                if(!GLists[v1].isEmpty()){
                    int j = 0;
                    Edge e = GLists[v1].get(j);
                    while(e.getV2()!=v2 && j<GLists[v1].size()){
                        e = GLists[v1].get(j);
                        j++;
                    }
                    if(e.getV2()!=v2 && j==GLists[v1].size()){//调试,反向返流
                        j = 0;
                        e = GLists[v2].get(j);
                        while(e.getV2()!=v1 && j<GLists[v2].size()){
                            e = GLists[v2].get(j);
                            j++;
                        }
                    }
                    e.setFlow(e.getFlow()+changenum);
                }
                v1  = v2;
            }
            bianli();
            produceGf();
            gf.bianli();
            list = augmentingPath();
        }
    }

    public void bianli(){
        System.out.println("共有 "+vNum+" 个顶点， "+eNum+" 条边");
        for(int i = 0;i<vNum;i++){
            if(GLists[i].size()==0)
                continue;
            for(int j = 0;j<GLists[i].size();j++){
                Edge e = GLists[i].get(j);
                System.out.println("[ "+e.getV1()+" , "+e.getV2()+" , "+e.getFlow()+" , "+e.getCapacity()+" ]");
            }
        }
    }

    public void showResult(){
        bianli();
        int maxflow = 0;
        for(int i = 0;i<vNum;i++){
            if(GLists[i].size()>0){
                for(int j = 0;j<GLists[i].size();j++){
                    if(GLists[i].get(j).getV2() == vNum-1){
                        maxflow += GLists[i].get(j).getFlow();
                    }
                }
            }
        }
        System.out.println("最大流为 "+maxflow);
    }

}

