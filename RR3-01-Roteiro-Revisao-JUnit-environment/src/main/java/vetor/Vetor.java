package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor {

	// O array interno onde os objetos manipulados são guardados
	private Object[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator comparadorMaximo;
	private Comparator comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = 0;
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(Object o) {
		this.arrayInterno[indice] = o;
		this.indice += 1;
	}

	// Remove um objeto do vetor
	public Object remover() {
		this.arrayInterno[indice-1] = null;
		this.indice -= 1;
		return null;
	}

	// Procura um elemento no vetor
	public Object procurar(Object o) {
		int i = 0;
		while (i < arrayInterno.length && !arrayInterno.equals(o)) {
			i += 1;
		}
		return arrayInterno[i];
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		boolean vazio = false;
		if (this.indice == 0) {
			vazio = true;
		}
		return vazio;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		boolean cheio = false;
		if (this.indice == this.arrayInterno.length) {
			cheio = true;
		}
		return cheio;
	}
}
