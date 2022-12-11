import java.io.*;
import java.util.Scanner;

public class FilesReader {
    private String root;
    private Graph filesGraph;

    public FilesReader() {
        System.out.println("Введите путь к корневому каталогу.");
        Scanner in = new Scanner(System.in);
        root = in.next();
        while (!new File(root).isDirectory()) {
            System.out.println("Нет такого каталога!");
            System.out.println("Введите путь к корневому каталогу.");
            root = in.next();
        }
    }

    public void createGraph() {
        filesGraph = new Graph();
        try {
            createGraphDFS(new File(root));
        } catch (FileNotFoundException e) {
            System.out.println("Проблемы с чтением файлов!");
            return;
        }
        filesGraph.getList();
    }

    public void print() {
        for (int i = 0; i < filesGraph.result.length; ++i) {
            for (int j = 0; j < filesGraph.result[i].size(); ++j) {
                System.out.println(filesGraph.result[i].get(j));
            }
        }
    }

    private void createGraphDFS(File path) throws FileNotFoundException {
        if (path.listFiles() == null) {
            return;
        }
        for (File item : path.listFiles()) {
            if (item.isDirectory()) {
                createGraphDFS(item);
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(item.toString()));
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        filesGraph.add(item.getAbsolutePath(), line.split(" ")[1]);
                    }
                } catch (Exception e) {
                    System.out.println("Проблема c содержимым файла!");
                }
            }
        }
    }
}
