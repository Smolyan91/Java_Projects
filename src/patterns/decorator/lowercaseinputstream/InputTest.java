package patterns.decorator.lowercaseinputstream;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputTest {
    public static void main(String[] args) {
        int c;
        try (FileInputStream fileInputStream = new FileInputStream("/home/smolyan/DataFile/d1.txt");
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                            InputStream lowerCaseInputStream = new LowerCaseInputStream(bufferedInputStream))
        {
            while ((c = lowerCaseInputStream.read()) >= 0){
                System.out.print((char) c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
