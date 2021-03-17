package org.campus02.addresses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AddressManager {
    private ArrayList<Address> addresses;// = new ArrayList<>();

    public AddressManager() {
        this.addresses = new ArrayList<>();
    }

    public void add(Address a){
        addresses.add(a);
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void loadFromCsv(String path, String separtor) throws AddressLoadException {

        // 1. Zugriff auf Datei
        //File file = new File(path);
        FileInputStream fileInputStream = null;
        try {
            // 2. FileInputStream
            fileInputStream = new FileInputStream(path);

            String fileContent = "";
            int byteRead = 0;
            while ((byteRead = fileInputStream.read()) != -1){
                char c = (char) byteRead;
//                String c = Character.toString(byteRead);
                fileContent = fileContent + c;
            }
            System.out.println(fileContent);

            // \n oder \r oder \n\r
            for (String line : fileContent.lines().collect(Collectors.toList())) {
                String[] columns = line.split(separtor);
                Address newAddress = new Address(columns[0], columns[1], columns[2], columns[3]);
                add(newAddress);
            }
        } catch (FileNotFoundException e) {
            throw new AddressLoadException(e);
        } catch (IOException e) {
            throw new AddressLoadException(e);
        }
        finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new AddressLoadException(e);
                }
            }
        }
    }
}
