package interviews.strings;

import java.util.*;

public class BONY {
		
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

	public static String getSmallestAndLargest(String s, int k) {
		String smallest = "";
		String largest = "";

		List<String> subs = new ArrayList<>();
		for (int i=0; i <= s.length() - k; i++) {
			String sub = s.substring(i, i + k);
			subs.add(sub);
		}

		Collections.sort(subs);
		smallest = subs.get(0);
		largest = subs.get(subs.size() - 1);

		return smallest + "\n" + largest;
	}

	static boolean isAnagram(String a, String b) {
		if (a.length() != b.length()) {
			return false;
		}

		var afrequency = frequency(a.toLowerCase());
		var bfrequency = frequency(b.toLowerCase());

		for (Map.Entry<Character, Integer> entry : afrequency.entrySet()) {
			if (!bfrequency.containsKey(entry.getKey())) {
				return false;
			}

			if (bfrequency.get(entry.getKey()) != entry.getValue()) {
				return false;
			}
		}

		return true;
	}

	private static Map<Character, Integer> frequency(String s) {
		Map<Character, Integer> result = new HashMap<>();
		for (int i = 0; i <= s.length() - 1; i++) {
			Character c = s.charAt(i);
			if (result.containsKey(c)) {
				result.put(c, result.get(c)+1);
			} else {
				result.put(c, 1);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		//System.out.print(getSubstrings("sdkjfsdk", 3));
		//System.out.println(getSubsCount("aabbbcccdfgqqqz"));
		//System.out.println(getSubsCount("aabbbcccdfgqqqzz"));
		//System.out.println(getSubsCount("abbbcccdfgqqqzz"));
		//System.out.println(getSubsCount("aabbbcccdfgqqqqqqz"));
		// System.out.println(getSmallestAndLargest("welcometojava" , 3));
	}
}
