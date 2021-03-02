import java.io.IOException;

public class FileNotFoundException extends IOException {

    public FileNotFoundException(){
        super("File not found!");
    }

}
