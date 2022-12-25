import java.io.*;
import java.util.Objects;
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

    public boolean createGraph() {
        filesGraph = new Graph();
        try {
            createGraphDFS(new File(root));
        } catch (FileNotFoundException e) {
            System.out.println("Проблемы с чтением файлов!");
            return false;
        }
        return filesGraph.getList();
    }

    public void print() {
        for (int i = 0; i < filesGraph.result.length; ++i) {
            for (int j = 0; j < filesGraph.result[i].size(); ++j) {
                try {
                    System.out.println(filesGraph.result[i].get(j) + ": ");
                    printFile(filesGraph.result[i].get(j));
                } catch (FileNotFoundException e) {
                    System.out.println(filesGraph.result[i].get(j) + " cannot be opened.");
                }
            }
        }
    }

    private void printFile(String filePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                if (!Objects.equals(words[0], "require")) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Проблема c содержимым файла!");
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
                        String[] words = line.split(" ");
                        if (Objects.equals(words[0], "require")) {
                            filesGraph.add(item.getAbsolutePath(), words[1]);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Проблема c содержимым файла!");
                }
            }
        }
    }
}
