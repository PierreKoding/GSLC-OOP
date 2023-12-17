package Repository;
import java.util.ArrayList;

import Models.Models;
import Utama.Connection;

public interface Repository {
	public ArrayList<Models> find(String column, String[] condition, Boolean join, String joinTable, Connection connection);
	public Models findOne(String col, String[] condition, boolean join, String table, Connection conn);
	public void insert(String[] userData, Connection conn);
}
