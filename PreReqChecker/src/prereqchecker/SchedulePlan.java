package prereqchecker;

import java.util.*;
//BILAL KHAN
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

	
    HashMap<String, ArrayList<String>> CG = getG(args[0]);

    StdIn.setFile(args[1]);

    String tr = StdIn.readString();

    String c = StdIn.readString();
    
    
    
    int part = 0;
    
    //String dere = StdIn.readString();
    //int part1
    //int part 2
    //int [ae]
    
    
    
    
    
    HashMap<Integer, ArrayList<String>> top = new HashMap<Integer, ArrayList<String>>();
    SchedMethod(CG, tr, c, part, top);

    
    Stack<ArrayList<String>> leg = new Stack<ArrayList<String>>();

    
    for (Map.Entry<Integer, ArrayList<String>> start : top.entrySet()) {
        
        leg.add(start.getValue());
        
    }
StdOut.setFile(args[2]);

    StdOut.println(leg.size());
    ArrayList<String> readyC = new ArrayList<>();
    
    
    while (!leg.isEmpty()) {
        ArrayList<String> Cs = leg.pop();
        
        
        for (String e: Cs) {
            if (!readyC.contains(e)) {
                StdOut.print(e+ " ");
                readyC.add(e);
            }
        }
        StdOut.println();
    }       

}


public static HashMap<String, ArrayList<String>> getG(String filename) {
    StdIn.setFile(filename);
Integer number = StdIn.readInt();
   
    
    
    
    
    HashMap<String, ArrayList<String>> CG = new HashMap<>();
    
    
    
    for (int i = 0; i < number; i++) {
                String c = StdIn.readString();
                CG.put(c, new ArrayList<>());
    }

   
    Integer connect = StdIn.readInt();
   
   
    StdIn.readLine();

    
    
    for (int i = 0; i < connect; i++) {
        String requirements = StdIn.readLine();
        
        
        
        String[] spr = requirements.split("\\s+");
        
        String MC = spr[0];
        
        
        String prqs = spr[1];

        CG.get(MC).add(prqs);
    }

    return CG;
}

public static void SchedMethod(HashMap<String, ArrayList<String>> CG, String tr, String pause, int part,HashMap<Integer, ArrayList<String>> top) {
    ArrayList<String> pqrs = CG.get(tr);
    
    part++;

    if (pqrs.contains(pause)) {
        pqrs.remove(pause);
    } 
    
    
    for (String pqr: pqrs) {
        
        
        
        
        SchedMethod(CG, pqr, pause, part, top);
       
       
        System.out.println(pqr + " "+part);
       
       
       
        if (top.containsKey(part)) {
            top.get(part).add(pqr);
        } else {
           
           
                 ArrayList<String> h = new ArrayList<>();
                h.add(pqr);
                top.put(part, h);
       
       
            }
        

    }

}

}


    

