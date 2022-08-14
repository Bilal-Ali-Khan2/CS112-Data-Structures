package prereqchecker;

import java.util.*;

/**
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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
    StdIn.setFile(args[0]);
        int line = Integer.parseInt(StdIn.readLine());
        ArrayList<ArrayList<String>> adjlist = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < line ; i++){
            ArrayList<String> arraylist = new ArrayList<String>();
            arraylist.add(StdIn.readLine());
            adjlist.add(arraylist);
        }
        line = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < line; i++){
            adjlist.get(find(StdIn.readString(),adjlist)).add(StdIn.readString());
        }

        StdIn.setFile(args[1]);
        String t  = StdIn.readLine();
        line = Integer.parseInt(StdIn.readLine());
        Set<String> set = new HashSet<>();
        for(int i = 0; i < line; i++){
            addpre(StdIn.readLine(), set, adjlist);
        }
        Set<String> finish = new HashSet<>();
        addpre(t, finish, adjlist);

        ArrayList<ArrayList<String>> param = new ArrayList<ArrayList<String>>();
        while(!eligible(set,adjlist).contains(t)){
            Boolean reg = false;
            ArrayList<String> hre = new ArrayList<String>();
            for (String i : finish) {
                for (String f : eligible(set,adjlist)) {
                    if(f.equals(i)){
                        hre.add(f);
                        reg = true;
                    }
                }
            }
            if(reg){
                param.add(hre);
                for(int i = 0; i < hre.size(); i++){
                    set.add(hre.get(i));
                }
            }
        }

        StdOut.setFile(args[2]);
        StdOut.println(param.size());
        for(int i  = 0; i < param.size(); i++){
            for(int f = 0; f < param.get(i).size(); f++){
                StdOut.print(param.get(i).get(f) + " ");
            }
            StdOut.println();
        }
    }
    
    private static Set<String> eligible(Set<String> ClassReg, ArrayList<ArrayList<String>> adjlist){
        Set<String> Eligible = new HashSet<>();
        for(int i = 0; i < adjlist.size(); i++){
            if(!ClassReg.contains(adjlist.get(i).get(0))){
                int j = 1;
                boolean ptr = true;
                while(j < adjlist.get(i).size()){
                    if(!ClassReg.contains(adjlist.get(i).get(j))){
                        ptr= false;
                    }
                    j++;
                }
                if(ptr){
                    Eligible.add(adjlist.get(i).get(0));
                }
            }
        }
        return Eligible;
    }

    private static void addpre(String course, Set<String> ClassReg, ArrayList<ArrayList<String>> adjlist){
        if(adjlist.get(find(course,adjlist)).size() == 1){
            if(!ClassReg.contains(course)){
              ClassReg.add(course);
            }
        }
        else{
          if(!ClassReg.contains(course)){
              ClassReg.add(course);
            }
            for(int i = 1;i < adjlist.get(find(course,adjlist)).size(); i++){
              addpre(adjlist.get(find(course,adjlist)).get(i), ClassReg, adjlist);
            }
        }
  }

  private static int find(String fill, ArrayList<ArrayList<String>> adj){
      for(int i = 0; i < adj.size(); i++){
          if(adj.get(i).get(0).equals(fill)){
              return i;
          }
      }
      return -1;

    }
}
