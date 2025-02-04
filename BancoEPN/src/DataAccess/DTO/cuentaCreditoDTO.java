package DataAccess.DTO;

public class cuentaCreditoDTO {
    private Integer id_cuentaCredito;
    private String numero_cuenta;
    private Integer id_persona;
    private Float saldo;
    private Float limite_credito;
    private String fecha_creacion;
    private String fecha_modificacion;
    private String estado;

    public cuentaCreditoDTO() {}

    public cuentaCreditoDTO(Integer id_cuentaCredito, String numero_cuenta, Integer id_persona, Float saldo, Float limite_credito, String fecha_creacion, String fecha_modificacion, String estado) {
        this.id_cuentaCredito = id_cuentaCredito;
        this.numero_cuenta = numero_cuenta;
        this.id_persona = id_persona;
        this.saldo = saldo;
        this.limite_credito = limite_credito;
        this.fecha_creacion = fecha_creacion;
        this.fecha_modificacion = fecha_modificacion;
        this.estado = estado;
    }

    public Integer getId_cuentaCredito() {
        return id_cuentaCredito;
    }

    public void setId_cuentaCredito(Integer id_cuentaCredito) {
        this.id_cuentaCredito = id_cuentaCredito;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Float getLimite_credito() {
        return limite_credito;
    }

    public void setLimite_credito(Float limite_credito) {
        this.limite_credito = limite_credito;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "cuentaCreditoDTO{" +
                "id_cuentaCredito=" + id_cuentaCredito +
                ", numero_cuenta='" + numero_cuenta + '\'' +
                ", id_persona=" + id_persona +
                ", saldo=" + saldo +
                ", limite_credito=" + limite_credito +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                ", fecha_modificacion='" + fecha_modificacion + '\'' +
                ", estado=" + estado +
                '}';
    }
    
}
