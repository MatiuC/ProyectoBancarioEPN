package BussinesLogic.ApiRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GetDatosCedula {

    public void sendPostRequest(String cedula) {
    String targetUrl = "https://si.secap.gob.ec/sisecap/logeo_web/json/busca_persona_registro_civil.php";
        
        // Crear los datos para enviar
        Map<String, String> postData = new HashMap<>();
        postData.put("documento", cedula);
        postData.put("tipo", "1");

        try {
            @SuppressWarnings("deprecation")
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

            int status = connection.getResponseCode();
            System.out.println("Status: " + status);
            // Leer la respuesta en texto
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            System.out.println("Respuesta completa: " + response.toString());           
            int reasponseCode = connection.getResponseCode();
            System.out.println("Response code: " + reasponseCode);
            
        } catch (Exception e) {
            System.out.println("Error al enviar petición " + e.getMessage());
        }




    }
}
