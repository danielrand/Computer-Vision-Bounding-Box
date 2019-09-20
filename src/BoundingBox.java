import java.io.*;
import java.util.Scanner;

public class BoundingBox {

    int numRowsImg, numColsImg, minValImg, maxValImg, numRowsProp, numColsProp,
        minValProp, maxValProp, numCC, label, numPixels, minRow, minCol, maxRow, maxCol, thrSize;
    int [] [] labelledAry;
    Scanner image, CCProperties;
    PrintWriter outFile1, outFile2;

    public BoundingBox (Scanner inFile1, Scanner inFile2, PrintWriter out1, PrintWriter out2) {
        image = inFile1;
        CCProperties = inFile2;
        outFile1 = out1;
        outFile2 = out2;
        numRowsImg = image.nextInt();
        numColsImg = image.nextInt();
        minValImg = image.nextInt();
        maxValImg = image.nextInt();
        numRowsProp = CCProperties.nextInt();
        numColsProp = CCProperties.nextInt();
        minValProp = CCProperties.nextInt();
        maxValProp = CCProperties.nextInt();
        if (numRowsImg != numRowsProp || numColsImg != numColsProp
            || minValImg != minValProp || maxValImg != maxValProp) {
            System.out.println("Error Image headers are not the same. Terminating Program...");
            System.exit(1);
        }
        labelledAry = new int [numRowsImg][numColsImg];
        outFile1.println(numRowsImg + " " + numColsImg + " " + minValImg + " " + maxValImg);
        outFile2.println(numRowsProp + " " + numColsProp + " " + minValProp + " " + maxValProp);
        loadImage();
    }

    private void loadImage () {
        for (int i = 0; i < numRowsImg; i++)
            for (int j = 0; j < numColsImg; j++)
                labelledAry [i][j] = image.nextInt();
    }

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("ERROR: Illegal arguments");
            System.exit(1);
        }
        try {
            Scanner inFile1 = new Scanner(new FileReader(args[0]));
            Scanner inFile2 = new Scanner(new FileReader(args[1]));
            PrintWriter outFile1 = new PrintWriter(new BufferedWriter(new FileWriter(args[3])), true);
            PrintWriter outFile2 = new PrintWriter(new BufferedWriter(new FileWriter(args[4])), true);
            BoundingBox BB = new BoundingBox(inFile1, inFile2, outFile1, outFile2);
            BB.thrSize = Integer.parseInt(args[2]);
            inFile1.close();
            inFile2.close();
            outFile1.close();
            outFile2.close();
        } catch (FileNotFoundException e) {
            System.out.println("One or more input files not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

