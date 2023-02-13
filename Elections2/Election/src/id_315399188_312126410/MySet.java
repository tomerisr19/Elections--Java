package id_315399188_312126410;

import java.io.Serializable;
import java.util.Arrays;

import id_315399188_312126410.exeptions.IDMismachExeption;
import id_315399188_312126410.exeptions.NotInRange;
import id_315399188_312126410.exeptions.PartyDosentExcist;

public class MySet<T extends Citizen> implements Serializable {
	private final int ENLARGE_FACTOR = 2;
	private T[] arr;
	private int currentSize;

	public MySet() {
		this.arr = (T[]) new Citizen[ENLARGE_FACTOR];
		this.currentSize = 0;
	}

	public boolean add(T newValue) {
		for (int i = 0; i < currentSize; i++) {
			if (newValue.equals(arr[i])) {
				return false;
			}
		}
		if (currentSize == arr.length) {
			enlargeArray();
		}
		arr[currentSize] = newValue;
		currentSize++;
		return true;
	}

	private void enlargeArray() {
		arr = Arrays.copyOf(arr, arr.length * 2);
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public T get(int index) {
		return arr[index];
	}

	@Override
	public String toString() {
		String str = " [ ";
		for (int i = 0; i < currentSize; i++) {
			str += arr[i] + " ";
		}
		str += "]";
		return str;

	}

}
