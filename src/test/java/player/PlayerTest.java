package player;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void lose_when_dice_number_is_too_low(){
        Dice dice = Mockito.mock(Dice.class);

        Mockito.when(dice.roll()).thenReturn(2); //forzamos el resultado de dice.roll a 2

        Player player = new Player(dice, 3);
        //assertEquals(false, player.play());
        assertFalse(player.play());
    }

    @Test
    public void win_when_dice_number_is_too_big(){
        Dice dice = Mockito.mock(Dice.class);

        Mockito.when(dice.roll()).thenReturn(4); //forzamos el resultado de dice.roll a 2

        Player player = new Player(dice, 3);
        //assertEquals(true, player.play());
        assertTrue(player.play());
    }

}