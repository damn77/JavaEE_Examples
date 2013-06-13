package bmt;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BMT_KVPair")
public class Pair implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @Column(unique = true)
    private String key;

    @Column
    private String value;

    public Pair() {}

    public Pair(String key, String value) {
        setKey(key);
        setValue(value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return key + "=" + value;
    }
}
