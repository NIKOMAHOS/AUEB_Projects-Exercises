import java.io.*;
import java.util.ArrayList;

public class Thiseas {
    public static void main(String[] args) {
        // Read the txt file
        File file = new File(args[0]);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            // Get the maze size 
            line = br.readLine(); // Read First Line
            String[] space_1 = line.split(" "); // Split the string in the center
            int rows = Integer.parseInt(space_1[0]); // First number (left)
            int columns = Integer.parseInt(space_1[1]); // Second number (right)

            // Get the entrance coordinates 
            line = br.readLine(); // Read Second Line
            String[] space_2 = line.split(" "); // Split the string in the center
            int in_1 = Integer.parseInt(space_2[0]); // First number (left)
            int in_2 = Integer.parseInt(space_2[1]); // Second number (right)

            ArrayList<String> boardOfStrings = new ArrayList<String>();
            
            StringStackImpl<String> stack = new StringStackImpl<String>();
            
            while ((line = br.readLine()) != null) {
                boardOfStrings.add((String.join("", line.split(" "))));
            }
            
            String[] array = boardOfStrings.toArray(new String[boardOfStrings.size()]);
            char[][] maze = new char[array.length][array[0].length()]; // [rows][columns]
            
            for (int i = 0; i < array.length; i++) { // iterate rows
                for ( int j = 0; j < array[0].length(); j++) { // iterate columns
                    maze[i][j] = array[i].charAt(j); // load the maze with the data from the txt file
                }
            }
            
            // Check if the dimensions of the maze are the same as stated above
            if ( maze.length != rows || maze[0].length != columns ) {
                System.out.println("Error !!!\nThe dimensions of the maze are not the same as stated above.");
                System.exit(0); // terminate the program
            }
            
            // Check if the coordinates for the entrance of the maze are the same as stated above
            if ( maze[in_1][in_2] != 'E' ) {
                System.out.println("Error !!!\nThe entrance coordinates are not the same as stated above.");
                System.exit(0); // terminate the program
            }
            
            // Symbol Info:
            /* 
            'E' is the entrance, a '0' in the boundaries of the array is an exit, any other '0' is a walkable path, a '1' is a wall,
                a 'T' is a temprorary visited path a 'X' is a visited path that leads to a dead - end
            In_1 is the row and In_2 is the column
            */ 
            
            // check to find if there is an exit in the maze -> check for a "0" in the boundaries of the array
            int counter = 0; // a variable to count the number of exits
            for( int i = 0; i < rows ; i++) {
                for (int j = 0; j < columns; j++){
                    if ((i==0 || j==0 || i == rows - 1 || j == columns - 1) && (maze[i][j] == '0')){ //condition for accessing boundary elements
                        counter++;
                    }   
                }
            }
            if (counter == 0) { // if no exits were found
                System.out.println("Error !!!\nThere is no exit in the maze.");
                System.exit(0); // terminate the program
            }else if (counter == 1) { // if one exit was found
                System.out.println("There is one exit in the maze.");
                System.out.println("Searching for a way out...");
            }else { // more than one exits were found
                System.out.println("There are " + counter + " exits in the maze.");
                System.out.println("Searching for a way out...");
            }
            
            // try to find a way out of the maze
            stack.push(String.valueOf(in_1) + String.valueOf(in_2)); // push the coordinates of the entrance of the maze into the stack
            
            while ( true ) {
                    if ( ( in_1 == 0 || in_2 ==0 || in_1 == rows - 1 || in_2 == columns - 1) && (maze[in_1][in_2] == '0')){ // check if you found an exit
                        System.out.println(); // Blank Line
                        // Print Stack (whole path)
                        System.out.println("The path is the following (from exit to entrance): ");
                        stack.printStack(System.out); // -Print the stack (not required by the assignment)
                        // Print the coordinates of the exit
                        System.out.println("Found a way out at exit: " + in_1 + ", " + in_2); 
                        break;
                    }
                    if ( (in_1 - 1 >= 0) && (maze[in_1 - 1][in_2] == '0') ) { // check the above row  
                        stack.push(String.valueOf(in_1 - 1) + String.valueOf(in_2)); // push the coordinates of the next step
                        if (maze[in_1][in_2] != 'E'){ // if the next step is not the entrance
                            maze[in_1][in_2] = 'T'; // mark the path as temprorary visited
                        }
                        // update the current coordinates
                        in_1 = in_1 - 1; // go one step up
                        continue;
                    }
                    if ( (in_2 + 1 <= maze[0].length - 1) && (maze[in_1][in_2 + 1 ] == '0') ) { // check column at the right
                        stack.push( String.valueOf(in_1) + String.valueOf(in_2 + 1) ); // push the coordinates of the next step into the stack 
                        if (maze[in_1][in_2] != 'E'){ // if the next step is not the entrance
                            maze[in_1][in_2] = 'T'; // mark the path as temprorary visited
                        }
                        // update the current coordinates
                        in_2 = in_2 + 1; // go one step to the right
                        continue;
                    }
                    if ( (in_1 + 1 <= maze.length - 1) && (maze[in_1 + 1][in_2] == '0') ) { // check the below row
                        stack.push(String.valueOf(in_1 + 1) + String.valueOf(in_2)); // push the coordinates of the next step
                        if (maze[in_1][in_2] != 'E'){ // if the next step is not the entrance
                            maze[in_1][in_2] = 'T'; // mark the path as temprorary visited
                        }
                        // update the current coordinates
                        in_1 = in_1 + 1; // go one step down
                        continue;
                    }
                    if ( (in_2 - 1 >= 0) && (maze[in_1][in_2 - 1 ] == '0')) { // check column at the left
                        stack.push( String.valueOf(in_1) + String.valueOf(in_2 - 1) ); // push the coordinates of the next step into the stack 
                        if (maze[in_1][in_2] != 'E'){ // if the next step is not the entrance
                            maze[in_1][in_2] = 'T'; // mark the path as temprorary visited
                        }
                        // update the current coordinates
                        in_2 = in_2 - 1; // go one step to the left
                        continue;
                    }
                    // You are in a dead - end 
                    else {                       
                        if ((maze[in_1][in_2] == '0')) { // if the current position is leads to a dead - end
                            maze[in_1][in_2] = 'X'; // mark the path as visited
                            // then start backtracking
                            stack.pop(); // pop the last walkable path, that is the current position and the last (top) element in the stack
                            String temp[] = (stack.peek()).split(""); // peek the previous walked path, which is the newest element (top) in the stack
                            
                            // read the last movement from the array
                            in_1 = Integer.valueOf(temp[0]);
                            in_2 = Integer.valueOf(temp[1]);
                            
                            if (maze[in_1][in_2] != 'E'){ // if the next step is not the entrance
                                maze[in_1][in_2] = '0';  // delete the temprorary visited symbol
                            }
                            continue;  
                        // There is no way out of the maze
                        } else {
                            System.out.println("There is no way out of the maze.");
                            break;
                        }
                    }       
            }
        } catch (FileNotFoundException e1) {
            System.out.println("File not found"); // if the file is not found
        } catch (IOException e) {
            System.out.println("IO Exception"); // if there is an IO exception
            }
    }
}



























