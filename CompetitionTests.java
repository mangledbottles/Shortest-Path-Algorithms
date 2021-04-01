import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

@RunWith(JUnit4.class)
public class CompetitionTests
{
    @Test
    public void testConstructor()
    {
//        new CompetitionDijkstra();
    }

    @Test
    public void testGen()
    {
        CompetitionDijkstra dijkstra = new CompetitionDijkstra("./files/input-D.txt", 1, 2, 3);
        assertEquals("", dijkstra.toString());
    }

}