package DataAccess.DTO;

public class TarjetaDTO {
    private Integer Id_tarjeta; 
    private String numero_tarjeta; 
    private String fecha_expedicion; 
    private String fecha_vencimiento; 
    private String cvv; 
    private Integer tipo_tarjeta; 
    private Integer id_franquicia; 
    private String fechaCreacion; 
    private String fechaModificacion; 
    private String estado;
    private Integer Persona;
    private Integer id_cuentabancaria;

    public TarjetaDTO() {}

    public TarjetaDTO(Integer Id_tarjeta, String numero_tarjeta, String fecha_expedicion, String fecha_vencimiento, String cvv, Integer tipo_tarjeta, Integer id_franquicia, String fechaCreacion, String fechaModificacion, String estado, Integer Persona, Integer id_cuentabancaria) {
        this.Id_tarjeta = Id_tarjeta; 
        this.numero_tarjeta = numero_tarjeta; 
        this.fecha_expedicion = fecha_expedicion;
        this.fecha_vencimiento = fecha_vencimiento;
        this.cvv = cvv; 
        this.tipo_tarjeta = tipo_tarjeta; 
        this.id_franquicia = id_franquicia; 
        this.fechaCreacion = fechaCreacion; 
        this.fechaModificacion = fechaModificacion; 
        this.estado = estado; 
        this.Persona = Persona; 
        this.id_cuentabancaria = id_cuentabancaria; 
    }

    public Integer getId_tarjeta() { return Id_tarjeta; }
    public void setId_tarjeta(Integer id_tarjeta) { Id_tarjeta = id_tarjeta; }

    public String getNumero_tarjeta() { return numero_tarjeta; }
    public void setNumero_tarjeta(String numero_tarjeta) { this.numero_tarjeta = numero_tarjeta; }

    public String getFecha_expedicion() { return fecha_expedicion; }
    public void setFecha_expedicion(String fecha_expedicion) { this.fecha_expedicion = fecha_expedicion; }

    public String getFecha_vencimiento() { return fecha_vencimiento; }
    public void setFecha_vencimiento(String fecha_vencimiento) { this.fecha_vencimiento = fecha_vencimiento; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public Integer getTipo_tarjeta() { return tipo_tarjeta; }
    public void setTipo_tarjeta(Integer tipo_tarjeta) { this.tipo_tarjeta = tipo_tarjeta; }

    public Integer getId_franquicia() { return id_franquicia; }
    public void setId_franquicia(Integer id_franquicia) { this.id_franquicia = id_franquicia; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getPersona() { return Persona; }
    public void setPersona(Integer persona) { Persona = persona; }

    public Integer getId_cuentabancaria() { return id_cuentabancaria; }
    public void setId_cuentabancaria(Integer id_cuentabancaria) { this.id_cuentabancaria = id_cuentabancaria; }

    @Override
    public String toString() {
        return "TarjetaDTO { Id_tarjeta=" + Id_tarjeta + ", numero_tarjeta=" + numero_tarjeta + ", fecha_expedicion='" + fecha_expedicion + "', fecha_vencimiento='" + fecha_vencimiento + "', cvv=" + cvv + ", estado='" + estado + "', tipo_tarjeta=" + tipo_tarjeta + ", id_franquicia=" + id_franquicia + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", Persona=" + Persona + ", id_cuentabancaria=" + id_cuentabancaria + " }";
    }
}
