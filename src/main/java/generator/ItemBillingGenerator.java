package generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class ItemBillingGenerator {

    public void generate(String fileName, int num_items) {

        try {
            FileOutputStream stream = new FileOutputStream(fileName);
            PrintStream data = new PrintStream(stream);

            for (int i = 0; i < num_items; i++) {
                Random rn = new Random();

                int idUser = rn.nextInt(1000000000 - 1000 + 1) + 1000;

                String date = RandomDateGenerator.generate();

                int consumption = rn.nextInt(1000 - 0 + 1) + 0;

                ArrayList<String> fascia = new ArrayList<String>();
                fascia.add("A1");
                fascia.add("A2");
                fascia.add("A3");

                ArrayList<String> cities = new CityGenerator().generate();
                String city = cities.get(rn.nextInt(98 - 0 + 1) + 0);

                data.println(idUser + "," + date + "," + consumption + ",ITA," + city + "," + fascia.get(rn.nextInt(2 - 0 + 1) + 0));
            }
            data.close();
            stream.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }
    }

    public String getItem() {
        Random rn = new Random();

        int idUser = rn.nextInt(100000000 - 1000 + 1) + 1000;

        String date = RandomDateGenerator.generateLong().toString();

        int consumption = rn.nextInt(1000 - 0 + 1) + 0;

        ArrayList<String> fascia = new ArrayList<String>();
        fascia.add("A1");
        fascia.add("A2");
        fascia.add("A3");

        ArrayList<String> cities = new CityGenerator().generate();
        String city = cities.get(rn.nextInt(98 - 0 + 1) + 0);

        String cap = (rn.nextInt((9 - 0) + 1) + 0) + "" + (rn.nextInt((9 - 0) + 1) + 0) + "" +
                (rn.nextInt((9 - 0) + 1) + 0) + "" + (rn.nextInt((9 - 0) + 1) + 0) + "" +
                (rn.nextInt((9 - 0) + 1) + 0);

        return idUser + "," + date + "," + consumption + ",ITA," + city + "," + fascia.get(rn.nextInt(2 - 0 + 1) + 0) + "," + cap;
    }

}

