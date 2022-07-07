package models;

import java.io.Serializable;

/**
 *
 * @author Dakota Chatt
 * @version June 25, 2022
 */
public class Role implements Serializable {
    private int id;
    private String name;
    
    public Role() {
        this.id = -1;
        this.name = "";
    }
    
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
