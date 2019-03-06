package strings;

import java.util.*;

/**
 * @author rpurigella
 */
public class JoinWordsToMakePalindrome {

    // -------------------- START ----------------------
    static String[] join_words_to_make_a_palindrome(String words[]) {
        String result[] = new String[2];
        result[0] = "NOTFOUND";
        result[1] = "DNUOFTON";

        HashMap<String, Integer> count = new HashMap<String, Integer>();
        for(String word: words){
            if(count.containsKey(word)){
                count.put(word, count.get(word)+1);
            }else{
                count.put(word, 1);
            }
        }

        String current = "";

        for(String left_word: words){

            current = "";
            for(int j=0;j<left_word.length();j++){
                current = left_word.charAt(j) + current;

                if(count.containsKey(current)){
                    if(current.equals(left_word)){
                        if(count.get(current)>1){
                            result[0] = left_word;
                            result[1] = current;
                            return result;
                        }
                    }
                    else if(isPalindrome(left_word.substring(j+1))){
                        result[0] = left_word;
                        result[1] = current;
                        return result;
                    }
                }
            }

            current = "";

            for(int j=left_word.length()-1;j>=0;j--){
                current = current + left_word.charAt(j);

                if(count.containsKey(current)){
                    if(current.equals(left_word)){
                        if(count.get(current)>1){
                            result[0] = current;
                            result[1] = left_word;
                            return result;
                        }
                    }
                    else if(isPalindrome(left_word.substring(0, j))){
                        result[0] = current;
                        result[1] = left_word;
                        return result;
                    }
                }
            }
        }
        return result;
    }

    // Check if string str is palindrome or not
    static boolean isPalindrome(String str) {
        int l = 0;
        int n = str.length();
        while(l<n-l){
            if(str.charAt(l)!=str.charAt(n-1-l)){
                return false;
            }
            l++;
        }
        return true;
    }


    public static void main(String[] args) {
        String[] words = {"ab", "bba"};
        join_words_to_make_a_palindrome(words);

    }
}

