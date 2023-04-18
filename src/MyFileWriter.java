import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class

public class MyFileWriter {
    private FileWriter writer;
    
    public MyFileWriter(String fileName) throws IOException {
    	System.out.println("top of MyFileWriter() constructor");
        writer = new FileWriter(fileName);
        System.out.println("bottom of MyFileWriter() constructor");
    }
    
    public void writeLine(String line) throws IOException {
    	System.out.println("top of writeLine(). line: " + line);
        writer.write(line);
        writer.write(System.lineSeparator()); // add a newline after the line
        System.out.println("bottom of writeLine()");
    }
    
    public void close() throws IOException {
    	System.out.println("top of close()");
        writer.close();
        System.out.println("bottom of close()");
    }
}