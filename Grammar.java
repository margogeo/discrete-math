//author Margarita Shimanskaia
//finds whether word can be deduced from that context free grammar or not
import java.util.*;
import java.io.*;
import java.lang.Math;

public class Grammar {
	
	static int [][][] cs; 
	
	static boolean check(int st, String x, int pos) {
		int c = (int)(x.charAt(pos) - 'a');
		if(pos == x.length() - 1) {
			if(cs[st][c][26] == 1)
			    return true;
			else
				return false;
		}
		int i;
	    for(i = 0; i < 26; i++) {
	    	if(cs[st][c][i] == 1) {
	    		if(check(i, x, pos + 1))
	    			return true;
	    	}
	    }
	    return false;
	}
	
	public static void main(String[] args) throws IOException {
		File fin = new File("automaton.in");
        Scanner in = new Scanner(fin);
        PrintWriter fout = new PrintWriter("automaton.out");
        int n = in.nextInt();
        int s = (int)((in.next()).charAt(0) - 'A');
        in.nextLine();
        cs = new int[26][26][27];  
        for(int i = 0; i < n; i++) {
        	String d = in.nextLine();
        	int a = (int)(d.charAt(0) - 'A');
        	int b = (int)(d.charAt(5) - 'a');
        	if(d.length() == 6) {
        		cs[a][b][26] = 1;
        	} else {
        		int k = (int)(d.charAt(6) - 'A');
        		cs[a][b][k]= 1;
        	}
        }
        int m = in.nextInt();
        for(int i = 0; i < m; i++) {
        	String x = in.next();
        	if(check(s, x, 0))
        		fout.println("yes");
        	else
        		fout.println("no");
        }
		fout.close();
	}
}
