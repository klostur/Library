package library.oop;

import java.util.ArrayList;

public class Book {
	private String name;
	private double price;
	private int quantity;
	private ArrayList<Author> author;
	
	
	public Book(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Book(String name, double price, int quantity, ArrayList<Author> author) {
		this.name = name;
		this.price = price;
		this.author = author;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < author.size(); i++) {
			sb.append(author.get(i).toString());
			if (i != author.size()-1) { //da ne pise "and" na kraj
				sb.append(" and ");
			}
		}
		return this.name + " by " + sb;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public ArrayList<Author> getAuthor() {
		return author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity -= quantity;
	}

	public void setAuthor(ArrayList<Author> author) {
		this.author = author;
	}
	
}
