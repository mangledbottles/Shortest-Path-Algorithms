import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

@RunWith(JUnit4.class)
public class CompetitionTests
{

//    @Test
//    public void testGen()
//    {
//        CompetitionDijkstra dijkstra = new CompetitionDijkstra("./files/input-D.txt", 1, 2, 3);
//        assertEquals("", dijkstra.toString());
//    }

//    @Test
//    public void testDijkstra() {
//        int minTime = new CompetitionDijkstra("./files/input-D.txt", 75, 56, 87).timeRequiredforCompetition();
//        assertEquals( minTime, 34);
//
//        minTime = new CompetitionDijkstra("./files/1000EWD.txt", 75, 56, 87).timeRequiredforCompetition();
//        TestCase.assertEquals("1000 input returns 25", minTime, 25);
//
//        minTime = new CompetitionDijkstra("./files/tinyEWD.txt", 75, 56, 87).timeRequiredforCompetition();
//        assertEquals("Tiny input returns 34", minTime, 34);
//
//        minTime = new CompetitionDijkstra("./files/input-K.txt", 75, 56, 87).timeRequiredforCompetition();
//        assertEquals("", -1, -1);
//
//        minTime = new CompetitionDijkstra("thisFileDoesNotExist", 56, 78, 57).timeRequiredforCompetition();
//        assertEquals("File does not exist!", minTime, -1);
//
//        minTime = new CompetitionDijkstra(null, 56, 78, 57).timeRequiredforCompetition();
//        assertEquals("Null name returns -1", minTime, -1);
//    }

    @Test
    public void testDijkstraWebCat() {
        int minTime = new CompetitionDijkstra("./input-D.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals(minTime, 34);

        minTime = new CompetitionDijkstra("./1000EWD.txt", 75, 56, 87).timeRequiredforCompetition();
        TestCase.assertEquals("1000 input returns 25", minTime, 25);

        minTime = new CompetitionDijkstra("./tinyEWD.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals("Tiny input returns 34", minTime, 34);

        minTime = new CompetitionDijkstra("./input-K.txt", 51, 70, 84).timeRequiredforCompetition();
        assertEquals("", minTime, 314);

        minTime = new CompetitionDijkstra("thisFileDoesNotExist", 56, 78, 57).timeRequiredforCompetition();
        assertEquals("File does not exist!", minTime, -1);

        minTime = new CompetitionDijkstra(null, 56, 78, 57).timeRequiredforCompetition();
        assertEquals("Null name returns -1", minTime, -1);
    }

}