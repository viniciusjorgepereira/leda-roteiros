package produto;

/**
 * Classe que representa um repositório de produtos usando arrays como estrutura
 * sobrejacente. Alguns métodos (atualizar, remover e procurar) ou executam com
 * sucesso ou retornam um erro. Para o caso desde exercício, o erro será
 * representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 * 
 * Obs: Note que você deve utilizar a estrutura de dados array e não
 * implementações de array prontas da API Collections de Java (como ArrayList,
 * por exemplo).
 * 
 * @author Adalberto
 *
 */
public class RepositorioProdutoNaoPerecivelArray {
	/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private ProdutoNaoPerecivel[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoNaoPerecivelArray(int size) {
		super();
		this.produtos = new ProdutoNaoPerecivel[size];
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		int indice = -1;
		for (int i = 0; i < produtos.length; i++) {
			if (produtos[i].getCodigo() == codigo) {
				indice = i;
			}
		}
		return indice;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		boolean saida = false;
		for (int i = 0; i < produtos.length; i++) {
			if (produtos[i].getCodigo() == codigo) {
				saida = true;
			}
		}
		return saida;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(ProdutoNaoPerecivel produto) {
		int i = 0;
		while(produtos[i].getNome() != null && i < produtos.length) {
			i += 1;
		}
		if (i == produtos.length - 1) {
			expandir();
			i += 1;
		}
		this.produtos[i] = produto;
	}
	
	/*
	 * Método auxiliar para expansao do Array
	 */
	private void expandir() {
		ProdutoNaoPerecivel[] novo = new ProdutoNaoPerecivel[produtos.length * 2];
		for (int i = 0; i < produtos.length; i++) {
			novo[i] = this.produtos[i];
		}
		this.produtos = novo;
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(ProdutoNaoPerecivel produto) {
		boolean atualizou = false;
		for (int i = 0; i < produtos.length; i++) {
			if (produtos[i].getCodigo() == produto.getCodigo()) {
				produtos[i] = produto;
				atualizou = true;
			}
		}
		if (!atualizou) {
			throw new RuntimeException();
		}
	} 

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public ProdutoNaoPerecivel procurar(int codigo) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
