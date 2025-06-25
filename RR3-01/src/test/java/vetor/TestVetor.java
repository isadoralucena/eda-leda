package vetor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestVetor {
	private Vetor<Aluno> vetor;

	@Before
    public void setUp() {
        vetor = new Vetor<>(5);
        vetor.setComparadorMaximo(new ComparatorMaximo<>());
        vetor.setComparadorMinimo(new ComparatorMinimo<>());
    }

	@Test
    public void testInserirEProcurar() {
        Aluno a1 = new Aluno("João", 8.5);
        vetor.inserir(a1);
        assertEquals(a1, vetor.procurar(new Aluno("João", 8.5)));
    }

    @Test
    public void testRemover() {
        Aluno a1 = new Aluno("Maria", 7.0);
        vetor.inserir(a1);
        Aluno removido = vetor.remover(new Aluno("Maria", 7.0));
        assertEquals(a1, removido);
        assertNull(vetor.procurar(a1));
    }

    @Test
    public void testMaximo() {
        vetor.inserir(new Aluno("Ana", 6.0));
        vetor.inserir(new Aluno("Beto", 9.2));
        vetor.inserir(new Aluno("Carlos", 8.3));

        Aluno esperado = new Aluno("Beto", 9.2);
        assertEquals(esperado, vetor.maximo());
    }

    @Test
    public void testMinimo() {
        vetor.inserir(new Aluno("Ana", 6.0));
        vetor.inserir(new Aluno("Beto", 9.2));
        vetor.inserir(new Aluno("Carlos", 8.3));

        Aluno esperado = new Aluno("Ana", 6.0);
        assertEquals(esperado, vetor.minimo());
    }

    @Test
    public void testIsVazioECheio() {
        assertTrue(vetor.isVazio());

        vetor.inserir(new Aluno("João", 8.0));
        assertFalse(vetor.isVazio());

        vetor.inserir(new Aluno("Maria", 7.0));
        vetor.inserir(new Aluno("José", 6.0));
        vetor.inserir(new Aluno("Ana", 9.0));
        vetor.inserir(new Aluno("Rita", 5.0));

        assertTrue(vetor.isCheio());
    }
}
