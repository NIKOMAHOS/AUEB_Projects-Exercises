public class Sort { // Quicksort Descending algorithm

public static void quickSortDescending(int[] array, int low, int high) {
    if (array == null || array.length == 0) {
        return;
    }

    if (low >= high) {
        return;
    }

    // Choose a pivot element
    int middle = low + (high - low) / 2;
    int pivot = array[middle];

    // Divide the array into two halves around the pivot element
    int i = low, j = high;
    while (i <= j) {
        // Find the first element from the left that is less than the pivot (Meaning that is in the wrong half of the array)
        while (array[i] > pivot) {
            i++; // If the element at index i is greater than the pivot move the index to the next element
        }
        // Find the first element from the right that is greater than the pivot (Meaning that is in the wrong half of the array)
        while (array[j] < pivot) {
            j--; // If the element at index j is less than the pivot move the index to the previous element
        }

        // Swap the elements if they are in the wrong half of the array
        if (i <= j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++; // Move the index to the next element
            j--; // Move the index to the next element
            // Start closing in towards the middle of the array (where the pivot is)
        }
    }

    // Recursively sort the two halves of the array
    if (low < j) {
        quickSortDescending(array, low, j); // Sort the left half of the array
    }
    if (high > i) {
        quickSortDescending(array, i, high); // Sort the right half of the array
    }
}

}