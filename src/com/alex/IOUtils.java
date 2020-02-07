package com.alex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOUtils {
	public static String readString() throws IOException {
	    return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

	public static double readDouble() throws NumberFormatException, IOException {
		return Double.parseDouble(readString());
	}
	
	public static int readInt() throws NumberFormatException, IOException {
		return Integer.parseInt(readString());
	}
}
