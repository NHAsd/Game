package game;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class GlobalScoreList implements Readable {
	private HashMap<String, Integer> scores = new HashMap<String, Integer>();
	private String[] playersArray;
	private Integer[] scoresArray;
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public GlobalScoreList() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/game", "root", "sql123");
			Statement statement = (Statement) connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT * FROM scores");

			while(resultSet.next()) {
				scores.put(resultSet.getString("player"), resultSet.getInt("score"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String[] getPlayersArray() {
		int i = 0;
		playersArray = new String[scores.size()];
		for (String a : scores.keySet()) {
			playersArray[i] = a;
			i++;
		}	
		return playersArray;
	}

	@Override
	public Integer[] getScoresArray() {
		int i = 0;
		scoresArray = new Integer[scores.size()];
		for (String a : scores.keySet()) {
			scoresArray[i] = scores.get(a);
			i++;
		}	
		return scoresArray;
	}
	
	public void upDate(HashMap<String, Integer> localList) {
		for(String a : localList.keySet()) {
			for(String b:scores.keySet()) {
				if(a.equals(b)) {
					if(localList.get(a)>scores.get(b)){
			//			scores.put(a, localList.get(a));
	    				try {
							preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE scores SET score =? WHERE player = ?");
		    				preparedStatement.setInt(1, localList.get(a));
		    				preparedStatement.setString(2, a);
		    				preparedStatement.executeUpdate();
						} catch (SQLException e) {
	
							e.printStackTrace();
						}

						
					}
				}else {
					try {
						preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO scores(id, player, score) VALUES(null, ? , ?)");
						preparedStatement.setString(1, a);
						preparedStatement.setInt(2, localList.get(a));
						preparedStatement.executeUpdate();
					} catch (SQLException e) {
	
						e.printStackTrace();
					}

				}
			}
		}
	}

	@Override
	public void addScore(String player, Integer score) {

		try {

			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/game", "root", "sql123");

			if(scores.size() == 0) {


				preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO scores(id, player, score) VALUES(null, ? , ?)");
				preparedStatement.setString(1, player);
				preparedStatement.setInt(2, score);
				preparedStatement.executeUpdate();
			} else {
		        for(String a: scores.keySet()) {
		        	if(a.equals(player)) {
		        		if(scores.get(player)<score) {
		    				preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE scores SET score =? WHERE player = ?");
		    				preparedStatement.setInt(1, score);
		    				preparedStatement.setString(2, player);
		    				preparedStatement.executeUpdate();
// dac pstmt.close();
			        		return;
		        		}
		        	//	break;
		        		return;
		        	}

		        }
				preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO scores(id, player, score) VALUES(null, ? , ?)");
				preparedStatement.setString(1, player);
				preparedStatement.setInt(2, score);
				preparedStatement.executeUpdate();
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}


	}

}
