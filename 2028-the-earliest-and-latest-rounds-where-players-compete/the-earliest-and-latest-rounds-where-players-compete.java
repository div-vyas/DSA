import java.util.*;

class Solution {
    int earliest = Integer.MAX_VALUE;
    int latest = Integer.MIN_VALUE;
    Map<String, int[]> memo = new HashMap<>();

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        List<Integer> players = new ArrayList<>();
        for (int i = 1; i <= n; i++) players.add(i);

        dfs(players, 1, firstPlayer, secondPlayer);
        return new int[]{earliest, latest};
    }

    private void dfs(List<Integer> players, int round, int firstPlayer, int secondPlayer) {
        // Key for memoization
        String key = players.toString();
        if (memo.containsKey(key)) return;

        int size = players.size();
        for (int i = 0; i < size / 2; i++) {
            int a = players.get(i);
            int b = players.get(size - 1 - i);

            // If the two players meet
            if ((a == firstPlayer && b == secondPlayer) || (a == secondPlayer && b == firstPlayer)) {
                earliest = Math.min(earliest, round);
                latest = Math.max(latest, round);
                return;
            }
        }

        // List of all possible winners from this round
        List<List<Integer>> nextRounds = new ArrayList<>();
        backtrack(players, 0, players.size() - 1, new ArrayList<>(), nextRounds, firstPlayer, secondPlayer);

        for (List<Integer> next : nextRounds) {
            Collections.sort(next); // Maintain order
            dfs(next, round + 1, firstPlayer, secondPlayer);
        }

        memo.put(key, new int[]{earliest, latest});
    }

    // Generate all possible outcomes of a round
    private void backtrack(List<Integer> players, int l, int r, List<Integer> temp, List<List<Integer>> results, int fp, int sp) {
        if (l > r) {
            results.add(new ArrayList<>(temp));
            return;
        }

        if (l == r) {
            temp.add(players.get(l)); // odd middle element
            backtrack(players, l + 1, r, temp, results, fp, sp);
            temp.remove(temp.size() - 1);
            return;
        }

        int a = players.get(l);
        int b = players.get(r);

        // If one of them is firstPlayer or secondPlayer, they must win
        if (a == fp || a == sp) {
            temp.add(a);
            backtrack(players, l + 1, r - 1, temp, results, fp, sp);
            temp.remove(temp.size() - 1);
        } else if (b == fp || b == sp) {
            temp.add(b);
            backtrack(players, l + 1, r - 1, temp, results, fp, sp);
            temp.remove(temp.size() - 1);
        } else {
            // Try both outcomes
            temp.add(a);
            backtrack(players, l + 1, r - 1, temp, results, fp, sp);
            temp.remove(temp.size() - 1);

            temp.add(b);
            backtrack(players, l + 1, r - 1, temp, results, fp, sp);
            temp.remove(temp.size() - 1);
        }
    }
}
