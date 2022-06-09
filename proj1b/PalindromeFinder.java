import org.junit.Test;

/**
 * This class outputs all palindromes in the words file in the current directory.
 */
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(10))) {
                System.out.println(word);
            }
        }
    }

    public int calNumByN(int N) {
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        int count = 0;
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(N))) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void testMaxN() {
        int count = 0;
        int maxNum = 0;
        int maxN = 0;
        for (int i = 0; i < 26; i++) {
            count = calNumByN(i);
            if (count > maxNum) {
                maxNum = count;
                maxN = i;
            }
        }
        System.out.println(maxN);
    }

    public String getLongestWordByN(int N) {
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        int longestLength = 0;
        String longestWord = "";
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(N))) {
                if (word.length() > longestLength) {
                    longestLength = word.length();
                    longestWord = word;
                }
            }
        }
        return longestWord;
    }

    @Test
    public void testLongestWord() {
        for (int i = 0; i < 26; i++) {
            String longestWord = getLongestWordByN(i);
            System.out.println("the longest offBy" + i + " palindrome = " + longestWord);
        }
    }
}
