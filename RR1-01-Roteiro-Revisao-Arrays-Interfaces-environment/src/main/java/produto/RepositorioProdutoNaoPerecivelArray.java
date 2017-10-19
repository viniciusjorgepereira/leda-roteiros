package produto;

/**
 * Classe que representa um repositÃ³rio de produtos usando arrays como estrutura
 * sobrejacente. Alguns mÃ©todos (atualizar, remover e procurar) ou executam com
 * sucesso ou retornam um erro. Para o caso desde exercÃ­cio, o erro serÃ¡
 * representado por uma RuntimeException que nÃ£o precisa ser declarada na
 * clausula "throws" do mos metodos.
 * 
 * Obs: Note que vocÃª deve utilizar a estrutura de dados array e nÃ£o
 * implementaÃ§Ãµes de array prontas da API Collections de Java (como ArrayList,
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
	 * inicial Ã© -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoNaoPerecivelArray(int size) {
		super();
		this.produtos = new ProdutoNaoPerecivel[size];
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse mÃ©todo Ã© util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse mÃ©todo.
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
		while(i < produtos.length && produtos[i] != null) {
			i += 1;
		}
		if (i == produtos.length) {
			expandir();
			i += 1;
		}
		this.produtos[i] = produto;
		this.index += 1;
	}
	
	/*
	 * Metodo auxiliar para expansao do Array
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
	 * esteja no array. Note que, para localizacao, o cÃ³digo do produto serÃ¡
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
		int i = 0;
		while (i < produtos.length && produtos[i].getCodigo() != codigo) {
			i += 1;
		}
		if (i == produtos.length) {
			throw new RuntimeException();
		}
		produtos[i] = null;
		organizar();
		this.index -= 1;
	}
	
	/**
	 * Metodo auxiliar para organizar o array retirando os "buracos"
	 */
	private void organizar() {
		int i = 0;
		while(i < produtos.length && produtos[i] != null) {
			i += 1;
		}
		produtos[i] = produtos[i + 1];
		for (int j = i + 1; j < produtos.length - 1; j++) {
			produtos[j] = produtos[j + 1];
		}
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public ProdutoNaoPerecivel procurar(int codigo) {
		int i = 0;
		while (i < produtos.length && produtos[i].getCodigo() != codigo) {
			i += 1;
		}
		if (i == produtos.length) {
			throw new RuntimeException();
		}
		return produtos[i];
	}
}
