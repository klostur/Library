package library.oop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class IOAuthor {

	public static ArrayList<Author> ucitajAutore() {
		ArrayList<Author> autori = new ArrayList<>();
		try (BufferedReader input = new BufferedReader(new FileReader("Authors.txt"))) {
			String red;
			while ((red = input.readLine()) != null) {
				autori.add(stringToAuthor(red));
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fajl ne postoji");
		} catch (IOException e) {
			System.out.println("IO EX");
		}
		return autori;
	}

	public static void snimiAutore(ArrayList<Author> listAuthors) throws FileNotFoundException {
		PrintWriter output = new PrintWriter("Authors.txt");
		for (int i = 0; i < listAuthors.size(); i++) {
			output.println(authorToString(listAuthors.get(i)));
		}
		output.close();
	}

	private static Author stringToAuthor(String red) {
		String[] niz = red.split(",");
		Author a = new Author(niz[0], niz[1], niz[2], niz[3], niz[4]);
		return a;
	}

	private static String authorToString(Author author) {
		String retVal = author.getId() + "," + author.getName() + "," + author.getSurname() + "," + author.getEmail()
				+ "," + author.getGender();
		return retVal;
	}

}
