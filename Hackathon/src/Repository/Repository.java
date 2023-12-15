package Repository;
import Utama.Connection;

public interface Repository {
	public void find(String col, String[] condition, boolean join, String tableJoin, Connection conn);
	public void insert(String[] teamName, Connection conn);
	public void findOne(String col, String[] condition, boolean join, String tableJoin, Connection conn);
}
