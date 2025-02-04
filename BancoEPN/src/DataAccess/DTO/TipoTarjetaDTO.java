package DataAccess.DTO;

public class TipoTarjetaDTO {
    private Integer id_tipo_tarjeta; 
    private String nombre_tipo_tarjeta;
    private String fechaCreacion; 
    private String fechaModificacion;
    private String estado; 

    

    public TipoTarjetaDTO(){  }

    public TipoTarjetaDTO( Integer id_tipo_tarjeta, String nombre_tipo_tarjeta, String fechaCreacion, String fechaModificacion, String estado){   
           this.id_tipo_tarjeta = id_tipo_tarjeta;
           this.nombre_tipo_tarjeta = nombre_tipo_tarjeta; 
           this.fechaCreacion = fechaCreacion;
           this.fechaModificacion = fechaModificacion; 
    }

    public Integer getId_tipo_tarjeta() {
        return id_tipo_tarjeta;
    }
    public void setId_tipo_tarjeta(Integer id_tipo_tarjeta) {
        this.id_tipo_tarjeta = id_tipo_tarjeta;
    }
    public String getNombre_tipo_tarjeta() {
        return nombre_tipo_tarjeta;
    }
    public void setNombre_tipo_tarjeta(String nombre_tipo_tarjeta) {
        this.nombre_tipo_tarjeta = nombre_tipo_tarjeta;
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
        return "TipoTarjetaDTO {"+ 
                " id_tipo_tarjeta=" +  id_tipo_tarjeta + 
                ", nombre_tipo_tarjeta=" + nombre_tipo_tarjeta + 
                "', fechaCreacion =" + fechaCreacion +
                "', fechaModificacion =" + fechaModificacion +
                "', estado =" + estado + " }";
              
}
}