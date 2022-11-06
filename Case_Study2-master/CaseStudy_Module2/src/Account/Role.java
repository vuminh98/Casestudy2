package Account;

import java.io.Serializable;

public class Role implements Serializable {
    private String name_role;

    public Role(String name_role) {
        this.name_role = name_role;
    }

    public String getName_role() {
        return name_role;
    }

    public void setName_role(String name_role) {
        this.name_role = name_role;
    }


}
