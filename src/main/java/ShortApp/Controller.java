package ShortApp;

import javafx.scene.control.Label;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Controller {
    private Label commandOutput;

     public Controller(Label commandOutput){
         this.commandOutput = commandOutput;
     }

     public void handelInput(String input){
         if(Regex("cmd*", input)){
             exec(input);
         } else if(Regex("google*", input)){
             commandOutput.setText("GOOGLE DETECTED");
         } else if(Regex("www*", input)){
             commandOutput.setText("WEBSITE DETECTED");
         } else if(Regex("ipconfig", input)){
             exec(input);

         }
     }

     public String exec(String command){
         try {
             String message = "";
             Runtime rt = Runtime.getRuntime();
             Process process = rt.exec(command);

             BufferedReader stdInput = new BufferedReader(new
                     InputStreamReader(process.getInputStream()));

             BufferedReader stdError = new BufferedReader(new
                     InputStreamReader(process.getErrorStream()));

             String s = null;
             while ((s = stdInput.readLine()) != null) {
                 message += s;
             }

             while ((s = stdError.readLine()) != null) {
                 message += s;
             }
             return message;
         } catch (IOException e){
             JOptionPane.showMessageDialog(null, e.getMessage());
             return "";
         }
     }

    public boolean Regex(String first, String second)
    {

        // If we reach at the end of both strings,
        // we are done
        if (first.length() == 0 && second.length() == 0)
            return true;

        // Make sure that the characters after '*'
        // are present in second string.
        // This function assumes that the first
        // string will not contain two consecutive '*'
        if (first.length() > 1 && first.charAt(0) == '*' &&
                second.length() == 0)
            return false;

        // If the first string contains '?',
        // or current characters of both strings match
        if ((first.length() > 1 && first.charAt(0) == '?') ||
                (first.length() != 0 && second.length() != 0 &&
                        first.charAt(0) == second.charAt(0)))
            return Regex(first.substring(1),
                    second.substring(1));

        // If there is *, then there are two possibilities
        // a) We consider current character of second string
        // b) We ignore current character of second string.
        if (first.length() > 0 && first.charAt(0) == '*')
            return Regex(first.substring(1), second) ||
                    Regex(first, second.substring(1));
        return false;
    }
}
