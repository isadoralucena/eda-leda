package problems;

import java.util.Arrays;

import org.junit.Before;

public class FlorCeilBinarySearchImplTest {
    private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;

    public FloorCeil implementation;

	@Before
	public void setUp() {
		getImplementation();
	}

	private void getImplementation() {
		this.implementation = new FloorCeilBinarySearchImpl();
	}
}
