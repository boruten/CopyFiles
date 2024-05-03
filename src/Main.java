import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String[] config = readConfigValues();
        if (config != null && config.length == 2) {
            File sourceDirectory = new File(config[0]);
            File resultFile = new File(config[1]);
            readFile(sourceDirectory, resultFile);
        } else {
            System.err.println("Ошибка при чтении файла конфигурации");
        }
    }


    public static void readFile(File sourceDirectory, File resultFile) {
        Set<String> excludedFiles = new HashSet<>();
        excludedFiles.add(resultFile.getName());

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
                            outputStream.write('\n');
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла" + e.getMessage());
        }
    }

    public static String[] readConfigValues() {
        String[] values = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/directory/config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("sourceDirectory=")) {
                    values[0] = line.substring("sourceDirectory=".length());
                } else if (line.startsWith("resultFile=")) {
                    values[1] = line.substring("resultFile=".length());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла конфигурации: " + e.getMessage());
        }
        return values;
    }
}