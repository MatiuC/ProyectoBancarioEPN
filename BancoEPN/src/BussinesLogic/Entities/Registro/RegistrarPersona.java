package BussinesLogic.Entities.Registro;

import DataAccess.DAO.*;
import DataAccess.DTO.*;
import BussinesLogic.BLFactory;
import BussinesLogic.ApiRequest.*;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.mail.MessagingException;

public class RegistrarPersona {

    private BLFactory<PersonaDTO> personaBL;
    private GetDatosCedula getDatosCedula;
    private GenerarCredenciales generador;
    private EnviarMail enviarCorreo;

    public RegistrarPersona() throws Exception {
            // Initialize components that don't throw checked exceptions first
            getDatosCedula = new GetDatosCedula();
            generador = new GenerarCredenciales();
            enviarCorreo = new EnviarMail();


    }

    public boolean registrarPersona(int id_persona, String nombre, String apellido, String edad, String correo) {
        try {
            // Guardar la persona y obtener el ID generado
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
            Date now = new Date();
            


                CredencialDTO credencial = generador.generarCredenciales(
                    id_persona,
                    nombre,
                    apellido,
                    edad,
                    correo
                );

                // Crear cuenta de ahorro
                GenerarCuenta generarCuenta = new GenerarCuenta();
                String numeroCuenta = generarCuenta.generarNumeroCuenta();

                // Guardar cuenta bancaria
                CuentaBancariaDTO cuentaBancaria = new CuentaBancariaDTO();
                cuentaBancaria.setNumero_cuenta(numeroCuenta);
                cuentaBancaria.setId_persona(id_persona);
                cuentaBancaria.setSaldo(15.0f);
                cuentaBancaria.setFecha_creacion(sdf.format(now));
                cuentaBancaria.setFecha_modificacion(sdf.format(now));
                cuentaBancaria.setEstado("A");
                

                CuentaBancariaDAO cuentaBancariaDAO = new CuentaBancariaDAO();
                cuentaBancariaDAO.create(cuentaBancaria);

                // Registrar bono de bienvenida
                TransaccionDTO transaccion = new TransaccionDTO();
                transaccion.setOrigen(1); // ID 1 para el banco 
                transaccion.setDestino(id_persona);
                transaccion.setMonto(15.0); 
                transaccion.setFecha(sdf.format(now));
                transaccion.setHora(sdfTime.format(now));
                transaccion.setDescripcion("Bono de bienvenida");
                transaccion.setTipoTransaccion("Deposito");
                transaccion.setActivo(true);
                
                TransaccionDAO transaccionDAO = new TransaccionDAO();
                transaccionDAO.create(transaccion);

                return true;
  
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

