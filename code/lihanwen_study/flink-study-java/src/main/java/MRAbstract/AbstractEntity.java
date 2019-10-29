package MRAbstract;

import org.apache.hadoop.io.Writable;

public abstract class AbstractEntity implements Writable {
    //用以生成唯一主键
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
