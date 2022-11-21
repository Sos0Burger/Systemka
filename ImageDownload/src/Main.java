
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static String siteFile = "site.txt";
    public static String site;
    public static String output = "output.txt";
    public static String photoOutput = "Photo/";

    public static void main(String[] args) {

        try(FileWriter fw = new FileWriter(output);BufferedReader bufferedReader = new BufferedReader(new FileReader(siteFile))){
            site = bufferedReader.readLine();
            URL url = new URL(site);

            StringBuilder siteHTML = new StringBuilder();
            URLConnection uc = url.openConnection();
            uc.connect();
            uc = url.openConnection();
            uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            try(BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()))){
                int i;
                while((i = br.read())!=-1 ){
                    siteHTML.append((char)i);
                }
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            Pattern pattern = Pattern.compile("src=\"https://img.freepik.com/([A-z1-9/\\-=\\s\\.]*)\\.jpg");
            Matcher matcher = pattern.matcher(siteHTML);

            while (matcher.find()){
                fw.write(matcher.group().replaceAll("\"|\\?|src=", "")+"\n");
                fw.flush();
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(output))){
            String link;
            ArrayList<DownloadThread> threadArrayList = new ArrayList<>();
            int counter = 0;
            while((link = bufferedReader.readLine())!=null){
                threadArrayList.add(new DownloadThread(link,photoOutput + counter+".jpg" + ""));
                threadArrayList.get(counter).start();
                counter++;
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
class DownloadThread extends Thread{
    String link;
    String file;

    public void run() {
        try{
            URL url = new URL(link);

            URLConnection uc = url.openConnection();
            uc.connect();
            uc = url.openConnection();
            uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

            ReadableByteChannel byteChannel = Channels.newChannel(uc.getInputStream());
            FileOutputStream stream = new FileOutputStream(file);
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            stream.close();
            byteChannel.close();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    DownloadThread(String link, String file){
        this.link=link;
        this.file=file;
    }
}