package org.campus02.copy;

import java.io.*;
import java.nio.file.Files;

public class CopyDemoApp {

    public static void main(String[] args) {

        String source = "D:\\temp\\jre-8u281-windows-x64.exe";
        String destination = "D:\\temp\\copy-file.exe";

        try {
            System.out.println("Starting to copy " + source + " >> to >> " + destination);
            copyFiles(source, destination);
            System.out.println("finished");
        } catch (FileAlreadyExistsException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFiles(String source, String destination) throws FileAlreadyExistsException, IOException {

        File sourceFile = new File(source);
        File destinationFile = new File(destination);

        if (destinationFile.exists()){
            throw new FileAlreadyExistsException("Destination exists already: " + destination);
        }

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(sourceFile);
            fileOutputStream = new FileOutputStream(destinationFile, false);

            int counter = 0;
            int byteRead = 0;
            System.out.print("writing ... ");
            while ((byteRead = fileInputStream.read()) != -1) {
                fileOutputStream.write(byteRead);
                counter++;
                if (counter % (1024 * 1024) == 0)
                    System.out.print("1MB..");
            }
            fileOutputStream.flush();
        } finally {
            try {
                fileInputStream.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }

            try {
                fileOutputStream.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }

    }
}
