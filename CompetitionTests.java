import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CompetitionTests
{

    private int shortestTime;
    private final String FILES_DIRECTORY;

    public CompetitionTests() {
        // Target WebCat, if running on WebCat, change file directory
        // Example: /tmp/tomcat7-tomcat7-tmp/SCSS/19336054.63932

        if(System.getProperty("user.dir").contains("SCSS")) {
            FILES_DIRECTORY = "./";
        } else {
            FILES_DIRECTORY = "./files/";
        }
    }

    @Test
    public void testDijkstra() {
        System.out.println(System.getProperty("user.dir"));

        shortestTime = new CompetitionDijkstra(FILES_DIRECTORY + "input-D.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals(shortestTime, 34);

        shortestTime = new CompetitionDijkstra(FILES_DIRECTORY + "1000EWD.txt", 75, 56, 87).timeRequiredforCompetition();
        TestCase.assertEquals(shortestTime, 25);

        shortestTime = new CompetitionDijkstra(FILES_DIRECTORY + "input-J.txt", 60, 75, 61).timeRequiredforCompetition();
        assertEquals(-1, -1);

        shortestTime = new CompetitionDijkstra(FILES_DIRECTORY + "input-J.txt", 98, 70, 84).timeRequiredforCompetition();
        assertEquals(-1, -1);

        shortestTime = new CompetitionDijkstra(FILES_DIRECTORY + "tinyEWD.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals(shortestTime, -1);

        shortestTime = new CompetitionDijkstra(FILES_DIRECTORY + "input-K.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals(-1, -1);

        shortestTime = new CompetitionDijkstra(FILES_DIRECTORY + "thisFileDoesNotExist", 56, 78, 57).timeRequiredforCompetition();
        assertEquals("File does not exist", shortestTime, -1);

        shortestTime = new CompetitionDijkstra(FILES_DIRECTORY + null, 56, 78, 57).timeRequiredforCompetition();
        assertEquals("Null name", shortestTime, -1);
    }

    @Test
    public void testFloydWarshall() {
        shortestTime = new CompetitionFloydWarshall(FILES_DIRECTORY + "1000EWD.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals(shortestTime, 25);

        shortestTime = new CompetitionFloydWarshall(FILES_DIRECTORY + "input-D.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals( shortestTime, 34);

        shortestTime = new CompetitionFloydWarshall(FILES_DIRECTORY + "1000EWD.txt", 75, 56, 87).timeRequiredforCompetition();
        TestCase.assertEquals(shortestTime, 25);

        shortestTime = new CompetitionFloydWarshall(FILES_DIRECTORY + "input-J.txt", 60, 75, 61).timeRequiredforCompetition();
        assertEquals(-1, -1);

        shortestTime = new CompetitionFloydWarshall(FILES_DIRECTORY + "input-J.txt", 98, 70, 84).timeRequiredforCompetition();
        assertEquals(-1, -1);

        shortestTime = new CompetitionFloydWarshall(FILES_DIRECTORY + "tinyEWD.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals(shortestTime, 34);

        shortestTime = new CompetitionFloydWarshall(FILES_DIRECTORY + "input-K.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals(-1, -1);

        shortestTime = new CompetitionFloydWarshall(FILES_DIRECTORY + "thisFileDoesNotExist", 56, 78, 57).timeRequiredforCompetition();
        assertEquals("File does not exist", shortestTime, -1);

        shortestTime = new CompetitionFloydWarshall(FILES_DIRECTORY + null, 56, 78, 57).timeRequiredforCompetition();
        assertEquals("Null file", shortestTime, -1);

    }


}