import java.io.*;
import java.text.DecimalFormat;
public class Greedy {
    public static void main(String[] args) {
    
        String pathname = args[0];
        int allFolders[] = read(pathname); // Reads the file and returns an array of all folder sizes
        DecimalFormat df = new DecimalFormat("#.##");
        float sizeOfallFolders = 0; // In TB
        
        for (int folderSize : allFolders) {
            sizeOfallFolders += folderSize / 1000000.0F; // MB to TB
        }
        System.out.println();
        System.out.println("Total size of all folders: " + df.format(sizeOfallFolders) + " TB");
        int disks = greedy(allFolders); // Call the greedy algorithm on the array of folder sizes
        
    }
    
    public static int[] read(String pathname) { // Reads the file and returns an array of folder sizes
        File file = new File(pathname);
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(file));
            String line;
            int lines = 0;
            while ((line = br.readLine()) != null) {
                lines++;
            }
            br.close();
            int []allFolders = new int[lines];
            int i = 0;
            br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                int folder = Integer.valueOf(line.trim());
                if (folder >= 0 && folder <= 1000000) {
                    allFolders[i] = folder;
                }else{
                    System.out.println("Folder size is not valid");
                    System.exit(0);
                }
                i++;
            }
        
        return allFolders;
        
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int greedy(int[] sizes) {
    
        MaxPQ<Disk> pq = new MaxPQ<>();
    
        // For each folder in the file:
        for (int i = 0; i < sizes.length; i++) {
            // If the priority queue is empty or the folder doesn't fit in the disk with the most free space:
            if (pq.isEmpty() || sizes[i] > pq.peek().getFreeSpace()) {
                // Create a new disk and store the folder to the new disk
                Disk newDisk = new Disk();
                newDisk.addFolder(sizes[i]);
                // Insert the new disk to the priority queue
                pq.insert(newDisk);
            } else {
                // Get the disk with the most free space
                Disk disk = pq.getMax();
                // Store the folder to that disk
                disk.addFolder(sizes[i]);
                // Put the updated disk back to the priority queue
                pq.insert(disk);
            }
        }
    
        int size = pq.size();
    
        // Print the details of each disk for the first 100 disks in decreasing order of free space
        System.out.println();
        System.out.println("Details of the " + size + " disks used (max number shown -> 100, in decreasing order of free space):\n");
        for (int i = 0; i <= 100; i++) {
            if (!pq.isEmpty()) {
                Disk d = pq.getMax();
                System.out.println(d.toString());
            } else {
                break;
            }
        }
        System.out.println();
        return size;
    }
}

