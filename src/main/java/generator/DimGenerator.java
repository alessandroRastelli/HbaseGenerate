package generator;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alessandro on 20/07/16.
 */
public class DimGenerator {

    public static ArrayList<Dim> pc_dimesion = new ArrayList<Dim>();
    public static Map<String,Dim> map_dimesion = new HashMap<String, Dim>();
    public static ArrayList<String> table = new ArrayList<String>();

    public ArrayList<Dim> generate() {
        if(pc_dimesion.isEmpty()){
            generateColumn();
            try {
                for (String line : Files.readAllLines(Paths.get("data/dim.txt"))) {
                    String[] lineItem = line.split("\t");

                    String id = lineItem[0];
                    String code= lineItem[1];
                    String type = lineItem[2];
                    String typeDim = lineItem[3];
                    //System.out.println("id: " + id + " code: " + code + " type: " +type+ " DIM: "+ typeDim);
                    boolean exist = false;

                    for ( Dim dim : pc_dimesion ){
                        if( dim.getType().equals(type) ){
                            exist = true;
                            dim.getCode().add(code);
                            dim.getId().add(id);
                        }
                    }

                    if( !exist ){
                        ArrayList<String> ids = new ArrayList<String>();
                        ids.add(id);

                        ArrayList<String> codes = new ArrayList<String>();
                        codes.add(code);

                        Dim dim = new Dim(ids,codes,type,typeDim);
                        pc_dimesion.add(dim);

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Dim dim : pc_dimesion){
            map_dimesion.put(dim.getTypeDim(), dim);
        }
        return pc_dimesion;

    }

    private void generateColumn() {
        try {
            for (String line : Files.readAllLines(Paths.get("data/columns.txt"))) {
                String[] lineItem = line.split("\t");
                table.add(lineItem[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
