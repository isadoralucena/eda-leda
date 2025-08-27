package adt.hashtable.open;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class StudentTestHashtableOpenAddressQuadraticProbing {

	private AbstractHashtableOpenAddress<HashtableElement> table1;
	private AbstractHashtableOpenAddress<HashtableElement> table2;

	private static final int PROPOSED_SIZE = 10;

	@Before
	public void setUp() {
		table1 = new HashtableOpenAddressQuadraticProbingImpl<>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);

		table1.insert(new HashtableElement(10));
		table1.insert(new HashtableElement(15));
		table1.insert(new HashtableElement(2));
		table1.insert(new HashtableElement(12));
		table1.insert(new HashtableElement(4));
		table1.insert(new HashtableElement(8));

		table2 = new HashtableOpenAddressQuadraticProbingImpl<>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);
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
		table2.insert(new HashtableElement(2));
		table2.insert(new HashtableElement(12));

		assertEquals(2, table2.indexOf(new HashtableElement(2)));
		assertEquals(0, table2.indexOf(new HashtableElement(12)));
		assertEquals(1, table2.getCOLLISIONS());
	}

	@Test
	public void testInsertWithMultipleCollisions() {
		table2.insert(new HashtableElement(2));
		table2.insert(new HashtableElement(12));
		table2.insert(new HashtableElement(22));

		assertEquals(2, table2.indexOf(new HashtableElement(2)));
		assertEquals(0, table2.indexOf(new HashtableElement(12)));
		assertEquals(8, table2.indexOf(new HashtableElement(22)));
		assertEquals(3, table2.getCOLLISIONS());
	}

	// -------------------------------
	// REMOVE TESTS
	// -------------------------------

	@Test
	public void testRemoveExistingAndNonExistingElement() {
		int initialSize = table1.size();

		// Remove elemento inexistente (não altera tamanho)
		table1.remove(new HashtableElement(17));
		assertEquals(initialSize, table1.size());

		// Remove elemento existente
		table1.remove(new HashtableElement(12));
		assertEquals(initialSize - 1, table1.size());
		assertNull(table1.search(new HashtableElement(12)));
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

		table2.insert(new HashtableElement(22));
		assertNotNull(table2.search(new HashtableElement(22)));
	}

	// -------------------------------
	// SEARCH TESTS
	// -------------------------------

	@Test
	public void testSearch() {
		assertNotNull(table1.search(new HashtableElement(4)));
		assertNull(table1.search(new HashtableElement(14)));
	}

	// -------------------------------
	// STATE CHECK TESTS
	// -------------------------------

	@Test
	public void testIsEmpty() {
		assertFalse(table1.isEmpty());

		// Remove todos elementos
		table1.remove(new HashtableElement(10));
		table1.remove(new HashtableElement(15));
		table1.remove(new HashtableElement(2));
		table1.remove(new HashtableElement(12));
		table1.remove(new HashtableElement(4));
		table1.remove(new HashtableElement(8));

		assertTrue(table1.isEmpty());
		assertTrue(table2.isEmpty());
	}

	/*
	 * Na tabela com hash quadrática, os índices gerados seguem a fórmula:
	 * h'(k, i) = (h(k) + c1 * i + c2 * i^2) % tableSize
	 * Nesse tipo de tabela, não é garantido que todos os índices da tabela sejam
	 * visitados durante as tentativas de inserção, ou seja, alguns slots podem 
	 * permanecer inacessíveis.
	 * Considerando as constantes usadas para criar table1 (c1 = 3, c2 = 5), é
	 * possível, com a escolha adequada dos elementos inseridos, preencher todos os slots
	 * "por coincidência" ou de forma proposital, alcançando a tabela cheia.
	 */
	@Test
	public void testIsFull() {
		assertFalse(table1.isFull());

		table1.insert(new HashtableElement(1)); // índice 1
		table1.insert(new HashtableElement(3)); // índice 3
		table1.insert(new HashtableElement(7)); // índice 7
		table1.insert(new HashtableElement(9)); // índice 9

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
		assertEquals(6, table1.size());
		table1.insert(new HashtableElement(23));
		assertEquals(7, table1.size());
	}
}