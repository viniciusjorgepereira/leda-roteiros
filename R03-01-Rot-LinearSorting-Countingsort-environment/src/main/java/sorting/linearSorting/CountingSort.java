package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex) {
      if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length && array.length != 0) {
         int maior = 0;
         for (int i = leftIndex; i < rightIndex + 1; i++) {
            if (array[i] > maior) {
               maior = array[i];
            }
         }
         Integer[] tempArray = new Integer[maior + 1];

         for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = 0;
         }
         for (int i = 0; i < rightIndex + 1; i++) {
            tempArray[array[i]] += 1;
         }
         for (int i = 1; i < tempArray.length; i++) {
            tempArray[i] = tempArray[i] + tempArray[i - 1];
         }

         Integer[] auxArray = new Integer[array.length];

         for (int i = rightIndex; i >= 0; i--) {
            auxArray[tempArray[array[i]] - 1] = array[i];
            tempArray[array[i]] -= 1;
         }
         for (int i = 0; i < array.length; i++) {
            array[i] = auxArray[i];
         }
      }
   }
}
