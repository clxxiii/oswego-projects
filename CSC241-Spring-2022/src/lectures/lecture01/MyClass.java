package lectures.lecture01;

import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

public class MyClass {
    public static void main(String[] args) throws Exception {
          //System.out.println(FileSystems.getDefault().getPath("src/Lecture01/names.txt").toAbsolutePath());
        Path p = Paths.get(System.getProperty("user.dir"),"src","Lecture01","roster.txt");

          // input file
        File inputFile = new File(p.toString());
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

          // output file
        File outputFile = new File(p.toString()+".temp");
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        Scanner line = new Scanner(inputFile);
        String rLine = new String();
        String name,ID;
        while(line.hasNext()) {
            rLine = line.nextLine();
            name = rLine.substring(0,rLine.lastIndexOf(" "));
            ID = rLine.substring(rLine.lastIndexOf(" ")+1);
            if (name.equals("John"))
                rLine = "John2 " + ID;
            bufferedWriter.write(rLine, 0, rLine.length());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        line.close();
        bufferedReader.close();

        inputFile.delete();
        outputFile.renameTo(new File(p.toString()));
    }
}
