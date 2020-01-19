import main.State;
import org.junit.Assert;
import org.junit.Test;

public class StateTest {

    @Test
    public void changeStateTest(){
        State alive = State.ALIVE;
        State dead = State.DEAD;
        Assert.assertEquals(State.DEAD,alive.changeState());
        Assert.assertEquals(State.ALIVE,dead.changeState());
    }

}
