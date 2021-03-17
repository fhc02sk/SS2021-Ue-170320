package org.campus02.addresses;

public class AddressDemoApp {

    public static void main(String[] args) {

        Address susi = new Address("Susi", "Sorglos", 
                "00436648855221", "susi.sorglos@gmail.com");
        Address max = new Address("Max", "Mustermann", "004367699887766"
                , "max.mustermann@test.com");
        
        AddressManager am = new AddressManager();
        am.add(susi);
        am.add(max);

        System.out.println("am.getAddresses() = " + am.getAddresses());


        am.loadFromCsv("D:\\temp\\addresses.csv", ";");

    }
}
