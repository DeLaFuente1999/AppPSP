package utilities;

import java.io.Serializable;

public class Balance implements Serializable {

    private String saldo;
    private String fecha;


    public Balance(String saldo, String fecha) {
        this.saldo = saldo;
        this.fecha = fecha;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public String toString() {
        return "El saldo disponible es: " + getSaldo() + "\n" +
                "Fecha del ultimo movimiento: " + getFecha() ;
    }
}
