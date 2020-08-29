import java.util.*;
import java.io.*;
import java.lang.Math;

public class Nfc { 
	
	public static void main(String[] args) throws IOException {
		File fin = new File("nfc.in");
        Scanner in = new Scanner(fin);
        PrintWriter fout = new PrintWriter("nfc.out");
        int n = in.nextInt();
        int s = (int)((in.next()).charAt(0) - 'A');
        in.nextLine();
        int[][] rules = new int[n][3];
        int[][] terms = new int[n][2];
        int[] used = new int[26];
        int j = 1;
        used[s] = 1;
        int count = 0, ct = 0;
        for(int i = 0; i < n; i++) {
            String d = in.nextLine();
            d = d.trim();
            int a = (int)(d.charAt(0) - 'A');
            used[a] = 1;
            if(d.length() == 6) {
            	terms[ct][0] = a;
            	terms[ct++][1] = (int)(d.charAt(5) - 'a');
            } else {
            	rules[count][0] = a;
            	rules[count][1] = (int)(d.charAt(5) - 'A');
            	rules[count][2] = (int)(d.charAt(6) - 'A');
            	used[rules[count][1]] = 1;
            	used[rules[count][2]] = 1;
            	count++;
            }
        }
        String w = in.next();
        int k = w.length();
        int[] v = new int[k];
        long[][][] dm = new long[26][k][k];
        for(int i = 0; i < k; i++) {
        	v[i] = (int)(w.charAt(i) - 'a');
        	for(int f = 0; f < ct; f++) {
        		if(terms[f][1] == v[i]) 
        			dm[terms[f][0]][i][i]++;
        	}
        }
        int num = 1000000007;
        for(int m = 1; m < k; m++) {
        	for(int i = 0; i < k - m; i++) {
        		int f = i + m;
        		for(int h = 0; h < count; h++) {
        			for(int t = i; t < f; t++) {
        			    dm[rules[h][0]][i][f] += (dm[rules[h][1]][i][t] * dm[rules[h][2]][t+1][f]) % num;
        			    dm[rules[h][0]][i][f] = dm[rules[h][0]][i][f] % num;
        			}
        		}
        	}
        }
        fout.println(dm[s][0][k-1]);
		fout.close();
	}
}

