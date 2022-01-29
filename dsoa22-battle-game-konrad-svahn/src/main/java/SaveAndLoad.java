import java.io.*;
import java.nio.charset.StandardCharsets;

public class SaveAndLoad {

    static Object loadObject(String fileName) {
        Object retObject = null;
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            ObjectInputStream objIn = new ObjectInputStream(inStream);
            retObject = objIn.readObject();
            objIn.close();

        } catch (FileNotFoundException e) {
            System.out.println("error1: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("error2: IOExeption");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("error3: ClassNotFoundExceptio");
            e.printStackTrace();
        }
        return retObject;
    }

    static void saveObject(String fileName, Object objektToSave) {
        try {
            FileOutputStream outStream = new FileOutputStream(fileName);
            ObjectOutputStream objOutS = new ObjectOutputStream(outStream);

            objOutS.writeObject(objektToSave);

            objOutS.close();
            UserInterface.printSaveMessage(true);

        } catch (FileNotFoundException e) {
            System.out.println("error4: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("error5: IOExeption");
            e.printStackTrace();
        }
    } 
    
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
            System.out.println("error6: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("error7: IOExeption");
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
            buffW.close();

        } catch (FileNotFoundException e) {
            System.out.println("error8: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("error9: IOExeption");
            e.printStackTrace();
        }
    }
}
