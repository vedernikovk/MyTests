package lamdas;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import static com.insightfullogic.java8.examples.chapter1.SampleData.*;

public class Answers {
	
	ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat(""));
	
	public static int addUp(Stream<Integer> numbers) {
		return numbers.reduce(0, (a, b) -> a + b);
	}

	public static List<String> getNamesAndOrigins(List<Artist> artists) {
		return artists.stream()
				.map(artist -> artist.getName() + "(" + artist.getNationality() + ")")
				.collect(Collectors.toList());
	}
	
	public static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> input) {
		 return input.stream()
				 .filter(album -> album.getTrackList().size() <= 3)
				 .collect(Collectors.toList());
	}
	
	public static long countBandMembersInternal(List<Artist> artists) {
		return artists.stream()
			.map(artist -> artist.getMembers().count())
			.reduce(0L, (a, b) -> a + b);
	}
	
	public static long countLowercaseLetters(String string) {
		return string.chars()
				.filter(ch -> Character.isLowerCase(ch))
				.count();
				
	}
	
	 public static Optional<String> mostLowercaseString(List<String> strings) {
		 return strings.stream()
				 .max(Comparator.comparing(string -> string.chars().filter(Character::isLowerCase).count()));
		 
	 }
	
	 public static Map<String, Long> countWords(Stream<String> names) {
		 return names.collect(Collectors.groupingBy(name -> name, Collectors.counting()));
	 }
	 
	 public static String longestName(Stream<String> names) {
		 return names.max(Comparator.comparing(name -> name.length())).get();
	 }
	 
	 public static void main(String[] args) {
		System.out.println("Summ: " + addUp(Stream.of(1, 2, 3, 4, 5)));
		
		System.out.println("Artists: " + getNamesAndOrigins(getThreeArtists()));
		
		System.out.println("List of albums with at most three tracks: " + getAlbumsWithAtMostThreeTracks(Arrays.asList(aLoveSupreme, sampleShortAlbum, manyTrackAlbum)));
		
		System.out.println("Members: " + countBandMembersInternal(getThreeArtists()));
		
		System.out.println("Number of lowcase letters: " + countLowercaseLetters("qweASDzxc"));
		
		System.out.println("String with the largest number of lowercase letters: " + mostLowercaseString(Arrays.asList("a", "asdWER", "zxcvLL")));
		
		System.out.println("Words: " + countWords(Stream.of("John", "Paul", "George", "John", "Paul", "John")));
		
		System.out.println("Longest name: " + longestName(Stream.of("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe")));
	 }
}
