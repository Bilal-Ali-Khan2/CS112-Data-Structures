package prereqchecker;

import java.util.ArrayList;

// Bilal Khan
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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

        StdIn.setFile(args[0]);

        ArrayList<ArrayList> type = new ArrayList<>();

        Integer number = StdIn.readInt();

        for (int i = 0; i < number; i++) {   // mainsrat here
            // leave last start from bottom up
            
            ArrayList<String> Cm = new ArrayList<>();
            
            String c = StdIn.readString();
            
            Cm.add(c);
            
            type.add(Cm);
    }

                Integer Connect = StdIn.readInt();

                System.out.println(Connect);
                System.out.println(number);

        StdIn.readLine();
        for (int i = 0; i < Connect; i++) {
            
            String requirements = StdIn.readLine();
            
            String[] TDS = requirements.split("\\s+");
            
            String mC = TDS[0];
            
            
            String pr = TDS[1];

            for (int j = 0; j < number; j++) {
                
                
                if (mC.equals(type.get(j).get(0))) {
                    type.get(j).add(pr+" ");
                    break;
                }
            }
        }

        StdOut.setFile(args[1]); 

        for (int f = 0; f < type.size(); f++) {
            
            
                for (int j = 0; j < type.get(f).size(); j++) {
                
                
                StdOut.print(type.get(f).get(j)+" ");
            }
           
           
            StdOut.println();
    }
}
}

//for (int f = 0; f < typeCour.size(); f++) {
            
            
   // for (int j = 0; j < .get(f).size(); j++) {
                
                
   //     StdOut.print(hit.get(f).get(j)+" ");
 //   }
   
   
 //   StdOut.println();
//}

