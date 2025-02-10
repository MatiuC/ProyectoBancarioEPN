package BussinesLogic.ApiRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import DataAccess.DTO.PersonaDTO;




public class GetDatosCedula {

    public PersonaDTO sendPostRequest(String cedula) {
        String targetUrl = "https://si.secap.gob.ec/sisecap/logeo_web/json/busca_persona_registro_civil.php";
        
        // Crear los datos para enviar
        Map<String, String> postData = new HashMap<>();
        postData.put("documento", cedula);
        postData.put("tipo", "1");

        try {
            URL url = new URL(targetUrl);
            
            // Abrir la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
    
            // Enviar los datos en el cuerpo de la solicitud
            StringBuilder postParams = new StringBuilder();
            for (Map.Entry<String, String> entry : postData.entrySet()) {
                if (postParams.length() > 0) {
                    postParams.append("&");
                }
                postParams.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue());
            }

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = postParams.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer la respuesta
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                // Extraer datos del JSON manualmente
                String jsonStr = response.toString();
                if (jsonStr.contains("nombres") && jsonStr.contains("apellidos")) {
                    PersonaDTO persona = new PersonaDTO();
                    persona.setCedula(cedula);
                    
                    // Extraer los campos necesarios
                    persona.setNombre(extractJsonValue(jsonStr, "nombres"));
                    persona.setApellido(extractJsonValue(jsonStr, "apellidos"));
                    persona.setSexo(extractJsonValue(jsonStr, "sexo"));
                    persona.setEstado_civil(extractJsonValue(jsonStr, "estadoCivil"));
                    String fechaNacStr = extractJsonValue(jsonStr, "fechaNacimiento");
                    try {
                        if (fechaNacStr != null && !fechaNacStr.isEmpty()) {
                            java.sql.Date fechaNac = java.sql.Date.valueOf(fechaNacStr);
                            persona.setFecha_nacimiento(fechaNac);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al parsear la fecha: " + fechaNacStr);
                        persona.setFecha_nacimiento(null);
                    }
                    persona.setCiudad(extractJsonValue(jsonStr, "provDomicilio"));
                    String edadStr = extractJsonValue(jsonStr, "edad");
                    if (edadStr.endsWith(",")) {
                        edadStr = edadStr.substring(0, edadStr.length() - 1);
                    }

                    persona.setEdad(edadStr);

                    return persona;
                }
            }
                
        } catch (Exception e) {
            System.out.println("Error al enviar petición: " + e.getMessage());
        }
        return null;
    }

    private String extractJsonValue(String json, String key) {
        try {
            key = "\"" + key + "\"";
            int keyIndex = json.indexOf(key);
            if (keyIndex == -1) return "";

            int valueStart = json.indexOf(":", keyIndex) + 1;
            while (valueStart < json.length() && 
                   (json.charAt(valueStart) == ' ' || json.charAt(valueStart) == '"')) {
                valueStart++;
            }

            int valueEnd = json.indexOf("\"", valueStart);
            if (valueEnd == -1) {
                valueEnd = json.indexOf(",", valueStart);
                if (valueEnd == -1) {
                    valueEnd = json.indexOf("}", valueStart);
                }
            }

            if (valueEnd > valueStart) {
                String value = json.substring(valueStart, valueEnd).trim();
                return value.replace("\"", "");
            }
        } catch (Exception e) {
            System.out.println("Error extrayendo " + key + ": " + e.getMessage());
        }
        return "";
    }
}
