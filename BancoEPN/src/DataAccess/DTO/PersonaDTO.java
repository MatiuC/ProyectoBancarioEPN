package DataAccess.DTO;

public class PersonaDTO {
private Integer persona_id; 
private String nombre;
private String apellido; 
private String fecha_nacimiento;
private String direccion;
private String email;
private String telefono;
private boolean estado;



public PersonaDTO(){ }

public PersonaDTO(Integer persona_id, String nombre, String apellido, String fecha_nacimiento, String direccion, String email, String telefono, Boolean estado){
this.persona_id = persona_id;
this.nombre = nombre; 
this.apellido = apellido; 
this.fecha_nacimiento = fecha_nacimiento; 
this.direccion = direccion;
this.email = email;
this.telefono = telefono;
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
public String getFecha_nacimiento() {
    return fecha_nacimiento;
}
public void setFecha_nacimiento(String fecha_nacimiento) {
    this.fecha_nacimiento = fecha_nacimiento;
}
public String getDireccion() {
    return direccion;
}
public void setDireccion(String direccion) {
    this.direccion = direccion;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getTelefono() {
    return telefono;
}
public void setTelefono(String telefono) {
    this.telefono = telefono;
}
public boolean isEstado() {
    return estado;
}

public void setEstado(boolean estado) {
    this.estado = estado;
}


}



