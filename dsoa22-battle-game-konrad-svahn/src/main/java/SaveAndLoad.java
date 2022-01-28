import java.io.*;
import java.nio.charset.StandardCharsets;

public class SaveAndLoad {
    
    static String readTextFile(String fileName) {
        StringBuilder retString = new StringBuilder();
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            InputStreamReader reader = new InputStreamReader(inStream, StandardCharsets.UTF_8);
            BufferedReader buffR = new BufferedReader(reader);
            String line;

            while ((line = buffR.readLine()) != null) {    
                retString.append(line);
            }
            buffR.close();

        } catch (FileNotFoundException e) {
            System.out.println("error1: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("error2: IOExeption");
            e.printStackTrace();
        }
        return retString.toString();
    }

    static void writeTextFile(String fileName, String fileContent) {
        try {
            FileOutputStream outStream = new FileOutputStream(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(outStream, StandardCharsets.UTF_8);
            BufferedWriter buffW = new BufferedWriter(writer); 
            buffW.write(fileContent);
            /*
            for (int i = 0; i < fileContent.length(); i++) {
                writer.write(fileContent.charAt(i));    
            }
            writer.close();
            */
            buffW.close();

        } catch (FileNotFoundException e) {
            System.out.println("error1: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("error2: IOExeption");
            e.printStackTrace();
        }
    }
}
