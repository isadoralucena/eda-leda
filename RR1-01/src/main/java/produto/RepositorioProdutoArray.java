package produto;

public class RepositorioProdutoArray implements RepositorioProduto {
	private Produto[] produtos;
	private int index = -1;

	public RepositorioProdutoArray(int size) {
		this.produtos = new Produto[size];
	}

	private int procurarIndice(int codigo) {
		int i = 0;
		int resposta = -1;
		boolean achou = false;

		while ((i <= index) && !achou) {
			if (produtos[i].getCodigo() == codigo) {
				resposta = i;
				achou = true;
			}
			i = i + 1;
		}
		return resposta;

	}

	@Override
	public boolean existe(int codigo) {
		boolean resposta = false;

		int i = this.procurarIndice(codigo);
		resposta = (i == -1);

		return resposta;

	}

	@Override
	public void inserir(Produto produto) {
		produtos[++index] = produto;
	}

	@Override
	public void atualizar(Produto produto) {
		int i = procurarIndice(produto.getCodigo());
		if (i != -1) {
			produtos[i] = produto;
		} else {
			throw new RuntimeException("Produto nao encontrado");
		}

	}

	@Override
	public void remover(int codigo) {
		int i = this.procurarIndice(codigo);
		if (i != -1) {
			produtos[i] = produtos[index];
			produtos[index] = null;
			index--;
		} else {
			throw new RuntimeException("Produto nao encontrado");
		}
	}

	@Override
	public Produto procurar(int codigo) {
		Produto resposta = null;
		int i = this.procurarIndice(codigo);
		if (i != -1) {
			resposta = produtos[i];
		} else {
			throw new RuntimeException("Produto nao encontrado");
		}

		return resposta;
	}
}
