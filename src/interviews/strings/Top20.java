package interviews.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Top20 {

	private static void stringReverse(String s, StringBuilder sb) {		
		if (s.length() != 0) {
			stringReverse(s.substring(1), sb);
			sb.append(s.charAt(0));
		} else {
			return;
		}
	}
	
	private static Character firstNonRepeatedCharacter(String s) {
		
		Set<Character> reps = new HashSet<Character>();
		List<Character> noReps = new ArrayList<Character>();
		
		// aaasbbbqwe
		for (Character c : s.toCharArray()) {
			if (reps.contains(c)) {
				noReps.remove(c);				
			} else {
				reps.add(c);
				noReps.add(c);
			}
		}
		
		return noReps.get(0);
	}

	/*
	"abcd"
	" bcd"
	"  cd"
	"   d"
	*/
	private static String stringReverse(String s) {		
		if (s.length() > 1) {
			return stringReverse(s.substring(1)) + s.charAt(0);
		} else {
			return s;
		}
	}

	/*
	"abcd"
	"abc"
	"ab"
	"a"
	*/
	private static String stringReverse2(String s) {		
		if (s.length() > 1) {
			return s.charAt(s.length() - 1) + stringReverse(s.substring(0, s.length() - 1));
		} else {
			return s;
		}
	}
	
	private static boolean isPalindrome(String inputString) {		
		char[] array = inputString.toCharArray();
		
		int forward = 0;
		int backward = array.length - 1; 
		
		while (forward < backward) {
			if (array[forward] != array[backward]) {
				return false;
			}
			forward++; backward--;
		}
		
		return true;
	}
	
	/*
	 * 1 a, b, c, d, e 
	 * 2 ab, bc, cd, de
	 * 3 abc, bcd, cde
	 * 4 abcd, bcde
	 * 5 abcde
	 */
	private static List<String> substrings(String s) {
		List<String> result = new ArrayList<String>();
		
		for (int k = 1; k <= s.length(); k++) {
			for (int i = 0; i <= s.length() - k; i++) {
				result.add(s.substring(i, i + k));
			}			
		}
		
		return result;
	}
	
	private static String findLongestPalindrome(String str) {
		List<String> subs = substrings(str);
		
		String result = null; int max = 0;
		
		for (String s : subs) {
			if (isPalindrome(s) && s.length() > max) {
				result = s;
			}
		}
		
		return result;
	}
	
	private static String removeDuplicateCharacters(String s) {
		Set<Character> uniqs = new LinkedHashSet<Character>();
		
		for (Character c : s.toCharArray()) {
			uniqs.add(c);
		}
		
		StringBuilder resultBuilder = new StringBuilder();  
		uniqs.stream().forEach(resultBuilder::append);
		
		return resultBuilder.toString();
	}

	private static String removeDuplicateCharacters2(String s) {

		StringBuilder result = new StringBuilder();
		result.append(s.charAt(0));
		
		for (int i = 1; i <= s.length() - 1; i++) {
			char c = s.charAt(i);
			if (result.indexOf(c + "") == -1) {
				result.append(c);
			}
		}
		
		return result.toString();
	}

	
	private static String removeDuplicateCharacters3(String s) {
		StringBuilder result = new StringBuilder();
		
		for (Character c : s.toCharArray()) {
			if (result.indexOf("" + c) == -1) {
				result.append(c);
			}
		}
		
		return result.toString();
	}
	
	private static boolean hasRepeatingCharacters(String s) {
		for (int i = 0; i <= s.length() - 1; i++) {
			char c = s.charAt(i);
			if (s.substring(i+1).indexOf(c+"") != -1) {
				return true;
			}
		}
		
		return false;
	}
	
	private static String longestSubstringWithoutRepeatingCharacters(String s) {
		String result = "";
		int max = 0;
		
		for (int k = 1; k <= s.length(); k++) {
			for (int i = 0; i <= s.length() - k; i++) {
				
				String sub = s.substring(i, i + k);
				
				if (!hasRepeatingCharacters(sub) && sub.length() > max) {
					result = sub;
					max = sub.length();
				}
			}			
		}
		
		return result;
	}
	
	public static String reverseWords(String sentence) { 
		List< String> words = Arrays.asList(sentence.split("\\s"));
		
		Collections.reverse(words); 
		
		StringBuilder sb = new StringBuilder(sentence.length()); 
		
		for (int i = words.size() - 1; i >= 0; i--) { 
			sb.append(words.get(i)); 
			sb.append(' '); 
		} 
		
		return sb.toString().trim(); 
	}

		
	public static boolean isRotation(String s1, String s2) {
		
		for (int i = s2.length() - 1; i >= 1; i--) {
			String rotation = s2.substring(i) + s2.substring(0, i);
			if (s1.equalsIgnoreCase(rotation)) {
				return true;
			}
		}
		
		return false;		
	}
	
	public static boolean checkAnagram(String first, String second) { 
		char[] characters = first.toCharArray();
		
		StringBuilder sbSecond = new StringBuilder(second);
		
		for (char ch : characters) { 
			int index = sbSecond.indexOf("" + ch);
			
			if (index != -1) { 
				sbSecond.deleteCharAt(index); 
			} else { 
				return false; 
			} 
		} 
		
		return sbSecond.length() == 0 ? true : false;
	}
	
	private static int countNumberOfwords(String s) {
		int result = 0; 
		int index = 0;
		char c = s.charAt(index);
		
		while (index < s.length()) {			
			
			// "qwe"
			// "	 "
			// " qwe "
			// " qwe qwe"			
			while (index < s.length()) {
				c = s.charAt(index);
				if (c == ' ' || c == '\t' || c == '\n') {
					index++;				
				} else {
					result++;
					break;
				}
			}
						
			while (index < s.length()) {
				c = s.charAt(index);
				if (c != ' ' && c != '\t' && c != '\n') {
					index++;				
				} else {
					break;
				}				
			}
		}
		
		return result;
	}
	
	private static boolean parenthesesValididy(String s) {
		
		Set<Character> opening = new HashSet<Character>(Arrays.asList('{', '(', '['));
		Set<Character> closing = new HashSet<Character>(Arrays.asList('}', ')', ']'));
		
		Stack<Character> stack = new Stack<Character>();
		
		try {
			for (Character c : s.toCharArray()) {
				if (opening.contains(c)) {
					stack.push(c);
					
				} else if (closing.contains(c)) {
					Character top = stack.peek();
					
					if (c == '}' && top != '{') {
						return false;
					}
					
					if (c == ']' && top != '[') {
						return false;
					}
					
					if (c == ')' && top != '(') {
						return false;
					}
					
					stack.pop();
				}
			}
			
			return stack.size() == 0;
			
		} catch (EmptyStackException e) {
			return false;
		}
	}
	
	private static int indexOf(String s, String subst) {

		if (subst.length() > s.length()) {
			return -1;
		}
		
		if (subst.length() == s.length()) {
			return subst.equals(s) ? 0 : -1;
		}

		for (int i = 0; i <= s.length() - subst.length(); i++) {
			if (s.substring(i, i + subst.length()).equals(subst)) {
				return i;
			}		
		}
		
		return -1;
	}

	private static Set<Character> toSet(String s) {				
		Set<Character> result = new HashSet<Character>();
		for (Character c : s.toCharArray()) {
			result.add(c);
		}
		return result;
	}
	
	// How to find the smallest substring in a given string 
	// containing all characters of another string?
	private static String smallestSubstring(String s, String anotherString) {
		
		Set<Character> another = toSet(anotherString);
		
		String result = null; int smallest = Integer.MAX_VALUE;
		
		for (int k = 1; k <= s.length(); k++) {
			for (int i = 0; i <= s.length() - k; i++) {
				String sub = s.substring(i, i + k);
				
				if (toSet(sub).containsAll(another)) {
					if (sub.length() < smallest) {
						result = sub;
						smallest = sub.length(); 
					}
				}
			}
		}
		
		return result;
	}

	/* How to find the length of the longest substring with K distinct characters */ 
	private static String longestSubstring(String s, int k) {
		
		String result = null; int longest = 0;
		
		for (int l = 1; l <= s.length(); l++) {
			for (int i = 0; i <= s.length() - l; i++) {
				
				String sub = s.substring(i, i + l);				
				Set<Character> subSet = toSet(sub);
				
				if (subSet.size() >= k) {
					result = sub;
					longest = sub.length(); 
				}
			}
		}
		
		return result;
	}
	
	private static long atoi(String s) {
		
		// "825"
		long result = 0;		
		for (int i = 0; i <= s.length() - 1; i++) {
			byte d = Byte.parseByte(String.valueOf(s.charAt(i)));
			long power = (long) Math.pow(10, s.length() - 1 - i);
			result +=  d * power;
		}
		
		return result;
	}
		
	private static boolean isValidShuffle(String first, String second, String third) {
		for (int i = 0; i <= first.length() - 1; i++) {
			
			char firstChar = first.charAt(i);
			char secondChar = second.charAt(i);
			
			char thirdLeft = third.charAt(i * 2);
			char thirdRight = third.charAt(i * 2 + 1);
			
			if ((firstChar == thirdLeft && secondChar == thirdRight) || firstChar == thirdRight && secondChar == thirdLeft) {
				continue;
			} else {
				return false;
			}
		}
		
		return true;
	}

	private static Set<Character> toSet2(String s) {
		String[] arrs = s.split("");
		
		List<Character> chars = Arrays.stream(arrs).map(e -> e.charAt(0)).collect(java.util.stream.Collectors.toList());
		
		Set<Character> result = new HashSet<Character>(chars);
		
		return result;
	}

	
	public static void main(String[] args) {
		//System.out.print(checkAnagram("word", "wrdo"));
		
		// StringBuilder sb = new StringBuilder();
		// stringReverse("Hellow String Reverse", sb);		
		// System.out.print(sb.toString());
		
		// System.out.print(stringReverse("aaabbbqwe"));
		// System.out.print(firstNonRepeatedCharacter("aaabbbqwe"));
		// System.out.println(stringReverse("a"));
		// System.out.println(stringReverse2("a"));
		
		// System.out.println(findLongestPalindrome("kazaq"));
		// System.out.println(removeDuplicateCharacters2("bananass"));
		// System.out.println(longestSubstringWithoutRepeatingCharacters("vangogkkuber"));
		// System.out.println(reverseWords("Java is Great"));
		// USAEnglandIndiaUSAEnglandIndia
		// System.out.println(isRotation("IndiaUSAEngland", "USAEnglandIndia"));
		// System.out.println(isRotation("IndiaUSAEngland", "IndiaEnglandUSA"));
		// System.out.println(countNumberOfwords(" 	 "));
		// System.out.println(countNumberOfwords("	 qwewqe "));
		// System.out.println(countNumberOfwords("	 qwewqe zxdszc	xvxcv "));
		// System.out.println(countNumberOfwords("qwewqe	 zxdszc	xvxcv q"));
		// System.out.println(countNumberOfwords("qwe"));
		// System.out.println(parenthesesValididy("()[]{}"));
		// System.out.println(parenthesesValididy("(]"));
		// System.out.println(parenthesesValididy("([)]"));
		// System.out.println(parenthesesValididy("((([({})])))"));
		// System.out.println(indexOf("qwerty", "q"));		
		// System.out.println(indexOf("qwerty", "ty"));
		// System.out.println(indexOf("qwerty", "abc"));
		// System.out.println(longestSubstring("araaci", 2));
		// System.out.println(isValidShuffle("abc", "def", "dabecf"));
		System.out.println(toSet2("dabecf"));
	}
}
