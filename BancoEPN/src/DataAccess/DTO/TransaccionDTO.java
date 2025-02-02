package DataAccess.DTO;

public class TransaccionDTO {
    private Integer transaccionId;
    private Integer origenTarjetaId;
    private Integer destinoTarjetaId;
    private Double monto;
    private String tipoTransaccion;
    private String fechaTransaccion;
    private String fechaCreacion;
    private String fechaModificacion;
    private Boolean activo;

    // Constructor vacío
    public TransaccionDTO() {}

    // Constructor sin ID (para inserción)
    public TransaccionDTO(Integer origenTarjetaId, Integer destinoTarjetaId, Double monto, String tipoTransaccion, String fechaTransaccion, String fechaCreacion, String fechaModificacion, Boolean activo) {
        this.origenTarjetaId = origenTarjetaId;
        this.destinoTarjetaId = destinoTarjetaId;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.fechaTransaccion = fechaTransaccion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
    }

    // Constructor completo (para consulta)
    public TransaccionDTO(Integer transaccionId, Integer origenTarjetaId, Integer destinoTarjetaId, Double monto, String tipoTransaccion, String fechaTransaccion, String fechaCreacion, String fechaModificacion, Boolean activo) {
        this.transaccionId = transaccionId;
        this.origenTarjetaId = origenTarjetaId;
        this.destinoTarjetaId = destinoTarjetaId;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.fechaTransaccion = fechaTransaccion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getTransaccionId() { return transaccionId; }
    public void setTransaccionId(Integer transaccionId) { this.transaccionId = transaccionId; }

    public Integer getOrigenTarjetaId() { return origenTarjetaId; }
    public void setOrigenTarjetaId(Integer origenTarjetaId) { this.origenTarjetaId = origenTarjetaId; }

    public Integer getDestinoTarjetaId() { return destinoTarjetaId; }
    public void setDestinoTarjetaId(Integer destinoTarjetaId) { this.destinoTarjetaId = destinoTarjetaId; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }

    public String getFechaTransaccion() { return fechaTransaccion; }
    public void setFechaTransaccion(String fechaTransaccion) { this.fechaTransaccion = fechaTransaccion; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "TransaccionDTO { transaccionId=" + transaccionId +
                ", origenTarjetaId=" + origenTarjetaId +
                ", destinoTarjetaId=" + destinoTarjetaId +
                ", monto=" + monto +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", fechaTransaccion='" + fechaTransaccion + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", fechaModificacion='" + fechaModificacion + '\'' +
                ", activo=" + activo +
                " }";
    }
}
