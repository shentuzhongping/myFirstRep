package test;

public class Cat implements Comparable<Cat>{
	
	int weight;
	int height;
	
	Cat(int weight,int height) {
		this.weight = weight;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Cat [weight=" + weight + ", height=" + height + "]";
	}

	@Override
	public int compareTo(Cat c) {
		if (this.weight == c.weight) {
			if (this.height == c.height) {
				return 0;
			} else {
				return this.height > c.height ? 1 : -1;
			}
		} else {
			return this.weight > c.weight ? 1 : -1;
		}
		
	}
	
	

}
