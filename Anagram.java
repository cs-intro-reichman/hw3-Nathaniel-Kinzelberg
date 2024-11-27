
/** Functions for checking if a given string is an anagram. */
public class Anagram {
    public static void main(String args[]) {
        // Tests the isAnagram function.
        System.out.println(isAnagram("silent", "listen"));  // true
        System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie", "Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

        // Tests the preProcess function.
        System.out.println(preProcess("What? No way!!!"));  // "whatnoway"

        // Tests the randomAnagram function.
        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
        
        // Performs a stress test of randomAnagram 
        String str = "1234567";
        Boolean pass = true;
        int add = 0;
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test Failed");
    }

    public static boolean isAnagram(String str1, String str2) {
        String processedStr1 = preProcess(str1);
        String processedStr2 = preProcess(str2);
        
        if (processedStr1.length() != processedStr2.length()) return false;

        char[] arr1 = processedStr1.toCharArray();
        char[] arr2 = processedStr2.toCharArray();
        java.util.Arrays.sort(arr1);
        java.util.Arrays.sort(arr2);

        return java.util.Arrays.equals(arr1, arr2);
    }

    private static String preProcess(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toLowerCase().toCharArray()) {
            if (Character.isLetter(c) || c == ' ') {
                result.append(c);
            }
        }
        return result.toString().replaceAll(" ", "");
    }

    public static String randomAnagram(String str) {
        char[] chars = str.toCharArray();
        java.util.List<Character> charList = new java.util.ArrayList<>();
        for (char c : chars) {
            charList.add(c);
        }
        java.util.Collections.shuffle(charList);
        StringBuilder result = new StringBuilder();
        for (char c : charList) {
            result.append(c);
        }
        return result.toString();
    }
}
