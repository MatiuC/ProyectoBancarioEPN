package DataAccess.DTO;

public class EstadoCivilDTO {
    private Integer id_estado_civil;
    private String nombre_estado_civil;

    public EstadoCivilDTO() {}

    public EstadoCivilDTO(Integer id_estado_civil, String nombre_estado_civil) {
        this.id_estado_civil = id_estado_civil;
        this.nombre_estado_civil = nombre_estado_civil;
    }

    public Integer getId_estado_civil() {
        return id_estado_civil;
    }

    public void setId_estado_civil(Integer id_estado_civil) {
        this.id_estado_civil = id_estado_civil;
    }

    public String getNombre_estado_civil() {
        return nombre_estado_civil;
    }

    public void setNombre_estado_civil(String nombre_estado_civil) {
        this.nombre_estado_civil = nombre_estado_civil;
    }

    @Override
    public String toString() {
        return "EstadoCivilDTO{" + "id_estado_civil=" + id_estado_civil + ", nombre_estado_civil=" + nombre_estado_civil + '}';
    }
}