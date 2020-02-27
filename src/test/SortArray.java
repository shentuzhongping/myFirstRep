package test;

import java.util.Arrays;

public class SortArray {
	
	public static void sort (Cat[] array) {
		Cat minc;
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i+1; j < array.length; j++) {
				minIndex = array[i].compareTo(array[j]) >= 0 ? j : i;
			}
			changeSeat(array,i,minIndex);
		}
	}

	private static void changeSeat(Cat[] array,int i, int min) {
		Cat temp;
		temp = array[i];
		array[i] = array[min];
		array[min] = temp;
		
	}
	
	public static void main(String[] args) {
		Cat[] array = {new Cat(3,2),new Cat(3,3),new Cat(4,1)};
		SortArray.sort(array);
		System.out.println(Arrays.toString(array));
	}

}
