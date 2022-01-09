package _00_Intro_To_String_Methods;

import java.util.Base64;
import java.util.regex.Matcher;

/*
 * Visit the JavaDocs for the String class to view everything you can do with a String.
 * https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
 * https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html
 *
 * HINT:  Here are some String methods you might find useful 
 * contains
 * replace
 * trim
 * length
 * getBytes
 * endsWith
 * split 
 * indexOf
 * lastIndexOf
 * compareTo(IgnoreCase)
 * substring
 * toUpperCase/toLowerCase
 * valueOf
 *
 * Here are some Character methods you might find useful:
 * Character.toLowerCase(char c);
 * Character.toUpperCase(char c);
 * Character.isLetter(char c);
 * Character.isDigit(char c);
 * Character.getNumericValue(char c);
 */

public class _01_StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length()>s2.length()) {
			return s1;
		}
		else {
			return s2;
		}
	}

	// If String s contains the word "underscores", change all of the spaces
	// to underscores
	public static String formatSpaces(String s) {
		String newString = s;
		if(s.contains("underscores") == true) {
			newString = s.replace(" ", "_");
		}
		return newString;
	}

	// Return the name of the person whose LAST name would appear first if they
	// were in alphabetical order.
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		String new1 = s1.trim();
		String new2 = s2.trim();
		String new3 = s3.trim();
		char last1 = new1.charAt(new1.length()-1);
		char last2 = new2.charAt(new2.length()-1);
		char last3 = new3.charAt(new3.length()-1);
		String save = "";

		if(last1<last2 && last1<last3) {
			save = new1;
		}
		else if(last2<last1 && last2<last3) {
			save = new2;
		}
		else if(last3<last1 && last3<last2) {
			save = new3;
		}
		return save;
	}

	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int sum= 0;

		for(int i = 0; i < s.length(); i++) {
			char num = s.charAt(i);
			if(Character.isDigit(num)) {
				sum+=Character.getNumericValue(num);
			}
		}

		return sum;
	}

	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int string = s.length();
		int sub = substring.length();
		int count = 0;

		for (int i = 0; i <= string - sub; i++){
			int j;
			for (j = 0; j < sub; j++) {
				if (s.charAt(i+j) != substring.charAt(j)) {
					break;
				}
			}
			if (j == sub) {
				count++;
			}
		}

		return count;
	}

	// Call Utilities.encrypt at the bottom of this file to encrypt String s
	public static String encrypt(String s, char key) {
		byte[] byteArray = s.getBytes();
		return Utilities.encrypt(byteArray, (byte) key);
	}

	// Call Utilities.decrypt at the bottom of this file to decrypt the
	// cyphertext (encrypted text)
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}

	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == substring.charAt(substring.length()-1) && s.charAt(i+1) == ' ') {
				if(substring.length() == 1) {
					count++;
					continue;
				}
				int check = 0;
				for(int j = 0; j < substring.length(); j++) {
					if(s.charAt(i-j) != substring.charAt(substring.length()-(j+1))) {
						check++;
					}
				}
				if(check == 0) {
					count++;
				}
			}
		}
		return count;
	}

	// Given String s, return the number of characters between the first
	// occurrence of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int first = 0;
		int last = 0;
		int string = s.length();
		int sub = substring.length();
		
		if(s.length() == 2 && substring.length() == 1 && s.charAt(0) == substring.charAt(0) && s.charAt(1) == substring.charAt(0)) {
			return 0;
		}
		
		for (int i = 0; i <= string - sub; i++){
			int j;
			for (j = 0; j < sub; j++) {
				if (s.charAt(i+j) != substring.charAt(j)) {
					break;
				}
			}
			if (j == sub) {
				first = i+(sub-1);
				break;
			}
		}
		
		for (int i = string-(sub-1); i >= sub-1; i--){
			int j;
			for (j = 0; j < sub; j++) {
				if (s.charAt(i+j) != substring.charAt(j)) {
					break;
				}
			}
			if (j == sub) {
				last = i;
				break;
			}
		}
		
		return (last-first)-1;
	}

	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		boolean isPal = true;
		s = s.replaceAll("[^a-zA-Z0-9]", "");
		String newString = s.toLowerCase();
		System.out.println(newString + "\n");
		for(int i = 0; i < newString.length(); i++) {
			if(newString.charAt(i) != newString.charAt((newString.length()-1)-i)){
				isPal = false;
				break;
			}
		}
		return isPal;
	}
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a
	// single byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
