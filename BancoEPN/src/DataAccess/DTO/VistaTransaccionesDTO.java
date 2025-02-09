package DataAccess.DTO;

public class VistaTransaccionesDTO {
    private Integer transacion_id;
    private Integer origen_tarjeta_id;
    private Integer destino_tarjeta_id;
    private Double monto;
    private String tipo_transaccion;
    private String fecha_transaccion;

    public VistaTransaccionesDTO() {
    }

    public VistaTransaccionesDTO(Integer transacion_id, Integer origen_tarjeta_id, Integer destino_tarjeta_id, Double monto, String tipo_transaccion, String Fecha_transaccion) {
        this.transacion_id = transacion_id;
        this.origen_tarjeta_id = origen_tarjeta_id;
        this.destino_tarjeta_id = destino_tarjeta_id;
        this.monto = monto;
        this.tipo_transaccion = tipo_transaccion;
        this.fecha_transaccion = Fecha_transaccion;
    }


    public Integer getTransacion_id () {
        return transacion_id;
    }

    public void setTransacion_id (Integer transacion_id) {
        this.transacion_id = transacion_id;
    }

    public Integer getOrigen_tarjeta_id () {
        return origen_tarjeta_id;
    }

    public void setOrigen_tarjeta_id (Integer origen_tarjeta_id) {
        this.origen_tarjeta_id = origen_tarjeta_id;
    }

    public Integer getDestino_tarjeta_id () {
        return destino_tarjeta_id;
    }

    public void setDestino_tarjeta_id (Integer destino_tarjeta_id) {
        this.destino_tarjeta_id = destino_tarjeta_id;
    }

    public Double getMonto () {
        return monto;
    }

    public void setMonto (Double monto) {
        this.monto = monto;
    }

    public String getTipo_transaccion () {
        return tipo_transaccion;
    }

    public void setTipo_transaccion (String tipo_transaccion) {
        this.tipo_transaccion = tipo_transaccion;
    }

    public String getFecha_transaccion () {
        return fecha_transaccion;
    }

    public void setFecha_transaccion (String fecha_transaccion) {
        this.fecha_transaccion = fecha_transaccion;
    }

    @Override
    public String toString() {
        return "VistaTransacciones{" + "transacion_id=" + transacion_id + 
        ", origen_tarjeta_id="
         + origen_tarjeta_id +
          ", destino_tarjeta_id="
           + destino_tarjeta_id + 
           ", monto="
            + monto +
             ", tipo_transaccion="
              + tipo_transaccion + 
              ", fecha_transaccion="
               + fecha_transaccion + '}'; 
    }
    
}
