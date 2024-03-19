package service;

import java.io.FileWriter;
import java.io.IOException;

public class WriterService {
    private CurrentDate currentDate;
    public static void wirteIntoFile(String msg, String path){
        try(FileWriter fw = new FileWriter(path, true);){
            fw.append(msg);
            fw.append("\n");
            fw.flush();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
