package DataAccess.DTO;

public class TransaccionDTO {
    private Integer idTransaccion;
    private Integer origen;
    private Integer destino;
    private Double monto;
    private String tipoTransaccion;
    private String fecha;
    private String hora;
    private String descripcion;
    private String fechaCreacion;
    private String fechaModificacion;
    private Boolean activo;
    private String nombreOrigen;
    private String nombreDestino;

    // Constructor vacío
    public TransaccionDTO() {}

    // Constructor sin ID (para inserción)
    public TransaccionDTO(Integer origen, Integer destino, Double monto, String tipoTransaccion, String fecha, String hora, String descripcion, String fechaCreacion, String fechaModificacion, Boolean activo) {
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
    }

    //Contructor para la vista de transacciones
    public TransaccionDTO(Integer idTransaccion, Integer origen, Integer destino, Double monto, String tipoTransaccion, String fecha) {
        this.idTransaccion = idTransaccion;
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = fecha;
    }


    // Constructor completo (para consulta)
    public TransaccionDTO(Integer idTransaccion, Integer origen, Integer destino, Double monto, String tipoTransaccion, String fecha, String hora, String descripcion, String fechaCreacion, String fechaModificacion, Boolean activo) {
        this.idTransaccion = idTransaccion;
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdTransaccion() { return idTransaccion; }
    public void setIdTransaccion(Integer idTransaccion) { this.idTransaccion = idTransaccion; }

    public Integer getOrigen() { return origen; }
    public void setOrigen(Integer origen) { this.origen = origen; }

    public Integer getDestino() { return destino; }
    public void setDestino(Integer destino) { this.destino = destino; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public String getNombreOrigen() { return nombreOrigen; }
    public void setNombreOrigen(String nombreOrigen) { this.nombreOrigen = nombreOrigen; }

    public String getNombreDestino() { return nombreDestino; }
    public void setNombreDestino(String nombreDestino) { this.nombreDestino = nombreDestino; }

    @Override
    public String toString() {
        return "TransaccionDTO { idTransaccion=" + idTransaccion +
                ", origen=" + origen +
                ", destino=" + destino +
                ", monto=" + monto +
                ", tipoTransaccion='" + tipoTransaccion + "'" +
                ", fecha='" + fecha + "'" +
                ", hora='" + hora + "'" +
                ", descripcion='" + descripcion + "'" +
                ", fechaCreacion='" + fechaCreacion + "'" +
                ", fechaModificacion='" + fechaModificacion + "'" +
                ", activo=" + activo +
                " }";
    }
}
