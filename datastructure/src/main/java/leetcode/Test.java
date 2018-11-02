package leetcode;

import java.util.*;

public class Test {


	public List<List<Integer>> largeGroupPositions(String S) {
		if(S.length() > 0){
			List<List<Integer>> r = new ArrayList<List<Integer>>();
			char[] array = S.toCharArray();
			int length = array.length;
			char currentChar = array[0];
			int startIndex = 0, endIndex = 0;
			for(int i = 1; i < length; i++){
				if(array[i] == currentChar){
					endIndex ++;
				} else {
					if(endIndex - startIndex >= 2){
						List<Integer> a = new ArrayList<Integer>();
						a.add(startIndex);
						a.add(endIndex);
						r.add(a);
					}
					startIndex = i;
					endIndex = i;
					currentChar = array[i];
				}
			}
			if(endIndex - startIndex >= 2){
				List<Integer> a = new ArrayList<Integer>();
				a.add(startIndex);
				a.add(endIndex);
				r.add(a);
			}
			return r;
		} else {
			return null;
		}
	}

	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}


	
	public static void main(String[] args) {
//		Test t = new Test();
////		System.out.println(t.getPermutation(4,9));
////		System.out.println(t.numSquares(51));
//		System.out.println(Integer.toBinaryString(128));
//		System.out.println(Integer.toBinaryString(192));
//		System.out.println(Integer.toBinaryString(224));
//		System.out.println(Integer.toBinaryString(240));
//		System.out.println(Integer.toBinaryString(248));
		byte[] a = new byte[2];
		Test[] t = new Test[3];
		Integer[] r = new Integer[2];
//		System.out.println(Arrays.toString(a));


	}


	Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public int numSquares(int n) {
//		map.put(0,0);
//		map.put(1,1);
//		int r = helper(n, 0);
//		System.out.println(map);
//		return r;
		if(n <= 0) return 0;
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(0);
		a.add(1);
		while(a.size() < n+1){
			int size = a.size();
			int min = Integer.MAX_VALUE;
			for(int i = 1; i * i <= size; i++){
				min = Math.min(min, a.get(size - i * i) + 1);
			}
			a.add(min);
		}
		return a.get(n);

	}

	public int helper(int a, int num){
		if(map.containsKey(a)) return map.get(a);
		int min = Integer.MAX_VALUE;
		int begin = (int)Math.sqrt(a);
		for(int i = begin; i >= 1; i--){
			int t = helper(a - i * i, 0);
			if( t + 1 < min ){
				min = t + 1;
				System.out.println("a=" + a + " i=" + i + " t=" + t + " num=" + num);
			}
		}
		map.put(a, min);
		System.out.println(map);
		return min;
	}

//	TrieNode root = new TrieNode();
//
//	public String longestWord(String[] words) {
//		root.word = ".";
//		for(String s: words){
//			insert(s);
//		}
//		return dfs(root, "");
//	}
//
//	public String dfs(TrieNode node, String curString){
//		if(null == node || null == node.word) return curString;
//		if(node.word != ".") curString = node.word;
//		String r = curString;
//		for(TrieNode kid: node.kids){
//			String temp = dfs(kid, curString);
//			if((temp.length() > r.length() ) || (temp.length() == r.length() && temp.compareTo(r) < 0) ){
//				r = temp;
//			}
//		}
//		return r;
//	}
//
//	public void insert(String s){
//		if( null == s ||  s.length() == 0 ) return;
//		TrieNode curNode = root;
//		char[] charList = s.toCharArray();
//		for(int i = 0; i < charList.length; i++){
//			int pos = charList[i] - 'a';
//			if( null == curNode.kids[pos] ){
//				curNode.kids[pos] = new TrieNode();
//				curNode.kids[pos].singleChar = charList[i];
//			}
//			curNode = curNode.kids[pos];
//		}
//		curNode.word = s;
//	}
//
//	private class TrieNode{
//		String word ;//存储的单词是什么
//		TrieNode[] kids = new TrieNode[26];//26个字母可以加在后面
//		char singleChar;//存储的单个字母
//	}
//
//	public String getPermutation(int n, int k) {
//		LinkedList<Integer> s = new LinkedList<Integer>();
//		for(int j = 1; j <= n; j++){
//			s.add(j);
//		}
//		System.out.println(s);
//		String t = "";
//		int l = n-1;
//		for(int i = 0; i < n && k> 0 ; i++){
//			int p = helper(l);
//			System.out.println("p=" + p);
//			if( k % p == 0){
//				t += s.get((k / p) -1);
//				System.out.println("t=" + t);
//				s.remove((k / p) -1);
//			} else {
//				t += s.get(k / p);
//				System.out.println("t=" + t);
//				s.remove(k / p);
//			}
//
//			System.out.println("s=" + s);
//			k -= p * (k /p);
//			System.out.println("k=" + k);
//			l--;
//			System.out.println("l=" + l);
//		}
//		for(int j = s.size()-1; j > -1; j--){
//			t += s.get(j);
//		}
//		return t;
//	}
//
//	private int helper(int c){
//		int r = 1;
//		for(int i = 2; i <= c; i++){
//			r *= i;
//		}
//		return r;
//	}
	
//	 public boolean isHappy(int n) {
//	        Set<Integer> existed = new HashSet<Integer>();
//	        existed.add(n);
//	        while(n != 1){
//	           String s = String.valueOf(n);
//	             System.out.println("s=" + s);
//	            int tmp = 0;
//	            for(int i = 0; i < s.length(); i++){
//	                int r = Integer.valueOf(s.charAt(i)-'0');
//	                  System.out.println("r=" + r);
//	                tmp += r*r;
//	            }
//	             System.out.println(tmp);
//	            if( ! existed.add(tmp)) return false;
//	            else  n = tmp;
//	        }
//	        return true;
//	    }
	
	
	
//	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
//        int[][] m = new int[n][n];
//        int[] t = new int[n];
//        for(int i = 0; i < n; i ++) {
//        	t[i] = Integer.MAX_VALUE;
//        	for(int j = 0; j < n; j++) {
//        		m[i][j] = Integer.MAX_VALUE;
//        	}
//        }
//        for(int i = 0; i < flights.length; i++) {
//        	int[] tmp = flights[i];
//        	m[tmp[0]][tmp[1]] = tmp[2];
//        }
//        for(int i = 0; i < n; i++) {
//        	t[i] = m[src][i];
//        }
////        printT(t);
////        print(m);
//       int r = helper(n, m,src,dst,K,Integer.MAX_VALUE,0);
//       if(r == Integer.MAX_VALUE) {
//    	   return -1;
//       } else {
//    	   return r;
//       }
//    }
//
//	public int helper(int n, int[][] m, int src, int dst, int K, int r, int curK) {
//		if(curK >= K) return m[src][dst];
//		System.out.println("curK=" + curK);
//		int[] e = new int[n];
//		for(int o = 0; o < n; o ++) {
//			e[o] = Integer.MAX_VALUE;
//		}
//		 for(int i = 0; i <n; i++ ) {
//			 if(m[src][i] != Integer.MAX_VALUE) {
//				 for(int j = 0; j < n; j++) {
//					 if(m[i][j] != Integer.MAX_VALUE) {
//						 int u = Math.min(m[src][j], m[src][i] + m[i][j]);
//						 e[j] = Math.min(e[j], u);
//					 }
//				 }
//			 }
//
//		 }
//		 for(int y = 0; y < n; y++) {
//			 if(e[y] < m[src][y]) m[src][y] = e[y];
//		 }
//		 print(m);
//		 return Math.min(helper(n,m, src, dst, K, r, curK+1), m[src][dst]);
//	}
//
//
//
//	public void printT(int[] t) {
//		System.out.println("t=");
//			for(int j = 0; j < t.length; j++) {
//				System.out.print(t[j] + " ");
//			}
//			System.out.println();
//
//	}
//
//	public void print(int[][] m) {
//		System.out.println("m=");
//		for(int i = 0; i < m.length; i ++) {
//			for(int j = 0; j < m[0].length; j++) {
//				System.out.print(m[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
	
//	 public int minSteps(int n) {
//	     return helper(n, 1, 0, 0);
//	  }
//	    
//    public int helper(int n, int cur, int copy, int steps){
//       if(cur == n) return steps;
//       if(cur > n) return Integer.MAX_VALUE;
//       if(copy == 0) return helper(n, cur, cur, 1);
//       if(copy == cur) return helper(n, 2*cur, copy, steps+1);
//       else return Math.min(helper(n, cur, cur, steps+1), helper(n, cur+copy, copy, steps+1));
//        
//    }
//	
	
//	public int helper(long n){
//        if (n == 1) return 0;
//        if(n%2 ==0) {
//        	int c=helper(n/2) +1;
//        	System.out.println("c=" + c);
//        	return c;
//        }
//        else {
//        	int b = Math.min(helper(n+1) + 1, helper((n-1)/2 +1) + 2);
//        	System.out.println("b=" + b);
//        	return b;
//        }
//    }
	
	
//	public int maxAreaOfIsland(int[][] grid) {
//        int max = 0;
//        int m = grid.length;
//        int n = grid[0].length;
//        int[][] visited = new int[m][n];
//        for(int i = 0; i < m; i++){
//            for (int j =0; j < n; j++){
//                if( grid[i][j] == 1 && visited[i][j] == 0){
//                    max = Math.max(max, helper(grid, visited, i, j, 0));
//                }
//            }
//        }
//        
//        
//        return max;
//        
//    }
//    
//    public int helper(int[][] grid, int[][] visited, int i, int j, int total){
//    	System.out.println(i + "  " + j);
//        if( i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] == 1 || grid[i][j] == 0){
//            return total;
//        }
//        if(grid[i][j] == 1) {
//            total ++;  
//            visited[i][j] = 1;
//        }
//        
//        if(i-1 >= 0) {
//        	 total = helper(grid, visited, i - 1, j, total);
//        }
//        if(i + 1 < grid.length) {
//        	total = helper(grid, visited, i + 1, j, total);
//        }
//        if(j-1 >= 0) {
//        	total = helper(grid, visited, i, j - 1, total);
//        }
//        if(j+1 < grid[0].length) {
//        	total = helper(grid, visited, i, j + 1, total);
//        }
//        
//        return total;
//    }
//		
	
//	public static int countBinarySubstrings(String s) {
//        int l = s.length();
//        int first = -1;
//        int second = -1;
//        int total = 0;
//        int firstNum = 0;
//        int secondNum = 0;
//        for(int i = 0; i < l; i++){
//        	System.out.println(s.charAt(i));
//            if(first == -1){
//            	System.out.println("11111111111");
//                first = s.charAt(i);
//                firstNum = 1;
//            } else if(second == -1 && s.charAt(i) == first){
//            	System.out.println("22222222");
//                firstNum ++;
//            } else if(second == -1 && s.charAt(i) != first){
//            	System.out.println("3333333333333");
//                second = s.charAt(i);
//                secondNum = 1;
//            } else if(second != -1 && s.charAt(i) == second) {
//            	System.out.println("444444444444");
//            	secondNum ++;
//            }
//            else if(second != -1 && s.charAt(i) == first) {
//            	System.out.println("555555555   " + firstNum + "  " + secondNum);
//            	total += Math.min(firstNum, secondNum);
//                firstNum = secondNum;
//                secondNum = 1;
//                first = second;
//                second = s.charAt(i);
//            } 
//        }
//        total += Math.min(firstNum, secondNum);
//        return total;
//    }
	
//	public static int countBinarySubstrings(String s) {
//        int l = s.length();
//        int total = 0;
//        int firstNum = 0;
//        int secondNum = 1;
//        for(int i = 1; i < l; i++){
//            if(s.charAt(i) == s.charAt(i-1)){
//                secondNum ++;
//            } else {
//                total += Math.min(firstNum, secondNum);
//                firstNum = secondNum;
//                secondNum = 1;
//            }
//        }
//        total += Math.min(firstNum, secondNum);
//        return total;
//    }
	
//	public static void main(String[] args) {
//		int a = 200, b=300, x=300,y=300;
//		int k =5;
//		int length = Math.max(x,y);
//		int[] p = new int[length+1];
//		int[] q = new int[length+1];
//		int m = 0;
//		for(int i = 0; i < x; i++) {
//			if(((k- i*a) % b)==0 && (k- i*a) >=0) {
//				p[m] = i;
//				q[m] = (k- i*a) / b;
//				System.out.println(p[m]);
//				System.out.println(q[m]);
//				System.out.println();
//				m++;
//			}
//		}
//		BigInteger sum = new BigInteger("0");
//		for(int i = 0; i<m; i++) {
//			if(p[i] <= x && q[i] <= y) {
////				System.out.println( x-p[i]);
//				BigInteger s = ((temp(x).divide(temp(p[i]))).divide(temp(x-p[i])));
//				BigInteger t = ((temp(y).divide(temp(q[i]))).divide(temp(y-q[i])));
//				sum = sum.add(s.multiply(t))  ;
//			}
//		}
//		System.out.println(sum);
//	}
//	
//	private static BigInteger temp(int n) {
//		BigInteger s = new BigInteger("1");
//		for(int i= 1; i<=n; i++) {
//			s = s.multiply(new BigInteger(i + ""));
//		}
////		System.out.println(s);
//		return s;
//	}

}
