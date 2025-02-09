package BussinesLogic.Entities.Agente;

import BussinesLogic.ApiRequest.GetDatosCedula;

public class AgenteBL {

    String cedula = "2300302714";

    public void getDatosCedula(String cedula) {
        GetDatosCedula api = new GetDatosCedula();
        api.sendPostRequest(cedula);


    }
    
    public void reguser(){
        generaruser();

    }

    public void generaruser(){  
    }
    


}
