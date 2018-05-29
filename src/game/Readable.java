package game;

public interface Readable {
	String[] playersArray = null;
	Integer[] scoresArray = null;
	
	public String[] getPlayersArray();
	public Integer[] getScoresArray();
	public void addScore(String player, Integer score); 

}
