package library.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Library {
	public static ArrayList<Author> listAuthors = new ArrayList<>();
	public static ArrayList<Book> listBooks = new ArrayList<>();
	public static ArrayList<Book> shoppingCart = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		listAuthors = IOAuthor.ucitajAutore();
		listBooks = IOBook.ucitajKnjige();
		String input = "";
		while (!input.equals("x")) {
			menu();
			input = ucitajSaTastature();

			switch (input) {
			case "1":
				addAuthor();
				break;
			case "2":
				printAuthors();
				break;
			case "3":
				deleteAuthor();
				break;
			case "4":
				addBook();
				break;
			case "5":
				printBooks();
				break;
			case "6":
				buyBooks();
				break;
			case "x":
				IOAuthor.snimiAutore(listAuthors);
				IOBook.snimiKnjige(listBooks);
				break;
			default:
				break;
			}
		}
	}

	private static void printShoppingCart() {
		System.out.println("Your shopping cart");
		if (shoppingCart.isEmpty()) {
			System.out.println("Is empty. For now ;)"); // San Remo (probaj, druzicemo se ;))
		} else {
			for (Book book : shoppingCart) {
				System.out.println((shoppingCart.indexOf(book)+1) + ") " + book.toString());
			}
		}
//		System.out.println();
	}

	private static void buyBooks() {
		boolean buying = true;
		while (buying == true) {
			System.out.println("--------------------");
			System.out.println("Choose a book to buy");
			printBooks();
			System.out.println((listBooks.size() + 1) + ") Exit");
			int input = Integer.parseInt(ucitajSaTastature()) - 1;
			buying = buyABook(input); // ako korisnik unese exit, metoda vraca false i prekida while petlju
		}
	}

	private static boolean buyABook(int input) {
		int end = listBooks.size();
		if (input == end)
			return false;
		if (input >= 0 && input <= listBooks.size()) { // lista ponudjenih knjiga
			if (listBooks.get(input).getQuantity() > 0) { // proverava da li ima na stanju
				listBooks.get(input).setQuantity(1);
				shoppingCart.add(listBooks.get(input));
			} else {
				System.out.println("Not currently available.");
			}
		} else {
			System.out.println("Enter a valid number.");
		}
		return true;
	}

	private static void printBooks() {
		for (Book book : listBooks) {
			System.out.println((listBooks.indexOf(book) + 1) + ") " + book.toString() + " / Available quantity: "
					+ book.getQuantity() + " / Price " + book.getPrice());
		}
	}

	private static void addBook() {
		System.out.println("Input name of the book:");
		String name = ucitajSaTastature();
		System.out.println("Input the price of the book:");
		double price = Double.parseDouble(ucitajSaTastature());
		System.out.println("Input the quantity:");
		int quantity = Integer.parseInt(ucitajSaTastature());
		ArrayList<Author> authors = newAuthorList();
		Book book = new Book(name, price, quantity, authors);
		listBooks.add(book);
	}

	private static ArrayList<Author> newAuthorList() {
		ArrayList<Author> autori = new ArrayList<>();
		while (true) {
			System.out.println("Input the author, add a new author or x to exit.");
			printAuthors();
			System.out.println((listAuthors.size() + 1) + ") Add new author");
			System.out.println("x) Exit");
			String input = ucitajSaTastature();
			if (input.equals("x")) {
				break;
			}
			if (Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= listAuthors.size()) {
				autori.add(listAuthors.get(Integer.parseInt(input) - 1));
			}
			if (input.equals(String.valueOf(listAuthors.size() + 1))) {
				addAuthor();
			}
		}
		return autori;
	}

	private static void deleteAuthor() {
		printAuthors();
		listAuthors.remove(Integer.parseInt(ucitajSaTastature()) - 1);
	}

	private static void addAuthor() {
		System.out.println("Name:");
		String name = ucitajSaTastature();
		System.out.println("Surname:");
		String surname = ucitajSaTastature();
		System.out.println("Email:");
		String email = ucitajSaTastature();
		System.out.println("Gender:");
		String gender = ucitajSaTastature();
		listAuthors.add(new Author(String.valueOf((listAuthors.size() + 1)), name, surname, email, gender));
	}

	private static void printAuthors() {
		for (Author author : listAuthors) {
			System.out.println((listAuthors.indexOf(author) + 1) + ") " + author.toString());
		}
	}

	private static void menu() {
		System.out.println("---------------");
		printShoppingCart();
		total();
		System.out.println("Library menu");
		System.out.println("1) Add Author");
		System.out.println("2) Print Authors");
		System.out.println("3) Delete Author");
		System.out.println("4) Add books");
		System.out.println("5) Print books");
		System.out.println("6) Buy book");
		System.out.println("x) Exit");
		System.out.println("---------------");
	}

	private static void total() {
		double total = 0;
		for (int i = 0; i < shoppingCart.size(); i++) {
			total += shoppingCart.get(i).getPrice();
		}
		System.out.printf("Total price is: %.1f\n", total);
		
	}

	private static String ucitajSaTastature() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		try {
			s = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

}
