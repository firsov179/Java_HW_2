public class Main {
    public static void main(String[] args) {
        FilesReader cur = new FilesReader();
        if (cur.createGraph()) {
            cur.print();
        } else {
            System.out.println("В файлах есть циклическая зависимость!");
        }
    }
}