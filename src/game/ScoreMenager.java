package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class ScoreMenager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private HashMap<String, Integer> scoreMap = new HashMap<String, Integer>();
	
	public HashMap getList() {
		return scoreMap;
	}
	
	public static void openFile() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:\\sc.txt"))) {
			try {
				ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:\\sc.txt"))) {
				oos.writeObject(this);
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
	}
	
	public void saveFile() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:sc.txt"))) {
			oos.writeObject(this);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
	

}
