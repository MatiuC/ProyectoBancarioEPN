package BussinesLogic.Entities.Agente;

import BussinesLogic.BLFactory;
import DataAccess.DAO.PersonaDAO;
import DataAccess.DTO.PersonaDTO;

public class EditarEliminarPersona {
 // Método para editar (actualizar) los datos de la persona
    public boolean editarPersona(PersonaDTO personaDTO) throws Exception {
        try {
            // Crear una instancia del DAO usando la fábrica de lógica de negocio
            BLFactory<PersonaDTO> blFactory = new BLFactory<>(() -> {
                try {
                    return new PersonaDAO();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            
            // Llamamos al método update de la lógica de negocio para actualizar la persona
            return blFactory.upd(personaDTO);
        } catch (Exception e) {
            // Manejo de excepciones, por ejemplo, logging o manejo de errores
            System.out.println("Error al editar la persona: " + e.getMessage());
            return false;
        }
    }
    

    //Eliminar persona

     // Método para eliminar (desactivar) la persona
     public boolean eliminarPersona(Integer idPersona) throws Exception {
        try {
            // Crear una instancia del DAO usando la fábrica de lógica de negocio
            BLFactory<PersonaDTO> blFactory = new BLFactory<>(() -> {
                try {
                    return new PersonaDAO();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            // Llamamos al método del de la lógica de negocio para eliminar la persona
            return blFactory.del(idPersona);
        } catch (Exception e) {
            // Manejo de excepciones, por ejemplo, logging o manejo de errores
            System.out.println("Error al eliminar la persona: " + e.getMessage());
            return false;
        }
    }



}
