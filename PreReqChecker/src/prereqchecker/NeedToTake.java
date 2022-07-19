package prereqchecker;

import java.util.*;
//Bilal Khan
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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	
    HashMap<String, ArrayList<String>> CG = getClassMethod(args[0]);

    StdIn.setFile(args[1]);

    String tr = StdIn.readString();

    
    HashSet<String> every = new HashSet<>();
    prqsMethod(CG, tr, every);

    
    int taken = StdIn.readInt();
    HashSet<String> done = new HashSet<>();

    for (int i = 0; i < taken; i++) {
        
        
        String c = StdIn.readString();
        
            done.add(c);
                
            prqsMethod(CG, c, done);
    }


    for (String courseTaken: done) {
        every.remove(courseTaken);
    }

    System.out.println(every);
                                                            //all courses taken get taken care of



                                                            //bigger area

 StdOut.setFile(args[2]);

    for (String course: every) {
        StdOut.println(course);
    }



}

public static HashMap<String, ArrayList<String>> getClassMethod(String filename) {
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


public static void prqsMethod(HashMap<String, ArrayList<String>> CG, String tr, HashSet<String> taken) {

    ArrayList<String> prqs = CG.get(tr);
    
    if (prqs.isEmpty()) return;

    for (String e: prqs) {
       
       
        if (!taken.contains(e)) {
            taken.add(e);
        }
        
        prqsMethod(CG, e, taken);

    }

}

}
    

