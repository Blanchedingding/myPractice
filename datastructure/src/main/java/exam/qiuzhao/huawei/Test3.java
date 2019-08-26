package exam.qiuzhao.huawei;

import java.util.*;

public class Test3 {

    static Set<String> minList = new HashSet<String>();
    static int minLength = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.valueOf(sc.nextLine());
        int M = Integer.valueOf(sc.nextLine());
        List<Edge> list = new ArrayList<>();
        Map<String, Integer> in = new HashMap<>();
        Map<String, Integer> out = new HashMap<>();
        Set<String> vs = new HashSet<>();
        for(int i = 0; i < M; i++){
            String[] s = sc.nextLine().split(",");
            list.add(new Edge(s[0], s[1], s[2], Integer.valueOf(s[3])));
            out.putIfAbsent(s[0], 0);
            out.put(s[0], out.get(s[0]) + 1);
            in.putIfAbsent(s[1], 0);
            in.put(s[1], in.get(s[1]) + 1);
            vs.add(s[0]);
            vs.add(s[1]);
        }
        Set<String> removeV = new HashSet<>();
        while(true){
            removeV.clear();
            for(String v: vs){
                if(!in.containsKey(v) || !out.containsKey(v)){
                    removeV.add(v);
                }
            }
            if(removeV.size() <= 0) break;
            List<Edge> newList = new ArrayList<>();
            for(Edge e: list){
                if(!removeV.contains(e.source) && !removeV.contains(e.target)){
                    newList.add(e);
                } else {
                    if(out.get(e.source) - 1 <= 0){
                        out.remove(e.source);
                    } else {
                        out.put(e.source, out.get(e.source) - 1);
                    }
                    if(in.get(e.target) - 1 <= 0){
                        in.remove(e.target);
                    } else {
                        in.put(e.target, in.get(e.target) - 1);
                    }
                }
            }
            Set<String> newVs = new HashSet<>();
            for(String s: vs){
                if(!removeV.contains(s)){
                    newVs.add(s);
                }
            }
            vs = newVs;
            list = newList;
        }
//        for(Edge e: list){
//            System.out.println(e.name);
//        }
        if(list.size() == 0) {
            System.out.println("#");
            return;
        }
        Collections.sort(list, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.length - o2.length;
            }
        });

        helper(vs, list, new HashSet<>(), 0);
        List<String> result = new ArrayList<>();
        for(String s: minList){
            result.add(s);
        }
        Collections.sort(result);
        for(String s: result){
            System.out.println(s);
        }

    }

    public static void helper(Set<String> vs, List<Edge> list, Set<String> removeEdge, int curLength){
        if(!hasLoop(vs, list)){
            if(curLength < minLength){
                minLength = curLength;
                minList = new HashSet<>(removeEdge);
            }
            return;
        }
        for(Edge e: list){
            List<Edge> newlist = new ArrayList<>(list);
            newlist.remove(e);
            removeEdge.add(e.name);
            helper(vs, newlist, removeEdge, curLength + e.length);
            removeEdge.remove(e.name);
        }
    }

    public static boolean hasLoop(Set<String> oldvs, List<Edge> oldlist){
        Map<String, Integer> in = new HashMap<>();
        Map<String, Integer> out = new HashMap<>();
        Set<String> vs = new HashSet<>(oldvs);
        List<Edge> list = new ArrayList<>(oldlist);
        for(Edge e: list){
            out.putIfAbsent(e.source, 0);
            out.put(e.source, out.get(e.source) + 1);
            in.putIfAbsent(e.target, 0);
            in.put(e.target, in.get(e.target) + 1);
        }
        Set<String> removeV = new HashSet<>();
        while(true){
            removeV.clear();
            for(String v: vs){
                if(!in.containsKey(v) || !out.containsKey(v)){
                    removeV.add(v);
                }
            }
            if(removeV.size() <= 0) break;
            List<Edge> newList = new ArrayList<>();
            for(Edge e: list){
                if(!removeV.contains(e.source) && !removeV.contains(e.target)){
                    newList.add(e);
                } else {
                    if(out.get(e.source) - 1 <= 0){
                        out.remove(e.source);
                    } else {
                        out.put(e.source, out.get(e.source) - 1);
                    }
                    if(in.get(e.target) - 1 <= 0){
                        in.remove(e.target);
                    } else {
                        in.put(e.target, in.get(e.target) - 1);
                    }
                }
            }
            Set<String> newVs = new HashSet<>();
            for(String s: vs){
                if(!removeV.contains(s)){
                    newVs.add(s);
                }
            }
            vs = newVs;
            list = newList;
        }
        return list.size() > 0;
    }

    static  class Edge{
        String source;
        String target;
        String name;
        int length;
        public Edge(String source, String target, String name, int length){
            this.source = source;
            this.target = target;
            this.name = name;
            this.length = length;
        }
    }
}
