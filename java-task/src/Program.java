import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Program {

    public static void main(String[] args)
    {
        Path rscdirectory = Paths.get("rsc");
        String line       = "two";

        printFiles(rscdirectory, line);
    }ч

    static void printFiles(Path directory, String line) {
        try (DirectoryStream<Path> dirStream =
                     Files.newDirectoryStream(directory)) {
            for (Path child: dirStream) {
                String fileName = child.getFileName().toString();
                int lastInd = fileName.lastIndexOf(".");
                if(lastInd != -1 && fileName.substring(lastInd + 1).equals("txt")
                    && fileHasLine(child, line))
                    System.out.println(child.getFileName());
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка ввода-вывода");
        }
    }

    static boolean fileHasLine(Path file, String line) {
        boolean has = false;

        try (BufferedReader bf = new BufferedReader(new FileReader(file.toFile()))) {
            String str;
            while ((str = bf.readLine()) != null && !has) {
                if(str.equals(line))
                    has = true;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не был найден");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка ввода-вывода");
        }

        return has;
    }
}
