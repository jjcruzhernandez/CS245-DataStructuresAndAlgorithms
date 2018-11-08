public class Record {
	private int key;
	private Object data;
	//constructor
	public Record (int key, Object data) {
		this.key = key;
		this.data = data;
	}
	//get key
	public int key() {
		return key;
	}
	//toString method to print out record 
	public String toString() {
		return key + " " + data.toString();
	}
}
