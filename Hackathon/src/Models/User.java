package Models;

public class User extends Models {
	public String Nama;
	public String NIM;
	public User(String NIM, String Nama, Integer TeamID) {
		super(TeamID);
		this.Nama = Nama;
		this.NIM = NIM;
	}
}
