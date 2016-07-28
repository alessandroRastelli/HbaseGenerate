package generator;

import java.util.List;

/**
 * Created by alessandro on 27/07/16.
 */
public class Dim {

    private List<String> id;
    private List<String> code;
    private String type;
    private String typeDim;


    public Dim(List<String> id, List<String> code, String type, String typeDim) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.typeDim = typeDim;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getCode() {
        return code;
    }

    public void setCode(List<String> code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDim() { return typeDim; }

    public void setTypeDim(String typeDim) { this.typeDim = typeDim; }
}
