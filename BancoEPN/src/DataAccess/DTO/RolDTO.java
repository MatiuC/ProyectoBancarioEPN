package DataAccess.DTO;

public class RolDTO {
    private Integer id_rol;
    private String nombre_rol;
    private String fechaCreacion;
    private String fechaModificacion;
    private String estado;

    public RolDTO() {}

    public RolDTO(Integer id_rol, String nombre_rol, String fechaCreacion, String fechaModificacion, String estado) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado;
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "RolDTO{" + "id_rol=" + id_rol + ", nombre_rol=" + nombre_rol + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", estado=" + estado + '}';
    }
}