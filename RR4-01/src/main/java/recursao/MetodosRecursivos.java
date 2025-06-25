package recursao;

public class MetodosRecursivos {

	private int calcularSomaArray(int[] array, int indexFrom) {
		int result = 0;
		if (indexFrom < array.length) {
			result = array[indexFrom] + calcularSomaArray(array, indexFrom + 1);
		}

		return result;
    }

	public int calcularSomaArray(int[] array){
		return calcularSomaArray(array, 0);
	}

	public long calcularFatorial(int n) {
		long result = 1;
		if (n > 1){
			result = n * calcularFatorial(n-1);
		}
	
		return result;
	}

	public int calcularFibonacci(int n) {
		int result = 1;
		if (n > 1){
			result = calcularFibonacci(n-1) + calcularFibonacci(n-2);
		}
		
		return result;
	}

	private int countNotNull(Object[] array, int indexFrom) {
		int result = (array[indexFrom] != null) ? 1 : 0;
		
		if (indexFrom < array.length - 1) {
			result = result + countNotNull(array, indexFrom + 1);
		}
		return result;
	}

	public int countNotNull(Object[] array) {
		return countNotNull(array, 0);
	}

	public long potenciaDe2(int expoente) {
		if (expoente < 0) {
			throw new IllegalArgumentException("Expoente não pode ser negativo");
		}

		long result = 1;
		if (expoente != 0) {
			result = 2 * potenciaDe2(expoente - 1);
		}
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = termoInicial;
		if (n > 1) {
			result = progressaoAritmetica(termoInicial, razao, n - 1) + razao;
		}
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = termoInicial;
		if (n > 1) {
			result = progressaoGeometrica(termoInicial, razao, n - 1) * razao;
		}
		return result;
	}
}
