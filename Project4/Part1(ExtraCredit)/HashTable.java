
public class HashTable {
	private String[] table; //the HashTable
	private int count; //the number of elements in the table
	
	//constructor that creates HashTable of n size and sets count to zero
	public HashTable(int n){
		this.table = new String[n];
		this.count = 0;
	}
	
	//method that returns the length of the table
	public int length(){
		return table.length;
	}
	
	//method that returns the id of a city, if city is not found return -1
	public int getId(String city){
		for(int i = 0; i < table.length; i++){ //iterate through table
			if(table[i] != null && table[i].equals(city)){ //check is city is in table
				return i;
			}
		}
		return -1;
	}
	
	//method that returns the previous prime number
	public int primeNum(int q){
		q = q -1;
		boolean isPrime = true;
		while(isPrime == true){
			for(int i = 2; i <= q/2; i++){ //checks that q is not divisible by any numbers before it
				if(q % i == 0){
					isPrime = false;
				}
			}
			q = q - 1; //if number is not prime then subtract one and check again
		}
		return q;
	}
	
	//method that returns the next prime number
	public int primeNum2(int q){
		q = q  + 1; 
		boolean isPrime = true;
		while(isPrime == true){
			for(int i = 2; i <= q/2; i++){ //checks that q is not divisible by any numbers before it
				if(q % i == 0){
					isPrime = false;
				}
			}
			q = q + 1; //if number is not prime then add one and check again
		}
		return q;
	}
	
	//a method that resizes and rehashes the table is necessary
	public String[] resizeAndRehash(String[] table){
		int n = table.length;
		int prime = primeNum2(n*2); //gets next prime number after twice the size of the length for new table
		String[] newTable = new String[prime]; //create new table twice the size 
		for(int i = 0; i < table.length; i++){ //rehash
			newTable[i] = table[i];
		}
		return newTable;
	}
	
	//method that creates hashCode based on polynomial hash code
	public int hashCode(String city){
		int i;
		int r = 0;
		char c;
		for(i = 0; i < city.length(); i++){ //iterate through each char in string to create unique hashCode
			c = city.charAt(i);
			r = (int) c + 39*r;
		}
		return Math.abs(r) % table.length; //final hashCode is the absolute value of r mod the length of the table
	}
	
	//method that inserts a city into the HashTable based on closed hashing & double hashing
	public void insert(String city){
		int length = table.length;
		int h = hashCode(city); //calculates hashCode
		int q = primeNum(length); //finds prime number
		int d = q - (h%q); //calculation for double hashing
		int j = 1; //j in double hashing formula
		int index = h; //index that String should be inserted at
		while(table[index] != null){
			index = (h + (j*d)) % length; //double hash if the position at table is taken
			j = j + 1; 
			if(count/length > 0.5){ //resize and rehash is number of elements in table/table size is greater than 0.5
				table = resizeAndRehash(table);
			}
		}
		table[index] = city; //add string to table at index calculated
		count++; //add to count, number of elements in table
	}
}
 