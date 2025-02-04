package DataAccess.DTO;

public class FranquiciaDTO {
    private Integer id_franquicia;
    private String nombre_franquicia;
    private String fechaCreacion; 
    private String fechaModificacion;
    private String estado;

    public FranquiciaDTO (){   }

    public FranquiciaDTO(Integer id_franquicia, String nombre_franquicia, String fechaCreacion, String fechaModificacion, String estado){
        this.id_franquicia = id_franquicia;
        this.nombre_franquicia = nombre_franquicia;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado; 
    }


    public Integer getId_franquicia() {
        return id_franquicia;
    }
    public void setId_franquicia(Integer id_franquicia) {
        this.id_franquicia = id_franquicia;
    }
    public String getNombre_franquicia() {
        return nombre_franquicia;
    }
    public void setNombre_franquicia(String nombre_franquicia) {
        this.nombre_franquicia = nombre_franquicia;
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
                " id_franquicia =" +  id_franquicia + 
                ", nombre_franquicia=" + nombre_franquicia + 
                "', fechaCreacion =" + fechaCreacion +
                "', fechaModificacion =" + fechaModificacion +
                "', estado =" + estado + " }";
              
}
}
