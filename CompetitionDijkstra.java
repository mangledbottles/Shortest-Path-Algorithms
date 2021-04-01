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
 * This class implements the competition using Dijkstra's algorithm
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CompetitionDijkstra {

//    ArrayList<Street> streets = new ArrayList<>();
    int speedA, speedB, speedC;
//    int intersectionsQuantity, streetQuantity;

    /**
     * @param fileName: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */

    CompetitionDijkstra (String fileName, int sA, int sB, int sC){
        // Parse lines 3 onwards into class Street
        parseStreets(fileName);
        // Configure contestant speeds
        this.speedA = sA;
        this.speedB = sB;
        this.speedC = sC;

//        System.out.println(getNumberIntersections(fileName));
//        Algorithms a = new Algorithms();
//        a.Dijkstra(new int[]{1, 3, 5, 2 }, sA);
    }
//                if(nextLine.charAt(0) == ' ') nextLine.substring(1);

    /**
     *  Parse the streets from a given fileName
     *  Enter each street into the Street class and store in a global ArrayList
     * @param fileName
     */
    void parseStreets(String fileName) {
        File myObj = new File(fileName);
        try {
            Scanner myReader = new Scanner(myObj);

            int intersectionsQuantity = Integer.parseInt(myReader.nextLine());
            int streetQuantity = Integer.parseInt(myReader.nextLine()); // skip first two lines
//            myReader.nextLine();
            Streets streets = new Streets(intersectionsQuantity, streetQuantity);
            while(myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                String[] row = nextLine.trim().split("\\s+");

                System.out.println(Arrays.toString(row));
                int intersectionA = Integer.parseInt(row[0]);
                int intersectionB = Integer.parseInt(row[1]);
                double streetLength = Double.parseDouble(row[2]);

//                streets.add(new Street(intersectionA, intersectionB, streetLength));
                streets.insertStreet(intersectionA, intersectionB, streetLength);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

class Streets {
    ArrayList<Street> streets = new ArrayList<>();
    int intersectionsQuantity, streetsQuantity;
    Streets(int intersectionsQuantity, int streetsQuantity) {
        this.intersectionsQuantity = intersectionsQuantity;
        this.streetsQuantity = streetsQuantity;
    }

    void insertStreet(int intersectionA, int intersectionB, double streetLength) {
        try {
            isVertexValid(intersectionA);
            isVertexValid(intersectionB);

            Street s = new Street(intersectionA, intersectionB, streetLength);
            streets.add(s);
        } catch(Exception e) {
            System.out.println("Error inserting edge");
        }
    }

    private void isVertexValid(int vertex) {
        if (vertex < 0 || vertex >= intersectionsQuantity) {
            throw new IllegalArgumentException("Vertex " + vertex + "  has an invalid range," +
                    "must be between 0 and " + (intersectionsQuantity - 1));
        }
    }

}

class Street {
    int intersectionA;
    int intersectionB;
    double streetLength;

    Street(int intersectionA, int intersectionB, double streetLength) {
        this.intersectionA = intersectionA;
        this.intersectionB = intersectionB;
        this.streetLength = streetLength;
    }
    double getStreetLength() {
        return this.streetLength;
    }
}