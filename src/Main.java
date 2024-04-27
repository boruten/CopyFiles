import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File sourceDirectory = new File("C:/directory");
        File resultFile = new File("C:/directory/result.txt");

        List<String> excludedFiles = new ArrayList<>();
        excludedFiles.add("result.txt");

        try (FileOutputStream outputStream = new FileOutputStream(resultFile)) {
            File[] files = sourceDirectory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && !excludedFiles.contains(file.getName())) {
                        try (FileInputStream fileInputStream = new FileInputStream(file)) {
                            int data;
                            while ((data = fileInputStream.read()) != -1) {
                                outputStream.write(data);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла" + e.getMessage());
        }
    }
}