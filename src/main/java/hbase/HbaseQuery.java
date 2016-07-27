package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alessandro on 21/07/16.
 */
public class HbaseQuery {
    /**
     * Initialization
     */
    private static final String tableName = "Enel2";
    public static long DEFAULT_HBASE_CLIENT_SCANNER_MAX_RESULT_SIZE = Long.MAX_VALUE;
    private static Configuration config = null;

    static {
        config = HBaseConfiguration.create();
    }

    public static void getAllRecord() {
        try {
            HTable table = new HTable(config, tableName);

            Scan s = new Scan();
            ResultScanner ss = table.getScanner(s);
            for (Result r : ss) {
                for (KeyValue kv : r.raw()) {
                    System.out.print(new String(kv.getRow()) + " ");
                    System.out.print(new String(kv.getFamily()) + ":");
                    System.out.print(new String(kv.getQualifier()) + " ");
                    System.out.println(new String(kv.getValue()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getFamilyFilter(String family, String column) {
        try {
            HTable table = new HTable(config, tableName);

            Scan s = new Scan();
            s.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));

            Filter filter1 = new ValueFilter(CompareFilter.CompareOp.GREATER, new BinaryComparator(Bytes.toBytes("800")));
            Filter filter2 = new ValueFilter(CompareFilter.CompareOp.LESS, new BinaryComparator(Bytes.toBytes("900")));

            List<Filter> filters = new ArrayList<Filter>();
            filters.add(filter1);
            filters.add(filter2);

            FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ALL, filters);
            s.setFilter(list);

            ResultScanner ss = table.getScanner(s);
            int count = 0;
            for (Result r : ss) {
                count++;
                String row = new String(r.getRow());
                String res = new String(r.getValue(Bytes.toBytes("user"), Bytes.toBytes("consumo")));
                System.out.println("id: " + row + " consumo: " + res);
            }
            System.out.print("risultati trovati: " + count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMultiFilter() {
        try {
            HTable table = new HTable(config, "Enel2");

            List<Filter> filters = new ArrayList<Filter>();

            Filter famFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("user")));
            Filter colFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("consumo")));
            Filter valFilter = new ValueFilter(CompareFilter.CompareOp.GREATER, new BinaryComparator(Bytes.toBytes("800")));

            filters.add(famFilter);
            filters.add(colFilter);
            filters.add(valFilter);

            Filter famFilter2 = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("user")));
            Filter colFilter2 = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("fascia")));
            Filter valFilter2 = new ValueFilter(CompareFilter.CompareOp.NOT_EQUAL, new BinaryComparator(Bytes.toBytes("A1")));

/*
            filters.add(famFilter2);
*/
            filters.add(colFilter2);
            filters.add(valFilter2);

            FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ALL, filters);
            Scan s = new Scan();
            s.setFilter(list);

            ResultScanner ss = table.getScanner(s);
            int count = 0;

            for (Result r : ss) {
                count++;
              /*  String row =new String(r.getRow());
                String res = new String( r.getValue(Bytes.toBytes("user"),Bytes.toBytes("consumo")));
                System.out.println("id: " + row + " fascia: " + res  );*/

             /*   String res = new String( r.getValue(Bytes.toBytes("user"),Bytes.toBytes("consumo")));
                System.out.println("id: " + row + " consumo: " + res );*/
            }
            System.out.print("risultati trovati: " + count);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
