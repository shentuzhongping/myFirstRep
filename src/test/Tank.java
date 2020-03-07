package test;

public class Tank extends Move{
	int i = 1;
	
	Tank () {
		this.list.add(this);
	}
	
	public void move(){
		System.out.println(this);
		this.list.remove(this);
		System.out.println(i);
	}
	
}
