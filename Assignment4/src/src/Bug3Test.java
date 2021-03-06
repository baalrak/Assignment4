package src;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Bug3Test
{
  BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

  Dice d1 = new Dice();
  Dice d2 = new Dice();
  Dice d3 = new Dice();

  Player player = new Player("Fred", 100);
  Game game = new Game(d1, d2, d3);
  List<DiceValue> cdv = game.getDiceValues();

  int totalWins = 0;
  int totalLosses = 0;
  int winCount = 0;
  int loseCount = 0;
  
  @Before
  public void setUp () throws Exception
  {
  }



  @After
  public void tearDown () throws Exception
  {
  }



  @Test
  public void testWinRateBug ()
  {
    for (int i = 0; i < 100; i++)
    {
      String name = "Fred";
      int balance = 100;
      int limit = 0;
      player = new Player(name, balance);
      player.setLimit(limit);
      int bet = 5;

      System.out.println(String.format("Start Game %d: ", i));
      System.out.println(String.format("%s starts with balance %d, limit %d", 
          player.getName(), player.getBalance(), player.getLimit()));

      int turn = 0;
      while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
      {
        turn++;                    
        DiceValue pick = DiceValue.getRandom();
        System.out.println("Starting Balance: " + player.getBalance ());
        System.out.printf("Turn %d: %s bet %d on %s\n",
            turn, player.getName(), bet, pick); 

        int winnings = game.playRound(player, pick, bet);
        cdv = game.getDiceValues();

        System.out.printf("Rolled %s, %s, %s\n",
            cdv.get(0), cdv.get(1), cdv.get(2));

        if (winnings > 0) {
          System.out.printf("%s won %d, balance now %d\n\n",
              player.getName(), winnings, player.getBalance());
          winCount++; 
        }
        else {
          System.out.printf("%s lost, balance now %d\n\n",
              player.getName(), player.getBalance());
          loseCount++;
        }

      } //while

      System.out.print(String.format("%d turns later.\nEnd Game %d: ", turn, i));
      System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));

    } //for

    System.out.println(String.format("Win count = %d, Lose Count = %d, %.2f", winCount, loseCount, (float) winCount/(winCount+loseCount)));
    totalWins += winCount;
    totalLosses += loseCount;
    System.out.println(String.format("Overall win rate = %.1f%%", (float)(totalWins * 100) / (totalWins + totalLosses)));
  }

  
  @Test
  public void testHypothesis1 ()
  {
    for (int i = 0; i < 100; i++)
    {
      String name = "Fred";
      int balance = 100;
      int limit = 0;
      player = new Player(name, balance);
      player.setLimit(limit);
      int bet = 5;
      
      System.out.println(String.format("Start Game %d: ", i));
      System.out.println(String.format("%s starts with balance %d, limit %d", 
          player.getName(), player.getBalance(), player.getLimit()));

      int turn = 0;
      while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
      {
        turn++;     
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();
        Game game = new Game(d1, d2, d3);
        DiceValue pick = DiceValue.getRandom();
        System.out.println("Starting Balance: " + player.getBalance ());
        System.out.printf("Turn %d: %s bet %d on %s\n",
            turn, player.getName(), bet, pick); 

        int winnings = game.playRound(player, pick, bet);
        cdv = game.getDiceValues();

        System.out.printf("Rolled %s, %s, %s\n",
            cdv.get(0), cdv.get(1), cdv.get(2));

        if (winnings > 0) {
          System.out.printf("%s won %d, balance now %d\n\n",
              player.getName(), winnings, player.getBalance());
          winCount++; 
        }
        else {
          System.out.printf("%s lost, balance now %d\n\n",
              player.getName(), player.getBalance());
          loseCount++;
        }

      } //while

      System.out.print(String.format("%d turns later.\nEnd Game %d: ", turn, i));
      System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));

    } //for

    System.out.println(String.format("Win count = %d, Lose Count = %d, %.2f", winCount, loseCount, (float) winCount/(winCount+loseCount)));
    totalWins += winCount;
    totalLosses += loseCount;
    System.out.println(String.format("Overall win rate = %.1f%%", (float)(totalWins * 100) / (totalWins + totalLosses)));
  }


  @Test
  public void testHypothesis2 ()
  {
    int heart = 0;
    int club = 0;
    int anchor = 0;
    int crown = 0;
    int diamond = 0;
    int spade = 0;
    DiceValue dv = null;
    for (int i = 0; i < 1000; i++){
      DiceValue a = dv.getRandom();
      if (a.equals (DiceValue.HEART)){
        heart++;
      }
      if (a.equals (DiceValue.CLUB)){
        club++;
      }
      if (a.equals (DiceValue.ANCHOR)){
        anchor++;
      }
      if (a.equals (DiceValue.CROWN)){
        crown++;
      }
      if (a.equals (DiceValue.DIAMOND)){
        diamond++;
      }
      if (a.equals (DiceValue.SPADE)){
        spade++;
      }
    }
    System.out.println("Number of Hearts: " + heart);
    System.out.println("Number of Clubs: " + club);
    System.out.println("Number of Anchors: " + anchor);
    System.out.println("Number of Crowns: " + crown);
    System.out.println("Number of Diamonds: " + diamond);
    System.out.println("Number of Spades: " + spade);
  }
}


