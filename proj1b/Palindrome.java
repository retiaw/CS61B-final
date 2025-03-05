
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ret = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            ret.addLast(word.charAt(i));
        }
        return ret;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> str = wordToDeque(word);
        while (str.size() > 1) {
            Character a = str.removeFirst();
            Character b = str.removeLast();
            if (a != b) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindrome_helper(Deque<Character> word, CharacterComparator cc) {
        if (word.size() <= 1) {
            return true;
        } else {
            Character a = word.removeFirst();
            Character b = word.removeLast();
            if (cc.equalChars(a, b)) {
                return isPalindrome_helper(word, cc);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> str = wordToDeque(word);
        return isPalindrome_helper(str, cc);
    }
}
