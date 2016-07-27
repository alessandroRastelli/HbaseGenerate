package generator;

import hbase.CreateTable;

import java.io.IOException;
import java.util.Date;

public class BillingMain {

    public static void main(String[] args) throws IOException {

        ItemBillingGenerator IB = new ItemBillingGenerator();

        System.out.println("start1");
        //create file
        /*IB.generate("data/esempio2.txt", (int) Math.pow(10, 5));*/

        long start = new Date().getTime();

        //create table to hbase

        CreateTable ct = new CreateTable();

//        int itemsCount = (int) Math.pow(10, 8);
        int itemsCount = 3;
        for (int i = 0; i < itemsCount; i++) {
            ct.persist(IB.getItem());
        }
        ct.close();

		/*
		getAll()
		*/
/*
		HbaseQuery.getAllRecord();
*/
/*
		HbaseQuery.getFamilyFilter("user","consumo");
*/

     /*   HbaseQuery.getMultiFilter();*/
        long end = new Date().getTime();
        System.out.println("done");
        System.out.println("tempo (mm): " + (end - start));
    }
}
