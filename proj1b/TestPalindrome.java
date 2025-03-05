import org.junit.Test;

import javax.naming.event.NamingExceptionEvent;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");;
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue("aabaa is a palindrome", palindrome.isPalindrome("aabaa"));
        assertTrue("`a` is a palindrome", palindrome.isPalindrome("a"));
        assertTrue("`ab` is not a palindrome", !palindrome.isPalindrome("ab"));
        assertTrue("aA is not a palindrome", !palindrome.isPalindrome("aA"));
        assertTrue("&% is not a palindrome", !palindrome.isPalindrome("&%"));


        CharacterComparator cc = new OffByOne();
        assertTrue("flake is a ofByOnePalindrome", palindrome.isPalindrome("flake", cc));
        assertTrue("Flake is a ofByOnePalindrome", !palindrome.isPalindrome("Flake", cc));
        assertTrue("abcd is not a ofByOnePalindrome", !palindrome.isPalindrome("abcd", cc));
        assertTrue("&% is a ofByOnePalindrome", palindrome.isPalindrome("&%", cc));
    }
}
