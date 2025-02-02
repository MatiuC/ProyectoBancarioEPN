package DataAccess.DTO;

public class VistaSesionActivaDTO {
    private Integer persona_id;
    private String nombre;
    private String apellido;
    private String fecha_inicio;
    private String estado;

    public VistaSesionActivaDTO() {

    }

    public VistaSesionActivaDTO(Integer persona_id, String nombre, String apellido, String fecha_inicio, String estado) {
        this.persona_id = persona_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_inicio = fecha_inicio;
        this.estado = estado;
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

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "VistaSesionActiva{" + "persona_id=" + persona_id + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha_inicio=" + fecha_inicio + ", estado=" + estado + '}';
    }
}
