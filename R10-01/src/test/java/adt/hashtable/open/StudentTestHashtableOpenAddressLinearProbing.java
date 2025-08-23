package adt.hashtable.open;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class StudentTestHashtableOpenAddressLinearProbing {

	protected AbstractHashtableOpenAddress<HashtableElement> table1;
	protected AbstractHashtableOpenAddress<HashtableElement> table2;

	protected final int PROPOSED_SIZE = 10;

	@Before
	public void setUp() {
		table1 = new HashtableOpenAddressLinearProbingImpl<>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);

		// preenche tabela 1 com alguns elementos sem colisão
		table1.insert(new HashtableElement(2));
		table1.insert(new HashtableElement(3));
		table1.insert(new HashtableElement(4));
		table1.insert(new HashtableElement(5));

		table2 = new HashtableOpenAddressLinearProbingImpl<>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
	}

	// -------------------------------
	// INSERT TESTS
	// -------------------------------

	@Test
	public void testInsertWithoutCollision() {
		table2.insert(new HashtableElement(7));
		assertNotNull(table2.search(new HashtableElement(7)));
		assertEquals(7, table2.indexOf(new HashtableElement(7)));
		assertEquals(0, table2.getCOLLISIONS());
	}

	@Test
	public void testInsertWithSingleCollision() {
		table2.insert(new HashtableElement(2)); // vai no índice 2
		table2.insert(new HashtableElement(12)); // colide com índice 2, vai para 3

		assertEquals(2, table2.indexOf(new HashtableElement(2)));
		assertEquals(3, table2.indexOf(new HashtableElement(12)));
		assertEquals(1, table2.getCOLLISIONS());
	}

	@Test
	public void testInsertWithMultipleCollisions() {
		table2.insert(new HashtableElement(2)); // índice 2
		table2.insert(new HashtableElement(12)); // colisão, índice 3
		table2.insert(new HashtableElement(22)); // colisão, índice 4

		assertEquals(2, table2.indexOf(new HashtableElement(2)));
		assertEquals(3, table2.indexOf(new HashtableElement(12)));
		assertEquals(4, table2.indexOf(new HashtableElement(22)));

		assertEquals(3, table2.getCOLLISIONS()); // 1 no segundo, 2 no terceiro
	}

	// -------------------------------
	// REMOVE TESTS
	// -------------------------------

	@Test
	public void testRemoveExistingAndNonExistingElement() {
		assertEquals(4, table1.size());

		table1.remove(new HashtableElement(12)); // inexistente
		assertEquals(4, table1.size());

		table1.remove(new HashtableElement(5)); // existente
		assertEquals(3, table1.size());
		assertNull(table1.search(new HashtableElement(5)));
	}

	@Test
	public void testRemoveElementWithCollision() {
		table2.insert(new HashtableElement(2));
		table2.insert(new HashtableElement(12));

		table2.remove(new HashtableElement(12));

		assertNull(table2.search(new HashtableElement(12)));
		assertEquals(1, table2.size());
	}

	@Test
	public void testReuseSlotAfterRemove() {
		table2.insert(new HashtableElement(2));
		table2.insert(new HashtableElement(12));
		table2.remove(new HashtableElement(2));

		table2.insert(new HashtableElement(22)); // deve reutilizar espaço
		assertNotNull(table2.search(new HashtableElement(22)));
	}

	// -------------------------------
	// SEARCH TESTS
	// -------------------------------

	@Test
	public void testSearch() {
		assertNotNull(table1.search(new HashtableElement(5))); // existe
		assertNull(table1.search(new HashtableElement(15))); // não existe
	}

	// -------------------------------
	// STATE CHECK TESTS
	// -------------------------------

	@Test
	public void testIsEmpty() {
		assertFalse(table1.isEmpty());
		table1.remove(new HashtableElement(2));
		table1.remove(new HashtableElement(3));
		table1.remove(new HashtableElement(4));
		table1.remove(new HashtableElement(5));
		assertTrue(table1.isEmpty());

		assertTrue(table2.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(table1.isFull());
		for (int i = 0; i < PROPOSED_SIZE - 4; i++) {
			table1.insert(new HashtableElement(100 + i));
		}
		assertTrue(table1.isFull());
		assertFalse(table2.isFull());
	}

	@Test(expected = HashtableOverflowException.class)
	public void testOverflow() {
		for (int i = 0; i < PROPOSED_SIZE; i++) {
			table2.insert(new HashtableElement(i));
		}
		table2.insert(new HashtableElement(999)); // deve lançar exceção
	}

	@Test
	public void testSize() {
		assertEquals(4, table1.size());
	}
}