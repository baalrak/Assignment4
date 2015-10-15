package src;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class Bug2Test
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
    balance = 15;
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


  
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  
  

  @Test
  //Test to verify that the user is not able to get to a 0 balance
  public void testVerifyNotReachingZero ()
  {
    int turn = 0;  
    for (int i = 0; i < 3; i++)
    {
      thrown.expectMessage ("Placing bet would go below limit.");
      System.out.println("Test 1 - Verify not reaching balance of 0");
      System.out.println(String.format("Start Game %d: ", i));

                  
      turn++;  
      DiceValue pick = DiceValue.CROWN;
      System.out.printf("Turn %d: %s bet %d on %s\n",
                         turn, player.getName(), bet, pick); 
      //Set the dice so that there are no wins
      Mockito.when (d1.getValue ()).thenReturn (DiceValue.HEART);
      Mockito.when (d2.getValue ()).thenReturn (DiceValue.ANCHOR);
      Mockito.when (d3.getValue ()).thenReturn (DiceValue.HEART);
      int winnings = game.playRound(player, pick, bet);
      cdv = game.getDiceValues();
      System.out.printf("Rolled %s, %s, %s\n",
                        cdv.get(0), cdv.get(1), cdv.get(2));

        //Two assert statements to verify that the player has not won any amount,
        //and a second to verify that the balance is changing with each turn 
        //until it reaches 5. 
        assertTrue(winnings == (bet * 0));
        assertTrue(player.getBalance() == (balance - (turn * bet)));
        System.out.printf("%s lost, balance now %d\n\n",
            player.getName(), player.getBalance());  
      } 
    } 
 
  
  
  @Test
  //Test to verify that the user is not able to get to a 0 balance
  public void testHypothesis()
  {
    //Setup Test
    player = Mockito.mock (Player.class);
    Mockito.when (player.getBalance ()).thenReturn (5);
    Mockito.when (player.getName ()).thenReturn ("Fred");
    System.out.println("Test 2 - Verify game reaches balance of 0");
    System.out.println(String.format("Start Game %d: ", 1)); 
    DiceValue pick = DiceValue.CROWN;
    System.out.printf("bet %d on %s\n", bet, pick); 
    //Set the dice so that there are no wins
    Mockito.when (d1.getValue ()).thenReturn (DiceValue.HEART);
    Mockito.when (d2.getValue ()).thenReturn (DiceValue.ANCHOR);
    Mockito.when (d3.getValue ()).thenReturn (DiceValue.HEART);
    //Override balanceExceedsLimitBy method to verify that it is causing the
    //bug.
    Mockito.when (player.balanceExceedsLimitBy (5)).thenReturn (false);
    //Verify that the balance is indeed 5
    assertTrue(player.getBalance () == 5);
    //set Balance back to 0
    Mockito.when (player.getBalance ()).thenReturn (0);
    int winnings = game.playRound(player, pick, bet);
    cdv = game.getDiceValues();
    System.out.printf("Rolled %s, %s, %s\n",
                      cdv.get(0), cdv.get(1), cdv.get(2));
    assertTrue(winnings == (bet * 0));
    assertTrue(player.getBalance () == 0);
    System.out.printf("%s lost, balance now %d\n\n",
                      player.getName(), player.getBalance());  
  } 
} 
