package hbase;
import generator.Dim;
import generator.DimGenerator;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateTable {
    private Configuration config;
    private HTable pcDimension=null;
    private HTableDescriptor descriptorPcDim;
    private HBaseAdmin admin;

    private HTable pcFctReceptionCs=null;
    private HTableDescriptor descriptorPcFctRecepCs;

    public CreateTable(String tableDimensionName,String tableFctName,List<Dim> listDim) throws IOException {
        this.config = HBaseConfiguration.create();
        this.admin = new HBaseAdmin(config);
        this.create_table_pc_dimension(tableDimensionName,listDim);
        this.create_table_pc_fct_reception_cs(tableFctName);
    }

    // create table PC_DIMENSION if not exists
    private void create_table_pc_dimension(String tableName,List<Dim> listDim) throws IOException {
        if(!this.admin.tableExists(tableName)){
            this.descriptorPcDim = new HTableDescriptor(TableName.valueOf(tableName));
            this.descriptorPcDim.addFamily(new HColumnDescriptor("dimension"));
            this.admin.createTable(this.descriptorPcDim);
            this.pcDimension = new HTable(this.config, tableName);
            this.persistDim(listDim);
        }
    }

    // create table PC_FCT_RECEPTION if not exists
    private void create_table_pc_fct_reception_cs(String tableName) throws IOException{
        if(!this.admin.tableExists(tableName)){
            this.descriptorPcFctRecepCs = new HTableDescriptor(TableName.valueOf(tableName));
            this.descriptorPcFctRecepCs.addFamily(new HColumnDescriptor("records"));
            this.admin.createTable(this.descriptorPcFctRecepCs);
        }
        this.pcFctReceptionCs = new HTable(this.config, tableName);
    }

    public void persist(List<String> list) throws IOException {
        String hash = Math.abs(list.hashCode()) +""+String.valueOf(System.currentTimeMillis());
        Put person = new Put(Bytes.toBytes(String.valueOf(hash)));
        for(int i=0; i<DimGenerator.table.size();i++) {
            byte [] colum_family = Bytes.toBytes("records");
            person.add(colum_family, Bytes.toBytes(DimGenerator.table.get(i)), Bytes.toBytes(list.get(i)));
        }
        this.pcFctReceptionCs.put(person);
    }

    private void persistDim(List<Dim> listDim) throws IOException {

        for(Dim dim: listDim) {
            for(int i = 0; i<dim.getCode().size();i++) {
                String id = dim.getId().get(i);
                String code = dim.getCode().get(i);
                String type = dim.getType();
                Put person = new Put(Bytes.toBytes(id));
                person.add(Bytes.toBytes("dimension"), Bytes.toBytes("code"), Bytes.toBytes(code));
                person.add(Bytes.toBytes("dimension"), Bytes.toBytes("type"), Bytes.toBytes(type));
                this.pcDimension.put(person);
            }
        }
    }

    public void close() throws IOException {
        // flush commits and close the table
        if(this.pcDimension!=null)
            this.pcDimension.flushCommits();
        if(this.pcFctReceptionCs!=null)
            this.pcFctReceptionCs.close();
    }

    public void deleteTable(String tableName) throws IOException{
        this.admin.disableTable(tableName);
        this.admin.deleteTable(tableName);
        System.out.println("Table deleted");
    }
}