package ist.barline.model;

import java.sql.Timestamp;

public class Bar {
	private Timestamp time;
	private int length;
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
	
}
