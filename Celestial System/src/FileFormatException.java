import java.io.IOException;

public class FileFormatException extends IOException {

    public FileFormatException(){
        super("Invalid format!");
    }

}
