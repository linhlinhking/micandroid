package a.sort;

import java.util.Arrays;

public class MergeSort {

	public static int[] sort(int[] data) {
		if (data == null)
			throw new NullPointerException("Do not let the data array null");
		int[] tmp = new int[data.length];

		return tmp;
	}

	private static void mergeSort(int[] data, int start, int end) {
	}

	private static int[] merge(int[] dataA, int[] dataB) {
		int[] orderData = new int[dataA.length + dataB.length];
		int indexA = 0, indexB = 0;

		for (int i = 0; i < orderData.length; i++) {
			if (indexA >= dataA.length) {
				orderData[i] = dataB[indexB++];
			} else if (indexB >= dataB.length) {
				orderData[i] = dataA[indexA++];
			} else if (dataA[indexA] > dataB[indexB]) {
				orderData[i] = dataB[indexB++];
			} else {
				orderData[i] = dataB[indexA++];
			}
		}
		return orderData;
	}

	public static void main(String[] args) {
		int[] a = { 5, 4, 6, 8, 2, 1, 9, 3, 7 };
		int[] b = { 7, 8, 0, 3, 7, 1, 4, 5, 2 };
		System.out.println(Arrays.toString(a));
		int[] temp = null ;
		temp = merge(a,b) ;
		System.out.println(Arrays.toString(temp));
	}
}
