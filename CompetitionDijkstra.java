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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.*;

public class CompetitionDijkstra {

    int speedA, speedB, speedC;
    Streets streets;

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

    }

    /**
     *  Parse the streets from a given fileName
     *  Enter each street into the Street class and store in a global ArrayList
     * @param fileName location of file to parse
     */
    void parseStreets(String fileName) {
        try {
            File myObj = new File(fileName);
            System.out.println("Starting parsing streets \n");
            Scanner myReader = new Scanner(myObj);
            int intersectionsQuantity = Integer.parseInt(myReader.nextLine());
            int streetQuantity = Integer.parseInt(myReader.nextLine()); // skip first two lines
            streets = new Streets(intersectionsQuantity, streetQuantity);

            while(myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                String[] row = nextLine.trim().split("\\s+");

//                System.out.println(Arrays.toString(row));
                int intersectionA = Integer.parseInt(row[0]);
                int intersectionB = Integer.parseInt(row[1]);
                double streetLength = Double.parseDouble(row[2]);

                streets.insertStreet(intersectionA, intersectionB, streetLength);
            }
            System.out.println("Finished parsing streets \n");
            myReader.close();
        } catch (FileNotFoundException error) {
//            e.printStackTrace();
            System.out.println("File '" + fileName + "' not found. " + error.getMessage());
        } catch(Exception error) {
            System.out.println("Invalid data set provided. " + error.getMessage());
        }
    }

    /**
     * Get slowest speed
     * Creates an int array, increases space complexity and the time complexity is good, although the very small data
     * set, O(N log(N)) where N=3
     * @return the slowest speed
     */
    private int getSlowestSpeed() {
        int[] speeds = new int[]{ this.speedA, this.speedB, this.speedC };
        Arrays.sort(speeds);
        return speeds[0];
     }

    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */

    /** WORK IN PROGRESS
     * TODO timeRequiredforCompetition()
     * TODO CompetitionFloydWarshall
     * TODO CompetitionTests
     */

    private boolean alternateContains(PriorityQueue<Path> paths, int w) {
        for (Path path : paths)
            if (path.v == w) return true;
        return false;
    }


    public int timeRequiredforCompetition() {
        if(this.streets == null) return -1;
        if ((this.speedA > 100 || this.speedA < 50) || (this.speedB > 100 || this.speedB < 50)
                || (this.speedC > 100 || this.speedC < 50)) return -1;

//        System.out.println("Finished getting slowest speed " + slowestSpeed  +" \n");
        double[][] graph = new double[Streets.getIntersectionsQuantity()][Streets.getIntersectionsQuantity()];

        for(int i=0; i<streets.getIntersectionsQuantity(); i++) {
//            Arrays.fill(graph[i], Double.POSITIVE_INFINITY);
            graph[i] = quickestAtVertex(i);
        }

        int slowestSpeed = getSlowestSpeed();
        if (getLongestDistance(graph) == Double.POSITIVE_INFINITY) return -1;
        return (int) Math.ceil((getLongestDistance(graph) * 1000) / slowestSpeed);
    }

    private double[] quickestAtVertex(int index) {
        double[] distTo = new double[Streets.getIntersectionsQuantity()];

        // Fill all destinations as unreachable initially (∞)
        for (int j = 0; j < Streets.getIntersectionsQuantity(); j++)
            distTo[j] = Double.POSITIVE_INFINITY;

        distTo[index] = 0.0;

        Comparator<Path> comparator = new Comparer();
        PriorityQueue<Path> paths = new PriorityQueue<Path>(Streets.getIntersectionsQuantity(), comparator);

        paths.add(new Path(index, distTo[index]));
        while (!paths.isEmpty()) {
            Path path = paths.poll();
            for(Street street : streets.adj[path.v]) {
                if(distTo[street.intersectionB] > distTo[street.intersectionA] + street.streetLength) {
                    distTo[street.intersectionB] = distTo[street.intersectionA] + street.streetLength;

                    if(!alternateContains(paths, street.intersectionB)) {
                        paths.add(new Path(street.intersectionB, distTo[street.intersectionB]));
                    } else {
                        paths = replaceSpecial(paths, street.intersectionB, distTo[street.intersectionB]);
                    }
                }
            }
        }
        return distTo;
    }

    private double getLongestDistance(double[][] graph) {
        double longest = 0.00;
        for (double[] streets : graph) {
            for (double street : streets) {
                if (longest < street) longest = street;
            }
        }
        return longest;
    }

    private PriorityQueue<Path> replaceSpecial(PriorityQueue<Path> paths, int w, double newWeight) {
        for (Path path : paths) {
            if (path.v == w) {
                paths.remove(path);
                path.weight = newWeight;
                paths.add(path);
                break;
            }
        }
        return paths;
    }
}

class Path {
    int v;
    double weight;

    Path(int v, double weight){
        this.v = v;
        this.weight = weight;
    }

    public int hashCode(){
        return v;
    }
}

class Comparer implements Comparator<Path>{
    public int compare(Path path, Path pathAlt) {
        return Double.compare(path.weight, pathAlt.weight);
    }
}