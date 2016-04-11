package patterns.decorator.uppercaseinputstream;

import java.io.*;

/**
 * Convert input data to upper case
 */
public class InputStream {

    public static void main(String[] args) {
        int c;
        File file = new File("/home/smolyan/DataFile/d1.txt");

        try (FileInputStream fileInputStream = new FileInputStream(file);
                         BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                         FilterInputStream filterInputStream = new UpperCaseInputStream(bufferedInputStream))
        {

            while ((c = filterInputStream.read()) >= 0){
                System.out.print((char) c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
