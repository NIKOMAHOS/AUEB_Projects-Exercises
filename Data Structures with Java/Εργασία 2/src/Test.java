import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.text.DecimalFormat;
public class Test {
    public static void main(String[] args) {
        // Create a Random object
        Random random = new Random();
        ArrayList<String> fileNames = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        
        int[] numIntegers = {100, 500, 1000};
        
        String currentDirectory = System.getProperty("user.dir");
        String nameOfFolder = "\\data";
        String path = currentDirectory + nameOfFolder + "\\";
        
        // Generate 10 txt files for each number of folders (100, 500, 1000)
        for (int numInts : numIntegers) {
            for (int fileNum = 0; fileNum < 10; fileNum++) {
                String filename = path + "N-"+ numInts + "_" + fileNum + ".txt";
                try (FileWriter fw = new FileWriter(filename)) {
                    for (int i = 0; i < numInts; i++) {
                        // Generate a random integer between 0 and 1000000
                        int randomInt = random.nextInt(1000000);
                        fw.write(randomInt + "\n");
                    }
                    fileNames.add(filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        System.out.println();
        
        System.out.println("Greedy Algorithm:");
        int greedyTotal = 0;
        int greedyCounter = 0;
        float sizeOfallFolders1 = 0; // In TB
        for (String fileName : fileNames) {
            // Read the file and get the array of folder sizes
            int[] sizes1 = Greedy.read(fileName); 
            // Calculate the total size of all folders in the file
            for (int folderSize : sizes1) {
                sizeOfallFolders1 += folderSize / 1000000.0F; // MB to TB
            }
            System.out.println();
            System.out.println("Total size of all folders: " + df.format(sizeOfallFolders1) + " TB");
            System.out.println();
            // Call the greedy algorithm on the array of folder sizes
            int disks = Greedy.greedy(sizes1); 
            greedyTotal += disks;
            greedyCounter++;
            if (greedyCounter == 10) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Average number of disks used for N = 100: " + df.format((double) greedyTotal / 10));
                System.out.println("---------------------------------------------------------");
                greedyTotal = 0;
            } else if (greedyCounter == 20) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Average number of disks used for N = 500: " + df.format((double) greedyTotal / 10));
                System.out.println("---------------------------------------------------------");
                greedyTotal = 0;
            } else if (greedyCounter == 30) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Average number of disks used for N = 1000: " + df.format((double) greedyTotal / 10));
                System.out.println("---------------------------------------------------------");
                greedyTotal = 0;
            }
                
        }
        
        System.out.println("---------------------------------------------------------");
        
        System.out.println("Greedy-decreasing Algorithm:");
        int greedyDecreasingTotal = 0;
        int greedyDecreasingCounter = 0;
        float sizeOfallFolders2 = 0; // In TB
        for (String fileName : fileNames) {
            // Read the file and get the array of folder sizes 
            int[] sizes2 = Greedy.read(fileName); 
            // Sort the array of folder sizes in decreasing order
            Sort.quickSortDescending(sizes2, 0, sizes2.length - 1);
            // Calculate the total size of all folders in the file
            for (int folderSize : sizes2) {
                sizeOfallFolders2 += folderSize / 1000000.0F; // MB to TB
            }
            System.out.println();
            System.out.println("Total size of all folders: " + df.format(sizeOfallFolders2) + " TB");
            System.out.println();
            // Call the greedy algorithm on the sorted array of folder sizes
            int disks = Greedy.greedy(sizes2); 
            greedyDecreasingTotal += disks;
            greedyDecreasingCounter++;
            if (greedyDecreasingCounter == 10) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Average number of disks used for N = 100: " + df.format((double) greedyDecreasingTotal / 10));
                System.out.println("---------------------------------------------------------");
                greedyDecreasingTotal = 0;
            } else if (greedyDecreasingCounter == 20) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Average number of disks used for N = 500: " + df.format((double) greedyDecreasingTotal / 10));
                System.out.println("---------------------------------------------------------");
                greedyDecreasingTotal = 0;
            } else if (greedyDecreasingCounter == 30) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Average number of disks used for N = 1000: " + df.format((double) greedyDecreasingTotal / 10));
                System.out.println("---------------------------------------------------------");
                greedyDecreasingTotal = 0;
            }
                
        }
        
    }

}
