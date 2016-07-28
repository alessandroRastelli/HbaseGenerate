package generator;

import hbase.CreateTable;

import java.io.IOException;

public class Main {

    public static final int NUM_RECORDS = (int) Math.pow(10,6);
    public static final String PC_DIMENSION = "PC_DIMENSION";
    public static final String PC_FCT_RECEPTION_CS = "PC_FCT_RECEPTION_CS";

    public static void main(String[] args) throws IOException {
        ItemGenerator IB = new ItemGenerator();
        CreateTable ct = new CreateTable(PC_DIMENSION,PC_FCT_RECEPTION_CS,IB.getDim());

        for(int i = 0; i<NUM_RECORDS;i++) {
            ct.persist(IB.getItem());
        }

//        ct.deleteTable("DIM1");
//        ct.deleteTable("PC1");

    }
}
