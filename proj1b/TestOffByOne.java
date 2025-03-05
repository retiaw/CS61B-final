import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void TestEqualCharacters() {
        assertTrue("a ！= a", !offByOne.equalChars('a', 'a'));
        assertTrue("a == b", offByOne.equalChars('a', 'b'));
    }
}
