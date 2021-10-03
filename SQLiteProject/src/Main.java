
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		Connection con = null;
		try {
			//SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			//SQLite 데이터베이스 파일에 연결
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:"+dbFile);
			
			//데이터 조회
			System.out.println("\n***데이터 조회***");
			Statement stat1 = con.createStatement();
			String sql1 = "select * from artists";
			ResultSet rsl = stat1.executeQuery(sql1);
			while(rsl.next()) {
				String id=rsl.getString("id");
				String name=rsl.getString("name");
				System.out.println(id+" "+name);
			}
			stat1.close();
			
			//데이터 추가
			System.out.println("\n*** 새 데이터 추가 ***");
			Statement stat2 = con.createStatement();
			String sql2 = "insert into artists (name, a_type, debut, hit_song, regdate)" + "values ('방탄소년단', '남성', '2010', 'DNA', datetime('now', 'localtime'));";
			int cnt = stat2.executeUpdate(sql2);
			if(cnt>0) System.out.println("새로운 데이터가 추가되었습니다.");
			else System.out.println("[Error] 데이터 추가 오류!");
			stat2.close();
			
			//데이터 삭제
			System.out.println("\n*** 데이터 삭제 ***");
			Statement stat4 = con.createStatement();
			String sql4 = "delete from artists where id=1;";
			cnt=stat4.executeUpdate(sql4);
			if(cnt>0) System.out.println("데이터가 삭제되었습니다!");
			else System.out.println("[Error] 데이터 삭제 오류!");
			stat4.close();
			
			//데이터 수정
			System.out.println("\n***데이터 수정 ***");
			Statement stat3 = con.createStatement();
			String sql3="update artists set debut='2020년대'"+"where id=4;";
			cnt=stat3.executeUpdate(sql3);
			if(cnt>0) {
				System.out.println("데이터가 수정되었습니다!");
			}
			else
				System.out.println("[Error] 데이터 수정 오류!");
			stat3.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {}
			}
		}

	}

}
