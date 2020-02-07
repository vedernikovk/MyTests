package generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MediaLibrary <T extends Media> {

	private Map<String, T> storage = new HashMap<String, T>();

	void store(T t) {
		storage.put(t.getName(), t);
	}

	void print(List<? extends Media> library) {
		//library.add(new Book("Finding Nemo")); - Error
		for (Media media: library) {
			System.out.println(media.getName());
		}
	}

	void print2(List<Media> library) {
		library.add(new Book("Finding Nemo"));
		for (Media media: library) {
			System.out.println(media.getName());
		}
	}

	public T retrieve(String name) {
		return storage.get(name);
	}

	public void catalog() {
		for (Entry<String, T> e : storage.entrySet()) {
			e.getValue().print();
		}
	}

	public static void main(String[] args) {
		MediaLibrary<Media> ml = new MediaLibrary<Media>();

		ml.store(new Book("Finding Nemo"));
		ml.store(new Video("Terminator"));
		ml.store(new Newpapaper("Pravda"));
		ml.store(new MyBook("My Book"));
		ml.catalog();

		List<Book> ml2 = new ArrayList<Book>();
		ml2.add(new Book("Finding Nemo"));
		ml.print(ml2);

		List<Video> ml3 = new ArrayList<Video>();
		ml3.add(new Video("Terminator"));
		ml.print(ml3);

		List<MyBook> ml4 = new ArrayList<MyBook>();
		ml4.add(new MyBook("My Book"));
		ml.print(ml4);
	}
}

interface Media {
	public String getName();
	public void print();
}

class Book implements Media {

	private String name;

	public Book(String name) {
		this.name = name;
	}

	public void print() {
		System.out.println("Book " + name);
	}

	public String getName() {
		return name;
	}
}

class MyBook extends Book {
	public MyBook(String name) {
		super(name);
	}
}

class Video implements Media {

	private String name;

	public Video(String name) {
		this.name = name;
	}

	public void print() {
		System.out.println("Video " + name);
	}

	public String getName() {
		return name;
	}
}

class Newpapaper implements Media {

	private String name;

	public Newpapaper(String name) {
		this.name = name;
	}

	public void print() {
		System.out.println("Newspaper " + name);
	}

	public String getName() {
		return name;
	}
}
