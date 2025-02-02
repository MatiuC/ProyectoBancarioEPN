package DataAccess.DTO;

public class VistaBalanceDTO {
    private Integer persona_id;
    private String nombre;
    private String apellido;
    private String balance_total;

    public VistaBalanceDAO() {
    }

    public VistaBalanceDAO(Integer persona_id, String nombre, String apellido, String balance_total) {
        this.persona_id = persona_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.balance_total = balance_total;
    }

    public Integer getPersona_id() {
        return persona_id;
    }
    
    public void setPersona_id(Integer persona_id) {
        this.persona_id = persona_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getBalance_total() {
        return balance_total;
    }

    public void setBalance_total(String balance_total) {
        this.balance_total = balance_total;
    }

    @Override
    public String toString() {
        return "VistaBalanceDAO{" + "persona_id=" + persona_id + ", nombre=" + nombre + ", apellido=" + apellido + ", balance_total=" + balance_total + '}';
    }
}
