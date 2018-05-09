package library.oop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class IOBook {

	static ArrayList<Book> ucitajKnjige() {
		ArrayList<Book> books = new ArrayList<>();
		try (BufferedReader input = new BufferedReader(new FileReader("Books.txt"))) {
			String red;
			while ((red = input.readLine()) != null) {
				books.add(stringToBook(red));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fajl ne postoji");
		} catch (IOException e) {
			System.out.println("IO EX");
		}
		return books;
	}

	private static Book stringToBook(String red) {
		String[] niz = red.split(",");
		ArrayList<Author> list = new ArrayList<>();
		Book book = new Book(niz[0], Double.parseDouble(niz[1]), Integer.parseInt(niz[2]));
		for (int i = 3; i < niz.length; i++) {
			list.add(findAuthor(niz[i]));
		}
		book.setAuthor(list);
		return book;
	}

	public static void snimiKnjige(ArrayList<Book> listBooks) throws IOException {
		PrintWriter output = new PrintWriter(new FileWriter("Books.txt"));
		for (int i = 0; i < listBooks.size(); i++) {
			output.println(book2String(listBooks.get(i)));
		}
		output.close();
	}

	private static String book2String(Book book) {
		String retVal = book.getName() + "," + book.getPrice() + "," + book.getQuantity();
		for (int i = 0; i < book.getAuthor().size(); i++) {
			retVal = retVal + "," + book.getAuthor().get(i).getId();
		}
		return retVal;
	}

	private static Author findAuthor(String id) {
		for (int i = 0; i < Library.listAuthors.size(); i++) {
			if (id.equals(Library.listAuthors.get(i).getId())) {
				return Library.listAuthors.get(i);
			}
		}
		return null;
	}
}
