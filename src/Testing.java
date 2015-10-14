import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Testing {

    private Dice realDie1;
    private Dice realDie2;
    private Dice realDie3;
    private Player realPlayer;
    private Game game;

    private Dice mockDie1 = mock(Dice.class);
    private Dice mockDie2 = mock(Dice.class);
    private Dice mockDie3 = mock(Dice.class);
    private Player mockPlayer = mock(Player.class);





    @After
    public void tearDown(){
        realPlayer = null;
        realDie1 = null;
        realDie2 = null;
        realDie3 = null;
    }

    /* Test for bug 1
     *
     * Test will guarantee that the player wins something in the round
     *
     * Update: Bug occurs as game does not add the bet itself to the winnings
     * Possible Fix: increase the winning multiplier by 1 to payout an extra winning
     */


    @Test
    public void testBug1TwoWins(){
            // ensure player gets 2 Anchors
        when (mockDie1.getValue()).thenReturn(DiceValue.CLUB);
        when (mockDie2.getValue()).thenReturn(DiceValue.ANCHOR);
        when (mockDie3.getValue()).thenReturn(DiceValue.ANCHOR);

        //player to test on
        realPlayer = new Player("Bruce", 100);

        //game to test on
        game = new Game(mockDie1, mockDie2, mockDie3);
        game.playRound(realPlayer,DiceValue.ANCHOR,10);

        System.out.println("players Balance = " + realPlayer.getBalance());
        Assert.assertTrue(realPlayer.getBalance() == 120); //check that the player has won $20;
        //problem player does not win $20, gets $10 instead
    }

    @Test
    public void testBug1OneWin(){

        when (mockDie1.getValue()).thenReturn(DiceValue.CLUB);
        when (mockDie2.getValue()).thenReturn(DiceValue.ANCHOR);
        when (mockDie3.getValue()).thenReturn(DiceValue.CLUB);

        realPlayer = new Player("Bruce", 100);

        game = new Game(mockDie1, mockDie2, mockDie3);
        game.playRound(realPlayer,DiceValue.ANCHOR,10);

        System.out.println("players Balance = " + realPlayer.getBalance());
        Assert.assertTrue(realPlayer.getBalance() == 110); //check that the player has won $20;
    }


    /**
     * Testing for bug #2
     *
     */

    @Test
    public void testBettingLimit(){
        when (mockDie1.getValue()).thenReturn(DiceValue.CLUB);
        when (mockDie2.getValue()).thenReturn(DiceValue.ANCHOR);
        when (mockDie3.getValue()).thenReturn(DiceValue.CLUB);

        game = new Game(mockDie1, mockDie2, mockDie3);

        realPlayer = new Player("Bort", 20);
        realPlayer.setLimit(10);

        game.playRound(realPlayer,DiceValue.CROWN,10);

        Assert.assertTrue(realPlayer.getBalance() == 10);

    }

    /**
     * Testing for bug 3
     *
     */
    @Test
    public void testWinLossRatio(){
        float wins = 0;
        float losses = 0;

        realDie1 = new Dice();
        realDie2 = new Dice();
        realDie3 = new Dice();

        game = new Game(realDie1, realDie2, realDie3);

        when (mockPlayer.getBalance()).thenReturn(100);

        int currentGameWins = 0;

      for (int i = 0; i < 10000; i++) {
            currentGameWins = game.playRound(mockPlayer, DiceValue.ANCHOR, 10);

            if (currentGameWins == 0) {
                losses ++;
            } else {
                wins ++;
            }

      }
        System.out.println("Wins = " + wins + " Losses = " + losses);
        float ratio = wins/(losses + wins);
        System.out.println("Win ratio = " + ratio);
    }

    //checking the fact 0 is acceptable for a bet is OK
    @Test
    public void test0(){
        when (mockDie1.getValue()).thenReturn(DiceValue.CLUB);
        when (mockDie2.getValue()).thenReturn(DiceValue.ANCHOR);
        when (mockDie3.getValue()).thenReturn(DiceValue.ANCHOR);

        //player to test on
        realPlayer = new Player("Bruce", 100);

        //game to test on
        game = new Game(mockDie1, mockDie2, mockDie3);
        game.playRound(realPlayer,DiceValue.ANCHOR,0);

        System.out.println("players Balance = " + realPlayer.getBalance());
        Assert.assertTrue(realPlayer.getBalance() == 100);

        //Confirmed nothing bad happens, works as normal without issue, better left alone...
    }

        //check if unused "balanceExceeds limit function should be used
    @Test
    public void checkBalanceLimitChecking(){
        when (mockDie1.getValue()).thenReturn(DiceValue.CLUB);
        when (mockDie2.getValue()).thenReturn(DiceValue.ANCHOR);
        when (mockDie3.getValue()).thenReturn(DiceValue.CLUB);

        game = new Game(mockDie1, mockDie2, mockDie3);

        realPlayer = new Player("Bort", 20);
        realPlayer.setLimit(10);
        game.playRound(realPlayer,DiceValue.CROWN,40);
        Assert.assertTrue(realPlayer.getBalance() == 20);

    }

        //confirm all string related fnctions are correct
    @Test
    public void verifyStringFunctions(){
        realDie1 = new Dice();
        realPlayer = new Player("aaa",100);
        System.out.println(realPlayer.toString()); //All good
        System.out.println(realDie1.toString()); //all good
    }

}
