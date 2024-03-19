package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderService {
    private final static String pathHistory = "./chat.txt";

    public String getChatHistory(){
        try(BufferedReader br = new BufferedReader(new FileReader(pathHistory))){
            String line = br.readLine();
            while(br.readLine() != null){
                line = line + "\n" + br.readLine() ;
            }
            return line;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
