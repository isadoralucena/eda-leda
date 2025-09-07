package adt.heap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import adt.heap.extended.FloorCeilHeap;
import adt.heap.extended.FloorCeilHeapImpl;
import orderStatistic.OrderStatistics;
import orderStatistic.OrderStatisticsHeapImpl;

public class StudentMaxHeapTest {

	Heap<Integer> heap;
	OrderStatistics<Integer> orderStatistics;
	FloorCeilHeap floorCeilHeap;

	@Before
	public void setUp() {
		Comparator<Integer> comparator = (o1, o2) -> o2.compareTo(o1);
		heap = new HeapImpl<Integer>(comparator);
		orderStatistics = new OrderStatisticsHeapImpl<>();
		floorCeilHeap = new FloorCeilHeapImpl(comparator);
	}

	@Test
	public void testBuild() {
		heap.buildHeap(new Integer[] { 82, 6, 99, 12, 34, 64, 58, 1 });

		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 99, 12, 82, 6, 34, 64, 58, 1 });
		verifyHeap(new Integer[] { 99, 34, 82, 6, 12, 64, 58, 1 });
	}

	@Test
	public void testInsert() {
		heap.insert(8);
		heap.insert(12);
		heap.insert(-2);
		heap.insert(7);
		heap.insert(8);
		heap.insert(-5);
		heap.insert(14);
		heap.insert(3);
		heap.insert(-10);
		heap.insert(0);

		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 14, 8, 12, 7, 8, -5, -2, 3, -10, 0 });
	}

	@Test
	public void testRemove() {
		heap.insert(22);
		heap.insert(45);
		heap.insert(38);
		heap.insert(17);
		heap.insert(40);
		heap.insert(15);
		heap.insert(26);
		heap.insert(79);
		heap.insert(53);
		heap.insert(30);

		assertEquals(new Integer(79), heap.extractRootElement());
		assertEquals(new Integer(53), heap.extractRootElement());
		assertEquals(new Integer(45), heap.extractRootElement());
		assertEquals(new Integer(40), heap.extractRootElement());
		assertEquals(new Integer(38), heap.extractRootElement());

		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 22, 17, 15, 26, 30 });
	}

	@Test
	public void testEmptyHeap() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		assertEquals(null, heap.rootElement());
		assertEquals(null, heap.extractRootElement());
	}

	@Test
	public void testInsertDuplicates() {
		heap.insert(10);
		heap.insert(10);
		heap.insert(10);

		assertEquals(3, heap.size());
		verifyHeap(new Integer[] { 10, 10, 10 });
	}

	@Test
	public void testExtractAll() {
		heap.insert(5);
		heap.insert(15);
		heap.insert(10);

		Integer[] extracted = new Integer[3];
		for (int i = 0; i < 3; i++) {
			extracted[i] = heap.extractRootElement();
		}

		assertArrayEquals(new Integer[] { 15, 10, 5 }, extracted);
	}

	@Test
	public void testSort() {
		assertArrayEquals(new Integer[] { 5, 6, 12, 20, 34, 43, 49, 92 },
				heap.heapsort(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertArrayEquals(new Integer[] {}, heap.toArray());
	}

	private void verifyHeap(Integer[] expected) {
		boolean isHeap = true;

		Comparable<Integer>[] original = heap.toArray();

		Arrays.sort(expected);
		Arrays.sort(original);

		if (Arrays.equals(expected, original) == false)
			isHeap = false;

		original = heap.toArray();

		for (int i = 0; i < original.length; i++) {
			if (2 * i + 1 < original.length && original[i].compareTo((Integer) original[2 * i + 1]) < 0)
				isHeap = false;
			if (2 * i + 2 < original.length && original[i].compareTo((Integer) original[2 * i + 2]) < 0)
				isHeap = false;
		}

		assertTrue(isHeap);
	}

	@Test
	public void testHeapsortEmptyArray() {
		assertArrayEquals(new Integer[] {}, heap.heapsort(new Integer[] {}));
	}

	@Test
	public void testHeapsortSingleElement() {
		assertArrayEquals(new Integer[] { 42 }, heap.heapsort(new Integer[] { 42 }));
	}

	@Test
	public void testHeapifyAfterMultipleInserts() {
		Integer[] values = { 3, 1, 6, 5, 2, 4 };
		for (int v : values)
			heap.insert(v);

		Integer[] sortedDesc = { 6, 5, 4, 3, 2, 1 };
		Integer[] extracted = new Integer[6];
		for (int i = 0; i < 6; i++) {
			extracted[i] = heap.extractRootElement();
		}

		assertArrayEquals(sortedDesc, extracted);
		assertTrue(heap.isEmpty());
	}

	@Test
	public void testOrderStatistics() {
		Integer[] values = { 7, 3, 5, 1, 9 };

		assertEquals(Integer.valueOf(1), orderStatistics.getOrderStatistics(values, 1));
		assertEquals(Integer.valueOf(3), orderStatistics.getOrderStatistics(values, 2));
		assertEquals(Integer.valueOf(5), orderStatistics.getOrderStatistics(values, 3));
		assertEquals(Integer.valueOf(7), orderStatistics.getOrderStatistics(values, 4));
		assertEquals(Integer.valueOf(9), orderStatistics.getOrderStatistics(values, 5));
	}

	@Test
	public void testFloorWithMultipleCandidates() {
		Integer[] array = { 4, 12, 7, 15, 9 };
		double number = 13;
		Integer expectedFloor = 12;

		Integer actualFloor = floorCeilHeap.floor(array, number);

		assertEquals(expectedFloor, actualFloor);
	}

	@Test
	public void testCeilWithMultipleCandidates() {
		Integer[] array = { 4, 12, 7, 15, 9 };
		double number = 8;
		Integer expectedCeil = 9;

		Integer actualCeil = floorCeilHeap.ceil(array, number);

		assertEquals(expectedCeil, actualCeil);
	}

	@Test
	public void testFloorNoCandidates() {
		Integer[] array = { 10, 20, 30 };
		double number = 5;
		Integer expectedFloor = null;

		Integer actualFloor = floorCeilHeap.floor(array, number);

		assertEquals(expectedFloor, actualFloor);
	}

	@Test
	public void testCeilNoCandidates() {
		Integer[] array = { 1, 2, 3 };
		double number = 10;
		Integer expectedCeil = null;

		Integer actualCeil = floorCeilHeap.ceil(array, number);

		assertEquals(expectedCeil, actualCeil);
	}
}
