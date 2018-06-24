/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.pkg1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yasara
 */
public class CODE1 {

    static boolean[][] myArray;
    static int[] fileSize;
    final static String fileName = "src\\resources\\sample.txt";
    private static final Logger LOGGER = Logger.getLogger( CODE1.class.getName() );
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
            fileSize = getFileSize();
            readFile();
    }

    public static void readFile() {
        Scanner input = null;
        int rows = fileSize[0];
        int columns = fileSize[1];
        int[][] myArray = new int[rows][columns];
        try {
            input = new Scanner(new FileReader(new File(fileName).getAbsoluteFile()));
            while (input.hasNextLine()) {
                for (int i = 0; i < myArray.length; i++) {
                    String[] line = input.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        myArray[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        finally{
            if(myArray.length>0 ){
                String msg = "File read successfull.";
                LOGGER.log(Level.INFO, msg);
                System.out.println(Arrays.deepToString(myArray));
            }
            else{
                String msg = "File is empty";
                LOGGER.log(Level.INFO, msg);
            }
            input.close();
        }
    }

    public static int[] getFileSize() {
         int[] sizeArray = new int[2];
         Scanner input=null;
        try {
            input = new Scanner(new FileReader(new File(fileName).getAbsoluteFile()));
            int columnSize = 0;
            int rowSize = 0;
           
            while (input.hasNextLine()) {
                rowSize++;
                if (rowSize == 1) {
                    String[] line = input.nextLine().trim().split(" ");
                    columnSize = line.length;
                }
                input.nextLine();
            }

            sizeArray[0] = rowSize+1;
            sizeArray[1] = columnSize;
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        finally{
            input.close();
             String msg = "File size is successfully retrieved.\n"+"Row Size : "+sizeArray[0]+"\n"+"Column Size : "+sizeArray[1];
             LOGGER.log(Level.INFO, msg);
            return sizeArray;
        }
    }
}
