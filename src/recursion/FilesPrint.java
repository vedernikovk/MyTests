package recursion;

import java.io.File;

public class FilesPrint {

	public static void main(String[] args) {
		printTree(new File("."));
	}
	
	static void printTree(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			
			for (File f :files) {
				printTree(f);
			}
			
		} else {
			System.out.println(file.getAbsolutePath());
		}		
	}
}
