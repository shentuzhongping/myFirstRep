package test;


public class Single {
	
	private static volatile Single s = null;
	
	private Single() {
		
	}
	public synchronized static Single getInstance() {
		if (s == null) {
			s = new Single();
		}
		return s;
	}

}
