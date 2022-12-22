public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> t = new LinkedListDeque<>();
        for (char c : word.toCharArray()) {
            t.addLast(c);
        }
        return t;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> t = this.wordToDeque(word);
        return isPalindrome(t);
    }

    // Recursive helper function.
    private boolean isPalindrome(Deque<Character> t) {
        if (t.isEmpty() || t.size() == 1) {
            return true;
        } else {
            if (t.removeFirst() == t.removeLast()) {
                return isPalindrome(t);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> t = this.wordToDeque(word);
        return isPalindrome(t, cc);
    }

    private boolean isPalindrome(Deque<Character> t, CharacterComparator cc) {
        if (t.isEmpty() || t.size() == 1) {
            return true;
        } else {
            if (cc.equalChars(t.removeFirst(), t.removeLast())) {
                return isPalindrome(t, cc);
            } else {
                return false;
            }
        }
    }
}
