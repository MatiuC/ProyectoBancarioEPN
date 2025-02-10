package DataAccess.DTO;
import java.sql.Date;

public class PersonaDTO {
private Integer persona_id; 
private String cedula;
private String nombre;
private String apellido;

private String sexo;

private String estado_civil;
private String ciudad;
private String edad; 
private Date fecha_nacimiento;
private String direccion;
private String correo;
private String telefono;
private String fechaCreacion;
private String fechaModificacion;
private Integer id_rol;


private String estado;

    public PersonaDTO() {}

    public PersonaDTO(Integer persona_id, String cedula, String nombre,
    String apellido, String sexo, String estado_civil, String ciudad,
    String edad, Date fecha_nacimiento, String direccion,
    String correo, String telefono, String fechaCreacion, String fechaModificacion, Integer id_rol, String estado) {
    this.persona_id = persona_id;
    this.cedula = cedula;
    this.nombre = nombre;
    this.apellido = apellido;
    this.sexo = sexo;
    this.estado_civil = estado_civil;
    this.ciudad = ciudad;
    this.edad = edad;
    this.fecha_nacimiento = fecha_nacimiento;
    this.direccion = direccion;
    this.correo = correo;
    this.telefono = telefono;
    this.fechaCreacion = fechaCreacion;
    this.fechaModificacion = fechaModificacion;
    this.id_rol = id_rol;
    this.estado = estado;
    }

    public Integer getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(Integer persona_id) {
        this.persona_id = persona_id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getcorreo() {
        return correo;
    }

    public void setcorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public void setRol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public Integer getRol() {
        return id_rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" + "persona_id=" + persona_id + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo + ", estado_civil=" + estado_civil + ", ciudad=" + ciudad + ", edad=" + edad + ", fecha_nacimiento=" + fecha_nacimiento + ", direccion=" + direccion + ", correo=" + correo + ", telefono=" + telefono + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", Rol=" + id_rol + ", estado=" + estado + '}';
    }

}