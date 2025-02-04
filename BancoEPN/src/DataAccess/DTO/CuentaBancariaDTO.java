package DataAccess.DTO;

public class CuentaBancariaDTO {
    private Integer id_cuentabancaria;
    private String numero_cuenta;
    private Integer id_persona;
    private Float saldo;
    private String fecha_creacion;
    private String fecha_modificacion;
    private String estado;


    public CuentaBancariaDTO() {}

    public CuentaBancariaDTO(Integer id_cuentabancaria, String numero_cuenta, Integer id_persona, Float saldo, String fecha_creacion, String fecha_modificacion, String estado) {
        this.id_cuentabancaria = id_cuentabancaria;
        this.numero_cuenta = numero_cuenta;
        this.id_persona = id_persona;
        this.saldo = saldo;
        this.fecha_creacion = fecha_creacion;
        this.fecha_modificacion = fecha_modificacion;
        this.estado = estado;
    }

    public Integer getId_cuentabancaria() {
        return id_cuentabancaria;
    }

    public void setId_cuentabancaria(Integer id_cuentabancaria) {
        this.id_cuentabancaria = id_cuentabancaria;
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
        return "CuentaBancariaDTO{" +
                "id_cuentabancaria=" + id_cuentabancaria +
                ", numero_cuenta='" + numero_cuenta + '\'' +
                ", id_persona=" + id_persona +
                ", saldo=" + saldo +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                ", fecha_modificacion='" + fecha_modificacion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
