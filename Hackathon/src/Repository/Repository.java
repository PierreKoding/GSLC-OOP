package Repository;
import java.util.ArrayList;

import Utama.Connection;

public interface Repository {
	public void find(String col, String[] condition, Boolean join, String tableJoin, Connection conn);
	public void findOne(String col, String[] condition, boolean join, String table, Connection conn);
	public void insert(String[] teamName, Connection conn);
}
