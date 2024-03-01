---------------------- -------------------------------------------------------------------------------------------------------------------------------------------------
  aim: write a program BinarySearch program
  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {

    public static int bsRecursive(int[] arr, int left, int right, int target) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target)
                return mid;

            if (arr[mid] > target)
                return bsRecursive(arr, left, mid - 1, target);

            return bsRecursive(arr, mid + 1, right, target);
        }

        return -1;
    }


    public static int bsIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target)
                return mid;

            if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        boolean sorted = isSorted(arr);

        if (!sorted) {
            Arrays.sort(arr); 
            System.out.println("Array was not sorted. Sorted the array before proceeding.");
        }
        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.print("Enter the element to search: ");
        int target = scanner.nextInt();

        System.out.println("Choose the search method:");
        System.out.println("1. Recursive Binary Search");
        System.out.println("2. Iterative Binary Search");

        int choice = scanner.nextInt();
        int result = -1;

        switch (choice) {
            case 1:
                result = bsRecursive(arr, 0, n - 1, target);
                break;
            case 2:
                result = bsIterative(arr, target);
                break;
            default:
                System.out.println("Invalid choice");
        }

        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found");
        }

        scanner.close();
    }

    // Function to check if array is sorted
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }
}

