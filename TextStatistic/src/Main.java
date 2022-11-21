import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();

        int i;
        int allCounter = 0;
        int symbolsCounter = 0;
        int wordCounter = 0;

        Scanner in = new Scanner(System.in);

        System.out.println("Введите путь до файла: ");
        String path = in.nextLine();

        try(FileReader fr = new FileReader(path)){
            while((i = fr.read())!=-1){
                text.append((char)i);
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        Pattern pattern = Pattern.compile(".");
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            allCounter++;

        }
        System.out.println("Всего символов в тексте: "+allCounter);

        pattern = Pattern.compile("\\S");
        matcher = pattern.matcher(text);
        while(matcher.find()){
            symbolsCounter++;
        }
        System.out.println("Количество символов без пробелов: " + symbolsCounter);

        pattern = Pattern.compile("\\b(\\w+)\\b");
        matcher = pattern.matcher(text);
        while(matcher.find()){
            wordCounter++;
        }
        System.out.println("Всего слов в тексте: " + wordCounter);

        text.append("\nВсего символов в тексте: " +allCounter);
        text.append("\nКоличество символов без пробелов: " + symbolsCounter);
        text.append("\nКоличество слов: " + wordCounter);

        try(FileWriter fw = new FileWriter("text.txt")){
            fw.write(text.toString());
            fw.flush();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}