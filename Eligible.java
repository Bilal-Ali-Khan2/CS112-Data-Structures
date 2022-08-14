package prereqchecker;

import java.util.*;

/**
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
    StdIn.setFile(args[0]);
        int param = Integer.parseInt(StdIn.readLine());
        ArrayList<ArrayList<String>> adjlist = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < param ; i++){
            ArrayList<String> ol = new ArrayList<String>();
            ol.add(StdIn.readLine());
            adjlist.add(ol);
        }
        int parser = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < parser; i++){
            adjlist.get(find(StdIn.readString(),adjlist)).add(StdIn.readString());
        }
        Set<String> set = new HashSet<>();
        StdIn.setFile(args[1]);
        parser = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < parser; i++){
            addpre(StdIn.readLine(), set, adjlist);
        }
        StdOut.setFile(args[2]);
        for(int i = 0; i < adjlist.size(); i++){
            if(!set.contains(adjlist.get(i).get(0))){
                int j = 1;
                boolean cursor = true;
                while(j < adjlist.get(i).size()){
                    if(!set.contains(adjlist.get(i).get(j))){
                        cursor = false;
                    }
                    j++;
                }
                if(cursor){
                    StdOut.println(adjlist.get(i).get(0));
                }
            }
        }
	    
    }

    private static void addpre(String fill, Set<String> param, ArrayList<ArrayList<String>> adjlist){
          if(adjlist.get(find(fill,adjlist)).size() == 1){
              if(!param.contains(fill)){
                param.add(fill);
              }
          }
          else{
            if(!param.contains(fill)){
                param.add(fill);
              }
              for(int i = 1;i < adjlist.get(find(fill,adjlist)).size(); i++){
                addpre(adjlist.get(find(fill,adjlist)).get(i), param, adjlist);
              }
          }
    }

    private static int find(String fill, ArrayList<ArrayList<String>> adjlist){
        for(int i = 0; i < adjlist.size(); i++){
            if(adjlist.get(i).get(0).equals(fill)){
                return i;
            }
        }
        return -1;
    }
}
