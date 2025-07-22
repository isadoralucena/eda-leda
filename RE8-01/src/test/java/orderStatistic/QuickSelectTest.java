package orderStatistic;

import orderStatistic.QuickSelect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuickSelectTest {
    private QuickSelect<Integer> quickSelect;

    @BeforeEach
    public void setUp() {
        quickSelect = new QuickSelect<>();
    }

    @Test
    public void testDistinctElements() {
        Integer[] array = {9, 1, 5, 3, 7, 4};
        assertEquals(1, quickSelect.quickSelect(array, 1));
        assertEquals(4, quickSelect.quickSelect(array, 3));
        assertEquals(9, quickSelect.quickSelect(array, 6));
    }

    @Test
    public void testWithDuplicates() {
        Integer[] array = {4, 2, 2, 9, 5, 2};
        assertEquals(2, quickSelect.quickSelect(array, 1));
        assertEquals(2, quickSelect.quickSelect(array, 2));
        assertEquals(4, quickSelect.quickSelect(array, 4));
    }

    @Test
    public void testSingleElement() {
        Integer[] array = {42};
        assertEquals(42, quickSelect.quickSelect(array, 1));
    }

    @Test
    public void testSortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        assertEquals(1, quickSelect.quickSelect(array, 1));
        assertEquals(5, quickSelect.quickSelect(array, 5));
    }

    @Test
    public void testReverseSortedArray() {
        Integer[] array = {5, 4, 3, 2, 1};
        assertEquals(2, quickSelect.quickSelect(array, 2));
    }

    @Test
    public void testInvalidKTooSmall() {
        Integer[] array = {1, 2, 3};
        assertNull(quickSelect.quickSelect(array, 0));
    }

    @Test
    public void testInvalidKTooLarge() {
        Integer[] array = {1, 2, 3};
        assertNull(quickSelect.quickSelect(array, 4));
    }

    @Test
    public void testNullArray() {
        assertNull(quickSelect.quickSelect(null, 1));
    }

    @Test
    public void testEmptyArray() {
        Integer[] array = {};
        assertNull(quickSelect.quickSelect(array, 1));
    }
}
