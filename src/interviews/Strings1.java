package com.milind.reactive;

import java.util.HashSet;
import java.util.Set;

public class Strings1 {
		
	private static Set<String> getSubstrings(String s, int size) {		 
		Set<String> result = new HashSet<String>();
		for (int i = 0; i <= s.length() - size; i++) {
			result.add(s.substring(i, i + size));
		}
		return result; 
	}
	
	private static String transforms(String s) {
		StringBuilder sb = new StringBuilder();
		
		char prev = s.charAt(0);
		int counter = 1;
		for (int i = 1; i <= s.length() - 1; i++) {
			char current = s.charAt(i);
			
			if (prev != current) {
				sb.append(prev);
				sb.append(counter);
				counter = 1;
			} else {
				counter++;
			}
			
			prev = current;
		}
		sb.append(prev);
		sb.append(counter);
		
		return sb.toString();
	}

	private static int getSubsCount(String s) {
		int subsCouner = 0;
		char prev = s.charAt(0);
		for (int i = 1; i <= s.length() - 1; i++) {
			char current = s.charAt(i);			
			if (current == prev) {
				subsCouner++;
				prev = ' ';
			} else {
				prev = current;
			}
		}		
		return subsCouner;
	}
	
	public static void main(String[] args) {
		//System.out.print(getSubstrings("sdkjfsdk", 3));
		System.out.println(getSubsCount("aabbbcccdfgqqqz"));
		System.out.println(getSubsCount("aabbbcccdfgqqqzz"));
		System.out.println(getSubsCount("abbbcccdfgqqqzz"));
		System.out.println(getSubsCount("aabbbcccdfgqqqqqqz"));		
	}

}
