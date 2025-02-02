package DataAccess.DTO;

public class TarjetaDTO {
    private Integer tarjetaId;
    private Integer personaId;
    private String numeroTarjeta;
    private String fechaExpiracion;
    private String pin;
    private Double saldo;
    private String estado;
    private String fechaCreacion;
    private String fechaModificacion;
    private Boolean activo;

    public TarjetaDTO() {}

    public TarjetaDTO(Integer personaId, String numeroTarjeta, String fechaExpiracion, String pin, Double saldo, String estado, String fechaCreacion, String fechaModificacion, Boolean activo) {
        this.personaId = personaId;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.pin = pin;
        this.saldo = saldo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
    }

    public TarjetaDTO(Integer tarjetaId, Integer personaId, String numeroTarjeta, String fechaExpiracion, String pin, Double saldo, String estado, String fechaCreacion, String fechaModificacion, Boolean activo) {
        this.tarjetaId = tarjetaId;
        this.personaId = personaId;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.pin = pin;
        this.saldo = saldo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
    }

    public Integer getTarjetaId() { return tarjetaId; }
    public void setTarjetaId(Integer tarjetaId) { this.tarjetaId = tarjetaId; }

    public Integer getPersonaId() { return personaId; }
    public void setPersonaId(Integer personaId) { this.personaId = personaId; }

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    public String getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(String fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }

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
        return "TarjetaDTO {"+ 
                "tarjetaId=" + tarjetaId + 
                ", personaId=" + personaId + 
                ", numeroTarjeta='" + numeroTarjeta + 
                "', fechaExpiracion='" + fechaExpiracion + 
                "', saldo=" + saldo + 
                ", estado='" + estado + 
                "', fechaCreacion='" + fechaCreacion + 
                "', fechaModificacion='" + fechaModificacion + 
                "', activo=" + activo + " }";
    }
}
