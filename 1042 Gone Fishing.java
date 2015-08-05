import java.io.BufferedInputStream;   
import java.util.Arrays;   
import java.util.Scanner;   
public class Main {   
    public static void main(String[] args) {   
        Scanner scan = new Scanner(new BufferedInputStream(System.in));   
        while (scan.hasNext()) {   
            int n = scan.nextInt();   
            if (n == 0) {   
                break;   
            }   
            int h = scan.nextInt();   
            h = h * 12; 
            int[] fish = new int[n];   
            int[] decrease = new int[n];   
            int[] interval = new int[n];   
            for (int i = 0; i < n; i++) {   
                fish[i] = scan.nextInt();   
            }   
            for (int i = 0; i < n; i++) {   
                decrease[i] = scan.nextInt();   
            }   
            interval[0] = 0; 
            for (int i = 1; i < n; i++) {   
                interval[i] = scan.nextInt() + interval[i - 1];   
            }   
  
            int[] maxResult = new int[n];   
            int max = maxFish(h, Arrays.copyOf(fish, 1), decrease, maxResult);   
            for (int i = 1; i < n; i++) {   
                if (h <= interval[i]) {   
                    break; 
                }   
                int[] result = new int[n];   
                int m = maxFish(h - interval[i], Arrays.copyOf(fish, i + 1), decrease, result);   
                if (m > max) {   
                    max = m;   
                    maxResult = Arrays.copyOf(result, result.length);   
                }   
            }   
            for (int i = 0; i < maxResult.length; i++) {   
                if (i > 0) {   
                    System.out.print(", ");   
                }   
                System.out.print(maxResult[i] * 5);   
            }   
            System.out.println("");   
            System.out.println("Number of fish expected: " + max);   
            System.out.println("");   
        }   
    }   
  
    public static int maxFish(int time, int[] fish, int[] decrease, int[] result) {   
        int maxNumber = 0;   
        while (time > 0) {   
            int max = fish[0];   
            int maxIndex = 0;   
            for (int i = 1; i < fish.length; i++) {   
                if (fish[i] > max) {   
                    max = fish[i];   
                    maxIndex = i;   
                }   
            }   
            fish[maxIndex] -= decrease[maxIndex]; 
            if (fish[maxIndex] < 0) {   
                fish[maxIndex] = 0; 
            }   
            result[maxIndex]++;   
            maxNumber += max;   
            time--;   
        }   
        return maxNumber;   
    }   
}