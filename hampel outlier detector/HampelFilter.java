package com.jendo.service.signal_processing.dsp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class HampelFilter {

    // getSliceOfArray , quickSort, median functions are using in the filter function to help the main function which is filter function

    public static List<Double> getSliceOfArray(double[] arr,
                                               int start, int end)
    {

        // Get the slice of the Array
        double[] slice = new double[end - start];

        // Copy elements of arr to slice
        for (int i = 0; i < slice.length; i++) {
            slice[i] = (int) arr[start + i];
        }
        List<Double> sliced = new ArrayList<Double>();
        for(Double text:slice) {
            sliced.add(text);
        }

        // return the slice
        return sliced;
    }

    public static void quickSort(double[] array, int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        double pivot = array[low + (high - low) / 2];

        // Divide into two lists
        double exchange;
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (array[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (array[j] > pivot) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange = array[i];
                array[i] = array[j];
                array[j] = exchange;
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quickSort(array, low, j);
        if (i < high)
            quickSort(array, i, high);
    }

    public static double median(List<Double> data) {
        double[] array = data.stream().mapToDouble(d -> d).toArray();
        int len = array.length;
        int mid = len / 2;
        quickSort(array, 0, len - 1);
        // Median is the midpoint of the sorted list
        if (len % 2 == 0) {
            return .5 * (array[mid - 1] + array[mid]);
        } else {
            return array[mid];
        }

    }


    public static double[] filter(double[] signal ,int half_window , int threshold){

        int n = signal.length;
        double[] signal_copy = signal;
        float L = (float) 1.4826;

        for (int i = half_window; i < n - half_window; i++) {
            List<Double> sliced_array = getSliceOfArray(signal ,i - half_window , i + half_window);
            float med = (float) median(sliced_array);

            List<Double> sliced_array_med = sliced_array.stream().map(m -> Math.abs(m  - med)).collect(Collectors.toList());
            float MAD = (float) (L * median(sliced_array_med));

            if (Math.abs(signal[i] - med) / MAD > threshold) {
                signal_copy[i] = med;
            }
        }
        return signal_copy;
    }
}
