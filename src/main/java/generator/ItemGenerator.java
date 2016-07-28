package generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class ItemGenerator {



    public ArrayList<Dim> getDim() {

        return new DimGenerator().generate();

    }

    public ArrayList<String> getItem() {
        ArrayList<String> result = new ArrayList<String>();

        Random rn = new Random();

        for(String column: DimGenerator.table){
            Dim dim = DimGenerator.map_dimesion.get(column);
            if(dim!=null){
                int index =  rn.nextInt(dim.getId().size()-1 - 0 + 1) + 0;
                result.add(dim.getId().get(index));
               // System.out.println("dim: " +dim.getTypeDim()+" id: " +dim.getId().get(index));

            }
            else{
                if( column.equals("POD_ID") ){
                    result.add(String.valueOf(rn.nextInt(100000000 - 10000 + 1) + 10000));
                }else{
                    result.add(String.valueOf(rn.nextInt(1 - 0 + 1) + 0));
                }
            }
        }

        return result;

    }



}

