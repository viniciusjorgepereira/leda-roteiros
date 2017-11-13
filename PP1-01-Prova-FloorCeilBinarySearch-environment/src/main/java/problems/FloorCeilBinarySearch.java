package problems;

import java.util.Scanner;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: - Algoritmo in-place (nao pode usar memoria extra a nao ser
 * variaveis locais) - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String[] seq = teclado.nextLine().split(" ");
		int valor = teclado.nextInt();
		Integer[] a = new Integer[seq.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(seq[i]);
		}
		System.out.println(new FloorCeilBinarySearch().floor(a, valor));
	}

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer saida = null;
		int meio = array.length - 1 / 2;
		if (x > 0) {
			if (x <= meio) {
				saida = buscaFloor(array, 0, meio, x);
			} else {
				saida = buscaFloor(array, meio + 1, array.length - 1, x);
			}
		}
		return saida;
	}

	// Auxilia o metodo do Floor
	private static Integer buscaFloor(Integer[] a, int n, int meio, int x) {
		if (a[0] > x) {
			return null;
		}
		if (n == meio) {
			return a[n];
		}
		if (a[n] > x) {
			return a[n - 1];
		}
		return buscaFloor(a, n + 1, meio, x);
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		Integer saida = null;
		if (x > 0) {
			saida = buscaCeil(array, array.length - 1, x);
		}
		return saida;
	}

	// Auxilia o metodo do Ceil
	private static Integer buscaCeil(Integer[] a, int n, int x) {
		if (a[a.length - 1] < x) {
			return null;
		}
		if (a[0] > x) {
			return a[0];
		}
		if (a[n] < x) {
			return a[n + 1];
		}
		return buscaCeil(a, n - 1, x);
	}
}
