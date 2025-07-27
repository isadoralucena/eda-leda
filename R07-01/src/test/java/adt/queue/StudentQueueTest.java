package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(10);
		queue2.enqueue(20);
	}

	private void getImplementations() {
		queue1 = new CircularQueue<>(5);
		queue2 = new CircularQueue<>(2);
		queue3 = new CircularQueue<>(3);
	}

	@Test
	public void testHead() {
		assertEquals(Integer.valueOf(1), queue1.head());
		assertEquals(Integer.valueOf(10), queue2.head());
		assertNull(queue3.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertTrue(queue3.isEmpty());
	}

	@Test
	public void testIsFull() throws QueueOverflowException {
		assertFalse(queue1.isFull());
		assertTrue(queue2.isFull());

		queue3.enqueue(5);
		queue3.enqueue(6);
		queue3.enqueue(7);
		assertTrue(queue3.isFull());
	}

	@Test
	public void testEnqueue() throws QueueOverflowException {
		queue1.enqueue(99);
		queue1.enqueue(100);
		assertTrue(queue1.isFull());
		assertEquals(Integer.valueOf(1), queue1.head());
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(30);
	}

	@Test
	public void testDequeue() throws QueueUnderflowException {
		assertEquals(Integer.valueOf(1), queue1.dequeue());
		assertEquals(Integer.valueOf(2), queue1.head());
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		queue3.dequeue();
	}

	@Test
	public void testEstadoInicial() {
		assertTrue(new CircularQueue<>(10).isEmpty());
		assertFalse(new CircularQueue<>(10).isFull());
	}

	@Test
	public void testDequeueEEnqueueCicloCircular() throws Exception {
		Queue<Integer> queue = new CircularQueue<>(3);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		assertTrue(queue.isFull());

		assertEquals(Integer.valueOf(1), queue.dequeue());
		assertFalse(queue.isFull());

		queue.enqueue(4);
		assertTrue(queue.isFull());

		assertEquals(Integer.valueOf(2), queue.dequeue());
		assertEquals(Integer.valueOf(3), queue.dequeue());
		assertEquals(Integer.valueOf(4), queue.dequeue());
		assertTrue(queue.isEmpty());
	}

	@Test
	public void testEsvaziamentoCompleto() throws Exception {
		while (!queue1.isEmpty()) {
			queue1.dequeue();
		}
		assertTrue(queue1.isEmpty());
		assertNull(queue1.head());
	}
}