package library.oop;


public class Author {
	final private String id;
	private String name;
	private String surname;
	private String email;
	final private String gender;
	
	public Author(String id, String name, String surname, String email, String gender) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.gender = gender;
	}
	@Override
	public String toString() {
		return this.name + " " + this.surname;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}
	public String getId() {
		return id;
	}
	
}
