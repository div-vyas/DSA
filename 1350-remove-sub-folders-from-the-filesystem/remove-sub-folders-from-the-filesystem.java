import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);

        List<String> result = new ArrayList<>();

        if (folder.length == 0) {
            return result;
        }

        result.add(folder[0]);
        String currentParent = folder[0];

        for (int i = 1; i < folder.length; i++) {
            String path = folder[i];

            if (path.startsWith(currentParent) &&
                (path.length() == currentParent.length() || path.charAt(currentParent.length()) == '/')) {
                continue;
            } else {
                result.add(path);
                currentParent = path;
            }
        }

        return result;
    }
}