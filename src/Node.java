import java.util.ArrayList;

public class Node {
    private final String fileName;
    public ArrayList<Integer> children = new ArrayList<>();

    public Node(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
