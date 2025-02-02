package DataAccess.DTO;

public class SesionDTO {
    private Integer sesionId;
    private Integer personaId;
    private String usuario;
    private String contrasenia;
    private String fechaInicio;
    private String fechaFin;
    private String ipOrigen;
    private String estado;
    private String fechaCreacion;
    private String fechaModificacion;
    private Boolean activo;

    public SesionDTO() {}

    public SesionDTO(Integer personaId, String usuario, String contrasenia, String fechaInicio, String fechaFin, String ipOrigen, String estado, String fechaCreacion, String fechaModificacion, Boolean activo) {
        this.personaId = personaId;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ipOrigen = ipOrigen;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
    }

    public SesionDTO(Integer sesionId, Integer personaId, String usuario, String contrasenia, String fechaInicio, String fechaFin, String ipOrigen, String estado, String fechaCreacion, String fechaModificacion, Boolean activo) {
        this.sesionId = sesionId;
        this.personaId = personaId;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ipOrigen = ipOrigen;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getSesionId() { return sesionId; }
    public void setSesionId(Integer sesionId) { this.sesionId = sesionId; }

    public Integer getPersonaId() { return personaId; }
    public void setPersonaId(Integer personaId) { this.personaId = personaId; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public String getIpOrigen() { return ipOrigen; }
    public void setIpOrigen(String ipOrigen) { this.ipOrigen = ipOrigen; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "SesionDTO { sesionId=" + sesionId +
                ", personaId=" + personaId +
                ", usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", ipOrigen='" + ipOrigen + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", fechaModificacion='" + fechaModificacion + '\'' +
                ", activo=" + activo +
                " }";
    }
}
