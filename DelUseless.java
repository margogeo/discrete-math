//author Margarita Shimanskaia
//delets useless non terminals from context free grammar
import java.util.*;
import java.io.*;
import java.lang.Math;

public class DelUseless { 
	
	public static void main(String[] args) throws IOException {
		File fin = new File("useless.in");
        Scanner in = new Scanner(fin);
        PrintWriter fout = new PrintWriter("useless.out");
        int n = in.nextInt();
        int s = (int)((in.next()).charAt(0) - 'A');
        in.nextLine();
        int[] ans = new int[26];
        int[] ans1 = new int[26];
        int[] used = new int[26];
        int[] rules = new int[n];
        String[] cs = new String[n];
        int j = 0,count = 0;
        used[s] = 1;
        for(int i = 0; i < n; i++) {
        	String d = in.nextLine();
        	d = d.trim();
        	int a = (int)(d.charAt(0) - 'A');
        	used[a] = 1;
        	if(d.length() > 4) {
        		int k = 5, lw = d.length();
        		j = 0;
    			for( ; k < lw; k++) {
    				int r = d.charAt(k) - 'A';
    				if(r <= 25) {
    					used[r] = 1;
    					j = -1;
    				}
        	    }
    			if(j == 0) 
    			    ans1[a] = 1;
    			else
        	       cs[count++] = d;
    		}
            else {
        		ans1[a] = 1;
        	}
        }
        
        int np = 1;
        while(np > 0) {
        	np = 0;       	
        	for(int i = 0; i < count; i++) {
        		int cc = cs[i].charAt(0) - 'A';
        	    if(ans1[cc] == 0) {
        			int k = 5, lw = cs[i].length();
        			for( ; k < lw; k++) {
        			    int r = cs[i].charAt(k) - 'A';
        			    if(r <= 25) 
        			        if(ans1[r] == 0)
        				        break;
        			}
        			if(k == lw) {
        			    ans1[cc] = 1;
        				np++;
        			}
        		}  	
        	}
        }
        
        for(int i = 0; i < count; i++) {
    		int cc = cs[i].charAt(0) - 'A';
    	    if(ans1[cc] == 1) {
    			int k = 5, lw = cs[i].length();
    			for( ; k < lw; k++) {
    			    int r = cs[i].charAt(k) - 'A';
    			    if(r <= 25)
    			        if(ans1[r] == 0) 
    			    	    break;
    			}
    			if(k == lw) 
    			    rules[i] = 1;
    	    }
        }
        ans[s] = 1;
        np = 1;
        while(np > 0) {
        	np = 0;       	
        	for(int i = 0; i < count; i++) {
        		if(rules[i] == 1) {
        		    int cc = cs[i].charAt(0) - 'A';
        	        if(ans[cc] == 1) {
        			    int k = 5, lw = cs[i].length();
        			    for( ; k < lw; k++) {
        			        int r = cs[i].charAt(k) - 'A';
        			        if(r <= 25 && ans[r] == 0) {
        				        ans[r] = 1;
        			 	        np++;
        			        }
        		        }  	
        	        }
                }
            }
        }
        for(int i = 0; i < 26; i++) {
        	if(used[i] == 1 && ans[i] * ans1[i] == 0) {
        		fout.print((char)(i + 'A') + " ");
        	}
        }
		fout.close();
	}
}

