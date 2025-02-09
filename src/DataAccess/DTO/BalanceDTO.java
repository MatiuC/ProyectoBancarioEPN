package DataAccess.DTO;


public class BalanceDTO {
private Integer balance_id; 
private Integer persona_id; 
private Double balance_to; 
private String fecha_act;
public BalanceDTO(){}
public BalanceDTO (Integer balance_id, Integer persona_id, Double balance_to, String fecha_act ){
    this.balance_id = balance_id;
    this.persona_id = persona_id;
    this.balance_to = balance_to;
    this.fecha_act = fecha_act; 
}


public Integer getBalance_id() {
    return balance_id;
}
public void setBalance_id(Integer balance_id) {
    this.balance_id = balance_id;
}
public Integer getPersona_id() {
    return persona_id;
}
public void setPersona_id(Integer persona_id) {
    this.persona_id = persona_id;
}
public Double getBalance_to() {
    return balance_to;
}
public void setBalance_to(Double balance_to) {
    this.balance_to = balance_to;
}
public String getFecha_act() {
    return fecha_act;
}
public void setFecha_act(String fecha_act) {
    this.fecha_act = fecha_act;
} 



}