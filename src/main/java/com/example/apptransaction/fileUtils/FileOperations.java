package com.example.apptransaction.fileUtils;

import com.example.apptransaction.dto.DatosDto;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperations {
    private static final Logger LOG = LoggerFactory.getLogger(FileOperations.class);
    public static void createArchivo(String datos) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("bd.txt", true);
            BufferedWriter bfwriter = new BufferedWriter(writer);
            bfwriter.write(datos);
            bfwriter.write("\r\n");
            bfwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List getAll(String userId) {
        Gson gson = new Gson();
        File file = new File("bd.txt");
        List<DatosDto> lista= new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                JSONObject json = new JSONObject(linea);

                if(json.optString("user_id").equals(userId)) {
                    LOG.info(linea);
                    DatosDto dto= gson.fromJson(linea, DatosDto.class);
                    lista.add(dto);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LOG.info("lista:  {}", lista);
        return lista;
    }

    public static String show(String userId, String transactionId){
        File file = new File("bd.txt");
        Scanner scanner;
        String response = "";
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                JSONObject json = new JSONObject(linea);

                if(json.optString("user_id").equals(userId) &&
                        json.optString("transaction_id").equals(transactionId)) {
                    LOG.info(json.toString());
                    return json.toString();
                }
            }

            if("".equals(response)) {
                response = "Transaction not found";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LOG.info(response);
        return response;
    }

    public static String sum(String userId){
        File file = new File("bd.txt");
        Scanner scanner;
        Double sum = 0.0;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                JSONObject json = new JSONObject(linea);

                if(json.optString("user_id").equals(userId)) {
                    sum += json.optDouble("amount");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LOG.info("{}",sum);
        return sum.toString();
    }
}
