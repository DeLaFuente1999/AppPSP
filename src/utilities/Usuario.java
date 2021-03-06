package utilities;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String dni;
    private String password;
    private String name;

    public Usuario(String dni, String password, String name) {
        super();
        this.dni = dni;
        this.password = password;
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
