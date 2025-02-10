import DataAccess.DAO.TarjetaDAO;
import DataAccess.DTO.TarjetaDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import BussinesLogic.Entities.ATM.Retiro;

public class App {
    public static void main(String[] args) throws Exception {
        
        TarjetaDAO tarjetaDAO = new TarjetaDAO();
        TarjetaDTO tarjeta = tarjetaDAO.ReadBy("1234567890123456");
        System.out.println(tarjeta);    
        
    }  
}
