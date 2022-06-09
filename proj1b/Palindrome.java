/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/9 下午9:21
 */
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> lld = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            lld.addLast(word.charAt(i));
        }
        return lld;
    }

    public boolean isPalindrome(String word) {
        LinkedListDeque<Character> dq = (LinkedListDeque<Character>) wordToDeque(word);
        while (dq.size() == 0 || dq.size() == 1) {
            return true;
        }
        isPalindromeHelper(dq);
        return dq.size() > 1 ? false : true;
    }

    private Deque<Character> isPalindromeHelper(Deque<Character> dq) {
        Character f = dq.removeFirst();
        Character l = dq.removeLast();
        if (!f.equals(l)) {
            dq.addLast('0');
            dq.addLast('0');
            return dq;
        }
        if (dq.size() < 2) {
            return dq;
        }
        return isPalindromeHelper(dq);
    }

    private Deque<Character> isPalindromeHelper(Deque<Character> dq, CharacterComparator cc) {
        Character f = dq.removeFirst();
        Character l = dq.removeLast();
        if (!cc.equalChars(f, l)) {
            dq.addLast('0');
            dq.addLast('0');
            return dq;
        }
        if (dq.size() < 2) {
            return dq;
        }
        return isPalindromeHelper(dq, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        LinkedListDeque<Character> dq = (LinkedListDeque<Character>) wordToDeque(word);
        while (dq.size() == 0 || dq.size() == 1) {
            return true;
        }
        isPalindromeHelper(dq, cc);
        return dq.size() > 1 ? false : true;
    }
}
