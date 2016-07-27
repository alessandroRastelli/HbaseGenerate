package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class CreateTable {

    private HTable table = null;

    public CreateTable() throws IOException {
        Configuration config = HBaseConfiguration.create();

        // create an admin object using the config
        HBaseAdmin admin = new HBaseAdmin(config);

        // create the table...
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("Enel100M"));
        // ... with two column families
        tableDescriptor.addFamily(new HColumnDescriptor("user"));
        tableDescriptor.addFamily(new HColumnDescriptor("location"));
        admin.createTable(tableDescriptor);
        config.set("hbase.client.keyvalue.maxsize", "10000000");
        this.table = new HTable(config, "tes");

    }

    public void persist(String item) throws IOException {
        String[] people = item.split(",");

        Put person = new Put(Bytes.toBytes(people[0]));
        person.add(Bytes.toBytes("user"), Bytes.toBytes("data"), Bytes.toBytes(people[1]));
        person.add(Bytes.toBytes("user"), Bytes.toBytes("consumo"), Bytes.toBytes(people[2]));
        person.add(Bytes.toBytes("user"), Bytes.toBytes("fascia"), Bytes.toBytes(people[5]));
        person.add(Bytes.toBytes("location"), Bytes.toBytes("nation"), Bytes.toBytes(people[3]));
        person.add(Bytes.toBytes("location"), Bytes.toBytes("city"), Bytes.toBytes(people[4]));
        person.add(Bytes.toBytes("location"), Bytes.toBytes("cap"), Bytes.toBytes(people[6]));
        this.table.put(person);

    }


    public void close() throws IOException {
        // flush commits and close the table
        this.table.flushCommits();
        this.table.close();
    }
}