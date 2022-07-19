package prereqchecker;

import java.util.*;
//Bilal Khan
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


     
     HashMap<String, ArrayList<String>> G = GG(args[0]);

     
     StdIn.setFile(args[1]);

     Integer CND = StdIn.readInt();
     
     
     HashSet<String> CT = new HashSet<String>();
     
     for (int i = 0; i < CND; i++) {
         
        
            String name = StdIn.readString();
            CT.add(name);
     }
     
     String[] courses = CT.toArray(new String[CT.size()]);

     for (String c: courses) {
         
        
                    getCoursesTaken(G, c, CT);
     }


     
     ArrayList<String> courseAllowed = new ArrayList<>();

     for (String y : G.keySet()) {
                ArrayList<String> prereqs = G.get(y);
                 boolean eligible = true;
         
         
         
                 for (String req: prereqs) {
                    if (!CT.contains(req)) {
                        eligible = false;
     }
}

         



            if (eligible && !CT.contains(y)) {
             courseAllowed.add(y);
         }
     }

     
     StdOut.setFile(args[2]);

     for (String cldr:courseAllowed) {
         
        
        
        StdOut.println(cldr);
     }

 }

 public static HashMap<String, ArrayList<String>> GG(String filename) {
     
    
    
    
    StdIn.setFile(filename);

     Integer number = StdIn.readInt();
    
     HashMap<String, ArrayList<String>> CTG = new HashMap<>();
     
     for (int i = 0; i < number; i++) {
         
        
                String cc = StdIn.readString();
                CTG.put(cc, new ArrayList<>());
     }

     
     Integer recon = StdIn.readInt();
     StdIn.readLine();

     for (int j = 0; j < recon; j++) {
         String need = StdIn.readLine();
         
         String[] stp = need.split("\\s+");
         
         String mc = stp[0];
         
         String prqs = stp[1];

         CTG.get(mc).add(prqs);
    }

     
    
    
    
    return CTG;
 }

 public static void getCoursesTaken(HashMap<String, ArrayList<String>> Coursemake, String classs, HashSet<String> done) {

     ArrayList<String> l = Coursemake.get(classs);

     if (l.isEmpty()) return;

     for (String c: l) {
        
        
        done.add(c);
        
        
        getCoursesTaken(Coursemake, c, done);
    }

}
}


