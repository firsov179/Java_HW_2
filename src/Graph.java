import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.max;

public class Graph {
    private final ArrayList<Node> graph = new ArrayList<>();
    private final HashMap<String, Integer> nameToIndex = new HashMap<>();
    public ArrayList<String>[] result;
    private ArrayList<Integer> heights;

    public int add(String fileName) {
        if (nameToIndex.containsKey(fileName)) {
            return nameToIndex.get(fileName);
        } else {
            graph.add(new Node(fileName));
            nameToIndex.put(fileName, graph.size() - 1);
            return graph.size() - 1;
        }
    }
    public void add(String fileName, String parentName) {
        int indexPar = add(parentName);
        int indexCur = add(fileName);
        graph.get(indexPar).children.add(indexCur);
    }

    public boolean getList() {
        if (cycleSearch()) {
            return false;
        }

        heights = new ArrayList<>(graph.size());
        for (int i = 0; i < graph.size(); ++i) {
            heights.add(-1);
        }
        for (int i = 0; i < heights.size(); ++i) {
            if (heights.get(i) == -1) {
                createHeights(i, 0);
            }
        }
        int maxHeight = 0;
        for (Integer height : heights) {
            maxHeight = max(maxHeight, height);
        }
        maxHeight++;
        result = (ArrayList<String>[]) new ArrayList[maxHeight];
        for (int  i = 0; i < maxHeight; ++i) {
            result[i] = new ArrayList<>();
        }
        for (int i = 0; i < heights.size(); ++i) {
            result[heights.get(i)].add(graph.get(i).getFileName());
        }
        return true;
    }

    private void createHeights(int v, int height) {
        heights.set(v, height);
        for (int i = 0; i < graph.get(v).children.size(); ++i) {
            int u = graph.get(v).children.get(i);
            if (heights.get(u) < height + 1) {
                createHeights(u, height + 1);
            }
        }
    }

    private boolean cycleSearchDFS(int v) {
        heights.set(v, 1);
        for (int i = 0; i < graph.get(v).children.size(); ++i) {
            int u = graph.get(v).children.get(i);
            if (heights.get(u) == 0) {
                if (cycleSearchDFS(u)) {
                    return true;
                }
            } else if (heights.get(u) == 1) {
                return true;
            }
        }
        heights.set(v, 2);
        return false;
    }

    private boolean cycleSearch() {
        heights = new ArrayList<>(graph.size());
        for (int i = 0; i < graph.size(); ++i) {
            heights.add(0);
        }
        for (int i = 0; i < graph.size(); ++i) {
            if (cycleSearchDFS(i)) {
                return true;
            }
        }
        return false;
    }
}
