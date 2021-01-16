package interviews.recursion;

import java.io.File;

public class PrintFiles {

	public static void main(String[] args) {
		print(new File("D:\\Kos\\Work\\Samples\\MyTests\\src\\interviews"));
	}
	
	public static void print(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				print(f);
			}
		} else {
			System.out.println(file.getAbsolutePath());
		}
	}
}
