package DataAccess.DTO;

public class TipoTransaccionDTO {
    private Integer idTipoTransaccion;
    private String nombreTipoTransaccion;

    // Constructor vacío
    public TipoTransaccionDTO() {}

    // Constructor con parámetros
    public TipoTransaccionDTO(Integer idTipoTransaccion, String nombreTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
        this.nombreTipoTransaccion = nombreTipoTransaccion;
    }

    // Getters y Setters
    public Integer getIdTipoTransaccion() { return idTipoTransaccion; }
    public void setIdTipoTransaccion(Integer idTipoTransaccion) { this.idTipoTransaccion = idTipoTransaccion; }

    public String getNombreTipoTransaccion() { return nombreTipoTransaccion; }
    public void setNombreTipoTransaccion(String nombreTipoTransaccion) { this.nombreTipoTransaccion = nombreTipoTransaccion; }

    @Override
    public String toString() {
        return "TipoTransaccionDTO { idTipoTransaccion=" + idTipoTransaccion +
                ", nombreTipoTransaccion='" + nombreTipoTransaccion + "'" +
                " }";
    }
}
