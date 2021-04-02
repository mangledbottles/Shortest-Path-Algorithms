/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CompetitionFloydWarshall {

    int speedA, speedB, speedC;
    Streets streets;

    /**
     * @param fileName: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String fileName, int sA, int sB, int sC){
        // Parse lines 3 onwards into class Street
        parseStreets(fileName);
        // Configure contestant speeds
        this.speedA = sA;
        this.speedB = sB;
        this.speedC = sC;
    }

    /**
     *  Parse the streets from a given fileName
     *  Enter each street into the Street class and store in a global ArrayList
     * @param fileName location of file to parse
     */
    void parseStreets(String fileName) {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            int intersectionsQuantity = Integer.parseInt(myReader.nextLine());
            int streetQuantity = Integer.parseInt(myReader.nextLine()); // skip first two lines
            streets = new Streets(intersectionsQuantity, streetQuantity);

            while(myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                String[] row = nextLine.trim().split("\\s+");

                int intersectionA = Integer.parseInt(row[0]);
                int intersectionB = Integer.parseInt(row[1]);
                double streetLength = Double.parseDouble(row[2]);

                streets.insertStreet(intersectionA, intersectionB, streetLength);
            }
            System.out.println("Finished parsing streets \n");
            myReader.close();
        } catch (FileNotFoundException error) {
            System.out.println("File '" + fileName + "' not found. " + error.getMessage());
        } catch(Exception error) {
            System.out.println("Invalid data set provided. " + error.getMessage());
        }
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }

}