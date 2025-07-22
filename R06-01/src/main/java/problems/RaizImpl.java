package problems;

/**
 * Classe com metodos para calcular raiz n-esima de um numero com aproximacao
 * e para encontrar os limites que dividem um array em 3 partes de mesmo tamanho
 * 
 * @author adalbertocajueiro
 *
 */
public class RaizImpl implements Raiz {

	public double raiz(int numero, int raiz, double erro){
		double resposta = buscaBinaria(numero, raiz, erro, 0, numero);
		return resposta;
	}

	public double buscaBinaria(int numero, int raiz, double erro, double left, double right) {
		double meio = (left + right) / 2;
		double potencia = myPow(meio, raiz);
		double diferenca = myAbs(potencia - numero);
		double resposta = 0;
		
		if (diferenca < erro) {
			resposta = meio;
		} else if (potencia < numero) {
			resposta = buscaBinaria(numero, raiz, erro, meio, right);
		} else {
			resposta = buscaBinaria(numero, raiz, erro, left, meio);
		}

		return resposta;
	}

	public double myPow(double base, int expoente){
		double resposta = 0;

		if (expoente == 0){
			resposta = 1;
		} else if (expoente == 1) {
			resposta = base;
		} else{
			resposta = base * myPow(base, expoente - 1);
		}

		return resposta;
	}

	public double myAbs(double numero){
		double resposta = numero;

		if (numero < 0){
			resposta = -numero;
		}

		return resposta;
	}
}