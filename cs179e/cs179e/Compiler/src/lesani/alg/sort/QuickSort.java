package lesani.alg.sort;

import java.util.Random;

public class QuickSort  {


   public static void quickSort(int[] array) {
      // check for empty or null array
      if (array == null || array.length == 0) {
         return;
      }
      quickSort(array, 0, array.length - 1);
   }

   public static void quickSort(int[] array, int low, int high) {
      if (low >= high)
         return;

      int pivot = array[low];

      int i = low - 1; // To prevent i from passing the right end.
      int j = high + 1;

      while (true) {
         do {
            i = i + 1;
         } while (array[i] < pivot);
         do {
            j = j - 1;
         } while (array[j] > pivot);

         if (i < j) {
            swap(array, i, j);
         } else
            break;

      }
      quickSort(array, low, j);
      quickSort(array, j + 1, high);
   }

   private static void swap(int[] array, int i, int j) {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
   }

// -----------------------------------------------------------------


   public static int[] makeRandomArray() {
      int[] numbers;
      final int SIZE = 7;
      final int MAX = 20;
      numbers = new int[SIZE];
      Random generator = new Random();
      for (int i = 0; i < numbers.length; i++) {
         numbers[i] = generator.nextInt(MAX);
      }
      return numbers;
   }


//   public void testNull() {
//      quickSort(null);
//   }
//
//   public void testEmpty() {
//      Quicksort sorter = new Quicksort();
//      sorter.sort(new int[0]);
//   }
//
//   public void testSimpleElement() {
//      Quicksort sorter = new Quicksort();
//      int[] test = new int[1];
//      test[0] = 5;
//      sorter.sort(test);
//   }
//
   private static int[] makeSpecialArray() {
      return new int[]{5, 5, 6, 6, 4, 4, 5, 5, 4, 4, 6, 6, 5, 5};
   }

   private static boolean validate(int[] numbers) {
      for (int i = 0; i < numbers.length - 1; i++) {
         if (numbers[i] > numbers[i + 1]) {
            return false;
         }
      }
      return true;
   }

   private static void printResult(int[] numbers) {
      for (int i = 0; i < numbers.length; i++) {
         System.out.print(numbers[i]);
      }
      System.out.println();
   }


   public static void main(String[] args) {
      int[] array = makeRandomArray();
      printResult(array);
      quickSort(array);
      printResult(array);
      boolean validated = validate(array);
      System.out.println(validated);

      int[] array2 = makeSpecialArray();
      printResult(array2);
      quickSort(array2);
      printResult(array2);
      boolean validated2 = validate(array2);
      System.out.println(validated2);


   }
// -----------------------------------------------------------------
}

