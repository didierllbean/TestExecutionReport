package Extrator;//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by klewis on 6/20/2016.
 */

//finds the latest files holding the zephyr executions on the local system
public class FindFile {

    DateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
    String FILENAME = "ZFJ-Executions-";

    public static void main(String[] args){
        FindFile main = new FindFile();
        main.findFile(main.FILENAME, "C:/users/klewis/downloads");
    }

    public void findFile(String nameToFind, String directoryToSearch){
        long newest = 0;
        long newest2 = 0;
        Date date = new Date();
        System.out.println(dateformat.format(date));
        String dateStr = dateformat.format(date);
        String filename = nameToFind;
        File newestfile = new File(filename);
        File newestfile2 = new File(filename);

        File directory = new File(directoryToSearch);

        File[] filesin = directory.listFiles();

        //System.out.println("nameToFind: " + nameToFind + "\nfilename: " + filename);


        for(File fil : filesin){
            if(fil.getName().contains(filename)){
                if(newest < fil.lastModified()){
                    newest = fil.lastModified();
                    newestfile = fil;
                }
            }
        }

        for(File fil : filesin){
            if(fil.getName().contains(filename)){
                if(newest2 < fil.lastModified() && newest > fil.lastModified()){

                    newest2 = fil.lastModified();
                    newestfile2 = fil;
                }
            }
        }

        Date newestd = new Date(newestfile.lastModified());
        System.out.println("Newest file: " + newestfile.getName());
        System.out.println("Newest2 file: " + newestfile2.getName());
        System.out.println("newest file time: " + newestd);


        ScrapeDataXML scrape = new ScrapeDataXML();
        try {
            scrape.run(newestfile, newestfile2, newestd.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




    }


}