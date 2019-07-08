package test.concurrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolTest{

    public static void main(String[] args) {
        File directory = new File("F:\\C++ Project");
        String keyWords = "namespace";
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<Integer> future = pool.submit(new ReadFileTask(directory, keyWords, pool));
        try {
            System.out.println(future.get() + " files match");
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pool.shutdown();
        ThreadPoolExecutor executor = (ThreadPoolExecutor)pool;
        System.out.println("Largest Pool Size: " + executor.getLargestPoolSize());
    }

}

class ReadFileTask implements Callable<Integer>{

    private File directory;
    private String keyWords;
    private ExecutorService pool;

    public ReadFileTask(File directory, String keyWords, ExecutorService pool) {
        super();
        this.directory = directory;
        this.keyWords = keyWords;
        this.pool = pool;
    }

    @Override
    public Integer call() throws Exception {
        File[] files = directory.listFiles();
        List<Future<Integer>> result = new ArrayList<>();
        int count = 0;
        for(File f : files){
            if(f.isDirectory()){
                Future<Integer> future = pool.submit(new ReadFileTask(f, keyWords, pool));
                result.add(future);
            }else{
                if(search(f)) ++count;
            }
        }

        for(Future<Integer> f : result){
            count += f.get();
        }
        return count;
    }

    public boolean search(File f) throws FileNotFoundException {
        try(Scanner in = new Scanner(f)){
            boolean found = false;
            while(!found & in.hasNextLine()){
                String line = in.nextLine();
                if(line.contains(keyWords)){
                    found = true;
                }
            }
            return found;
        }
    }

}

