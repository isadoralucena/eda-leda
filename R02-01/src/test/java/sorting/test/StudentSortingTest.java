package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.divideAndConquer.MergeSort;
import sorting.divideAndConquer.QuickSort;
import sorting.divideAndConquer.quicksort3.QuickSortMedianOfThree;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;

	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });

		getImplementation();
	}

	private void getImplementation() {
		this.implementation = new QuickSortMedianOfThree<>();
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays .copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void genericTest(Integer[] array) {
		Integer[] copy1 = {};
		if(array != null && array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);
		}
		implementation.sort(array);
		Arrays.sort(copy1);
		System.out.println(java.util.Arrays.toString(array));
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}

	public void genericTest(Integer[] array, int leftIndex, int rightIndex) {
		Integer[] copy1 = {};

		if (array != null && array.length > 0) {
			copy1 = Arrays.copyOf(array, array.length);
		}

		boolean valid =
			array != null &&
			leftIndex >= 0 &&
			rightIndex < array.length &&
			leftIndex <= rightIndex;

		if (valid) {
			implementation.sort(array, leftIndex, rightIndex);
			Arrays.sort(copy1, leftIndex, rightIndex + 1);
		}

		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testPartialSort01() {
		genericTest(vetorTamPar, 2, 7);
	}

	@Test
	public void testPartialSort02() {
		genericTest(vetorTamImpar, 3, 9);
	}

	@Test
	public void testPartialSort03() {
		genericTest(vetorValoresRepetidos, 2, 6);

	}

	@Test
	public void testPartialSort04() {
		genericTest(vetorValoresIguais, 0, vetorValoresIguais.length - 1);
	}

	@Test
	public void testPartialSort05() {
		genericTest(vetorVazio, 0, 0);
	}

	@Test
	public void testPartialSort06() {
		genericTest(vetorTamImpar, 0, 15);
	}

	@Test
	public void testPartialSort07() {
		genericTest(vetorTamImpar, -1, 10);
	}
}