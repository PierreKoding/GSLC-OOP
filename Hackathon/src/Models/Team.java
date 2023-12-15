package Models;

public class Team extends Models {
	public String NamaTeam;
	public Team(Integer TeamID, String NamaTeam) {
		super(TeamID);
		this.NamaTeam = NamaTeam;
	}
}
