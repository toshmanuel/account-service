import java.util.Arrays;

public class QuestionTwo {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(computeScores(new int[]{4, 1, 5, 6, 6}, new int[]{2, 5, 1, 0, 6}))); //[3, 1]
        System.out.println(Arrays.toString(computeScores(new int[]{6, 3, 5, 6, 1}, new int[]{6, 1, 6, 4, 2}))); //[2, 2]
        System.out.println(Arrays.toString(computeScores(new int[]{1, 4, 7, 2, 4}, new int[]{3, 4, 2, 4, 4}))); //[1, 4]
    }

    public static int[] computeScores(int[] player1, int[] player2) {
        int[] scores = new int[2];
        for(int index = 0; index < player1.length; index++) {
            if(player1[index] > player2[index]){
                ++scores[0];
            }else{
                if(player1[index] < player2[index]){
                    ++scores[1];
                }
            }
        }
        return scores;
    }
}
