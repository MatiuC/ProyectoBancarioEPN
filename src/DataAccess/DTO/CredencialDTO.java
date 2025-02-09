package DataAccess.DTO;

public class CredencialDTO {
    private Integer idCredenciales;
    private Integer idPersona;
    private String usuario;
    private String pass;
    private String fechaCreacion;
    private String fechaModificacion;
    private String estado;

    // Constructor vacío
    public CredencialDTO() {}

    // Constructor sin ID (para inserción)
    public CredencialDTO(Integer idPersona, String usuario, String pass, String fechaCreacion, String fechaModificacion, String estado) {
        this.idPersona = idPersona;
        this.usuario = usuario;
        this.pass = pass;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado;
    }

    // Constructor completo (para consulta)
    public CredencialDTO(Integer idCredenciales, Integer idPersona, String usuario, String pass, String fechaCreacion, String fechaModificacion, String estado) {
        this.idCredenciales = idCredenciales;
        this.idPersona = idPersona;
        this.usuario = usuario;
        this.pass = pass;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado;
    }

    // Getters y Setters
    public Integer getIdCredenciales() { return idCredenciales; }
    public void setIdCredenciales(Integer idCredenciales) { this.idCredenciales = idCredenciales; }

    public Integer getIdPersona() { return idPersona; }
    public void setIdPersona(Integer idPersona) { this.idPersona = idPersona; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "CredencialesDTO { idCredenciales=" + idCredenciales +
                ", idPersona=" + idPersona +
                ", usuario='" + usuario + "'" +
                ", pass='" + pass + "'" +
                ", fechaCreacion='" + fechaCreacion + "'" +
                ", fechaModificacion='" + fechaModificacion + "'" +
                ", estado='" + estado + "'" +
                " }";
    }
}
