package generator;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by alessandro on 20/07/16.
 */
public class CityGenerator {

    public ArrayList<String> generate() {

        ArrayList<String> cities = new ArrayList<String>();
        try {
            for (String line : Files.readAllLines(Paths.get("data/data.txt"))) {
                cities.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;

    }
}
