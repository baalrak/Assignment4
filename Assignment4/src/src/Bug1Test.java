package src;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class Bug1Test
{
  
  private int winnings;
  private String name;
  private int balance;
  private int limit;
  private Player player;
  List<DiceValue> cdv;
  private int bet;
  private Dice d1;
  private Dice d2;
  private Dice d3;
  private Game game;
  

  @Before
  public void setUp () throws Exception
  {
    winnings = 0;
    name = "Fred";
    balance = 100;
    bet = 5;
    limit = 0;
    player = new Player(name, balance);
    d1 = Mockito.mock(Dice.class);
    d2 = Mockito.mock(Dice.class);
    d3 = Mockito.mock(Dice.class);
    game = new Game(d1, d2, d3);
    List<DiceValue> cdv = game.getDiceValues();
  }



  @After
  public void tearDown () throws Exception
  {
    player = null;
    d1 = null;
    d2 = null;
    d3 = null;
    game = null;
    cdv = null;
  }



  @Test
  //Test to see that the tests payout correctly for single matches
  public void testSingleMatchValues ()
  {
    for (int i = 0; i < 3; i++)
    {
      System.out.println("Test 1 - Single Match");
      System.out.println(String.format("Start Game %d: ", i));

      int turn = 0;
      turn++;                    
      DiceValue pick = DiceValue.CROWN;
      System.out.printf("Turn %d: %s bet %d on %s\n",
                         turn, player.getName(), bet, pick); 
      Mockito.when (d1.getValue ()).thenReturn (DiceValue.CROWN);
      Mockito.when (d2.getValue ()).thenReturn (DiceValue.ANCHOR);
      Mockito.when (d3.getValue ()).thenReturn (DiceValue.HEART);
      int winnings = game.playRound(player, pick, bet);
      cdv = game.getDiceValues();
      System.out.printf("Rolled %s, %s, %s\n",
                        cdv.get(0), cdv.get(1), cdv.get(2));

      if (winnings > 0) 
      {
        assertTrue(winnings == (bet * 1));
        System.out.printf("%s won %d, balance now %d\n\n",
            player.getName(), winnings, player.getBalance());
      }
      else 
      {
        assertFalse(winnings == (bet * 0));
        System.out.printf("%s lost, balance now %d\n\n",
            player.getName(), player.getBalance());
      } 
    } 
  }




@Test
//Test to see that the tests payout correctly for double matches
public void testDoubleMatchValues ()
{
  for (int i = 0; i < 3; i++)
  {
    System.out.println("Test 2 - Double Match");
    System.out.println(String.format("Start Game %d: ", i));
    int turn = 0;
    turn++;                    
    DiceValue pick = DiceValue.CROWN;
    System.out.printf("Turn %d: %s bet %d on %s\n",
        turn, player.getName(), bet, pick); 
    Mockito.when (d1.getValue ()).thenReturn (DiceValue.CROWN);
    Mockito.when (d2.getValue ()).thenReturn (DiceValue.CROWN);
    Mockito.when (d3.getValue ()).thenReturn (DiceValue.HEART);
    int winnings = game.playRound(player, pick, bet);
    cdv = game.getDiceValues();
    System.out.printf("Rolled %s, %s, %s\n",
        cdv.get(0), cdv.get(1), cdv.get(2));

    if (winnings > 0) 
    {
      assertTrue(winnings == (bet * 2));
      System.out.printf("%s won %d, balance now %d\n\n",
          player.getName(), winnings, player.getBalance());
    }
    else 
    {
      assertFalse(winnings == (bet * 0));
      System.out.printf("%s lost, balance now %d\n\n",
          player.getName(), player.getBalance());
    } 
  } 
}



@Test
//Test to see that the tests payout correctly for triple matches
public void testTripleMatchValues ()
{
  for (int i = 0; i < 3; i++)
  {
    System.out.println("Test 3 - Triple Match");
    System.out.println(String.format("Start Game %d: ", i));

    int turn = 0;
    turn++;                    
    DiceValue pick = DiceValue.CROWN;
    System.out.printf("Turn %d: %s bet %d on %s\n",
        turn, player.getName(), bet, pick); 
    Mockito.when (d1.getValue ()).thenReturn (DiceValue.CROWN);
    Mockito.when (d2.getValue ()).thenReturn (DiceValue.CROWN);
    Mockito.when (d3.getValue ()).thenReturn (DiceValue.CROWN);
    int winnings = game.playRound(player, pick, bet);
    cdv = game.getDiceValues();
    System.out.printf("Rolled %s, %s, %s\n",
        cdv.get(0), cdv.get(1), cdv.get(2));

    if (winnings > 0) 
    {
      assertTrue(winnings == (bet * 3));
      System.out.printf("%s won %d, balance now %d\n\n",
          player.getName(), winnings, player.getBalance()); 
    }
    else 
    {
      assertFalse(winnings == (bet * 0));
      System.out.printf("%s lost, balance now %d\n\n",
          player.getName(), player.getBalance());
    } 
  } 
}



@Test
//Test to see that the tests payout correctly for no matches
public void testNoMatchValues ()
{
  for (int i = 0; i < 3; i++)
  {
    System.out.println("Test 4 - No Match");
    System.out.println(String.format("Start Game %d: ", i));

    int turn = 0;
    turn++;                    
    DiceValue pick = DiceValue.CROWN;
    System.out.printf("Turn %d: %s bet %d on %s\n",
        turn, player.getName(), bet, pick); 
    Mockito.when (d1.getValue ()).thenReturn (DiceValue.HEART);
    Mockito.when (d2.getValue ()).thenReturn (DiceValue.ANCHOR);
    Mockito.when (d3.getValue ()).thenReturn (DiceValue.HEART);
    int winnings = game.playRound(player, pick, bet);
    cdv = game.getDiceValues();
    System.out.printf("Rolled %s, %s, %s\n",
        cdv.get(0), cdv.get(1), cdv.get(2));

    if (winnings > 0) 
    {
      assertFalse(winnings == (bet * 1));
      System.out.printf("%s won %d, balance now %d\n\n",
          player.getName(), winnings, player.getBalance()); 
    }
    else 
    {
      assertTrue(winnings == (bet * 0));
      System.out.printf("%s lost, balance now %d\n\n",
          player.getName(), player.getBalance());
    } 
  } 
}

}
