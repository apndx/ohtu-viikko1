/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apndx
 */
public class StatisticTest {

    Reader readerStub = new Reader() {

        public java.util.List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    public StatisticTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isPlayerFoundCorrectly() {

        assertEquals("EDM", stats.search("Semenko").getTeam());
        assertEquals("Semenko", stats.search("Semenko").getName());
        assertEquals(4, stats.search("Semenko").getGoals());
        assertEquals(12, stats.search("Semenko").getAssists());
        assertEquals(null, stats.search("Semeno"));

    }

    @Test
    public void doTopScoresWorkCorrectly() {
        List<Player> bestOnes = stats.topScorers(3);

        assertEquals(4, bestOnes.size());
        assertEquals("Gretzky", bestOnes.get(0).getName());
        assertEquals("PIT", bestOnes.get(1).getTeam());
        assertEquals(42, bestOnes.get(2).getGoals());
        assertEquals(53, bestOnes.get(3).getAssists());

    }

    @Test
    public void doesTheTeamSearchWork() {

        List<Player> team = stats.team("EDM");

        assertEquals(3, team.size());
        assertEquals("EDM", team.get(0).getTeam());
        assertEquals("EDM", team.get(1).getTeam());
        assertEquals("EDM", team.get(2).getTeam());

    }

}
