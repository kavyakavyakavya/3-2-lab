____________________________________________________________________________________________________________________________________________________________________
aim : write a program on MinMaxDivideConquer
____________________________________________________________________________________________________________________________________________________________________
import java.util.Scanner;
public class MinMaxDivideConquer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Finding minimum and maximum using divide and conquer approach
        Pair result = findMinMax(arr, 0, n - 1);

        System.out.println("Minimum element: " + result.min);
        System.out.println("Maximum element: " + result.max);

        scanner.close();
    }

    public static Pair findMinMax(int[] arr, int low, int high) {
        if (low == high) {
            return new Pair(arr[low], arr[low]);
        } else if (high - low == 1) {
            return arr[low] < arr[high] ? new Pair(arr[low], arr[high]) : new Pair(arr[high], arr[low]);
        } else {
            int mid = low + (high - low) / 2;

            Pair leftPair = findMinMax(arr, low, mid);
            Pair rightPair = findMinMax(arr, mid + 1, high);

            int min = Math.min(leftPair.min, rightPair.min);
            int max = Math.max(leftPair.max, rightPair.max);

            return new Pair(min, max);
        }
    }

    static class Pair {
        int min;
        int max;

        Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
