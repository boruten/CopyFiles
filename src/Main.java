import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File sourceDirectory = new File("C:/Users/Данила/Desktop/source_directory");
        File resultFile = new File("C:/Users/Данила/Desktop/source_directory/result.txt");
        try  {
            FileOutputStream outputStream = new FileOutputStream(resultFile);
            File[] files = sourceDirectory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        int data;
                        while ((data = fileInputStream.read()) != -1) {
                            outputStream.write(data);
                        }
                        fileInputStream.close();
                    }
                }
                outputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}