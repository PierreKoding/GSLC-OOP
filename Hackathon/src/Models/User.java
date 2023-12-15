package Models;

public class User extends Models {
	public String Nama;
	public String NIM;
	public User(Integer TeamID, String Nama, String NIM) {
		super(TeamID);
		this.Nama = Nama;
		this.NIM = NIM;
	}
}
