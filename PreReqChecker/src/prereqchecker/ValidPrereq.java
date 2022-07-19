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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
	
    

HashMap<String, ArrayList<String>> CG = getClassMethod(args[0]);


StdIn.setFile(args[1]);

String first = StdIn.readString();
String second = StdIn.readString();



HashSet<String> taken = new HashSet<String>();

getClassMethod(CG, second, taken);


StdOut.setFile(args[2]);



//String first = StdIn.readString();
//String second = StdIn.readString();

System.out.println(taken);



System.out.println("course1: "+ first);



System.out.println("course2: "+ second);



System.out.println(taken.contains(first));






if (taken.contains(first)) {
    
    
    
    
    StdOut.print("NO");
} else {
    StdOut.print("YES");
}





}

public static boolean Checker(String tr, String second, HashMap<String, ArrayList<String>> CG) {

ArrayList<String> temp = CG.get(second);
for (String c: temp) {
            
    
    
                if (c.equals(tr)) return false;

                Checker(CG, c, tr);
}

return true;
}

public static void getClassMethod(HashMap<String, ArrayList<String>> CG, String e, HashSet<String> taken) {

ArrayList<String> start = CG.get(e);

        if (start.isEmpty()) return;

        for (String d: start) {
    
    
    
    
            taken.add(d);
             getClassMethod(CG, d, taken);
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
    
    String mc = spr[0];
   
   
   
   
   
    String prqs = spr[1];

    CG.get(mc).add(prqs);
}

return CG;
}


public static boolean Checker(HashMap<String, ArrayList<String>> CG, String first, String second) {
ArrayList<String> cds = CG.get(first);





if (cds.isEmpty()) return false;




for (String per: cds) {
    if (per.equals(second)) {
        return true;
    }
}

for (String c: cds) {
            
    
    
    
    
    
            System.out.print(c);
            return Checker(CG, c, second);         
}    





return false;
}




}
