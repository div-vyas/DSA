import java.util.Arrays;

class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int i = 0; // player pointer
        int j = 0; // trainer pointer
        int matches = 0;

        while (i < players.length && j < trainers.length) {
            if (players[i] <= trainers[j]) {
                // Valid match
                matches++;
                i++;
                j++;
            } else {
                // Trainer too weak, try next trainer
                j++;
            }
        }

        return matches;
    }
}
