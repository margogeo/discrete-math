import java.util.*;
import java.io.*;
import java.lang.Math;

public class Epsilon { 
	
	public static void main(String[] args) throws IOException {
		File fin = new File("epsilon.in");
        Scanner in = new Scanner(fin);
        PrintWriter fout = new PrintWriter("epsilon.out");
        int n = in.nextInt();
        int s = (int)((in.next()).charAt(0) - 'A');
        in.nextLine();
        int[] ans = new int[26];
        String[] cs = new String[n];
        int j,count = 0;
        for(int i = 0; i < n; i++) {
        	String d = in.nextLine();
        	d = d.trim();
        	int a = (int)(d.charAt(0) - 'A');
        	if(d.length() > 4) {
        		for(j = 5; j < d.length(); j++)
        			if(d.charAt(j) > 'Z')
        				break;
        		if(j == d.length())
        		    cs[count++] = d;
        	}		
        	else 
        		ans[a] = 1;
        }
        int np = 1;
        while(np > 0) {
        	np=0;       	
        	for(int i = 0; i < count; i++) {
        		int cc = cs[i].charAt(0) - 'A';
        	    if(ans[cc] == 0) {
        			int k = 5, lw = cs[i].length();
        			for( ; k < lw; k++ ) {
        			    int r = cs[i].charAt(k) - 'A';
        				if(r < 0 || r >= 26 || ans[r] == 0)
        					break;
        			}
        			if(k == lw) {
        				ans[cc] = 1;
        				np++;
        			}
        		}
        		  	
        	}
        }
        for(int i = 0; i < 26; i++) {
        	if(ans[i] == 1) {
        		fout.print((char)(i + 'A') + " ");
        	}
        }
		fout.close();
	}
}

