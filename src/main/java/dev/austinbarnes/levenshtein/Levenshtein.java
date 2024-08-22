package dev.austinbarnes.levenshtein;

import java.util.Arrays;

public class Levenshtein {

    public boolean isSimilar(String str1, String str2, double maxDifferencePercent) {
        int levDistance = getLevenshteinDistance(str1, str2);

        double percentDifference = ((double) levDistance / Math.max(str1.length(), str2.length())) * 100;
        return percentDifference <= maxDifferencePercent;
    }

    private int getLevenshteinDistance(String str1, String str2) {
        int[][] levMatrix = new int[str1.length() + 1][str2.length() + 1];

        for(int i = 0; i <= str1.length(); i++){
            for(int j = 0; j <= str2.length(); j++){
                if(i == 0){
                    levMatrix[i][j] = j;
                } else if(j == 0){
                    levMatrix[i][j] = i;
                }else{
                    levMatrix[i][j] = minEdits(levMatrix[i - 1][j - 1] + numReplacements(str1.charAt(i - 1), str2.charAt(j - 1)), // replace
                            levMatrix[i - 1][j] + 1, // delete
                            levMatrix[i][j - 1] + 1);// insert
                    
                }
            }
        }
        return levMatrix[str1.length()][str2.length()];
    }

    private int numReplacements(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }

    private int minEdits(int... nums) {
        return Arrays.stream(nums).min().orElse(Integer.MAX_VALUE);
    }
}
