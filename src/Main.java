public class Main {
    public static void main(String[] args) {
        Graph filesGraph = new Graph();
        filesGraph.add("a");
        filesGraph.add("aa", "a");
        filesGraph.add("aaa", "aa");
        filesGraph.add("b", "a");
        filesGraph.add("bb", "b");
        filesGraph.add("aaa", "bb");
        filesGraph.add("aaa", "a");
        filesGraph.getList();
        for (int i = 0; i < filesGraph.result.length; ++i) {
            for (int j = 0; j < filesGraph.result[i].size(); ++j) {
                System.out.println(filesGraph.result[i].get(j));
            }
        }
    }
}