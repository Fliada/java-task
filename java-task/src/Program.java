import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Program {
    final static Path rscdirectory = Paths.get("/rsc");
    public static void Main()
    {
        if(Files.exists(rscdirectory) && Files.isDirectory(rscdirectory))
            try (DirectoryStream<Path> dirStream =
                         Files.newDirectoryStream(rscdirectory)) {

            } catch (IOException e) {
                throw new RuntimeException("Такого файла или директории не существует");
            }
    }
}
