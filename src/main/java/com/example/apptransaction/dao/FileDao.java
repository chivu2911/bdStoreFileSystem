package com.example.apptransaction.dao;

import com.example.apptransaction.dto.DatosDto;
import com.example.apptransaction.fileUtils.FileOperations;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.StringReader;
import java.util.UUID;

@Repository
public class FileDao implements IFileDao {

    private static final Logger LOG = LoggerFactory.getLogger(FileDao.class);
    @Autowired
    private Gson gson;

    @Override
    public void add(String userId, String transactionJson) {
        LOG.info("transactionjson {}", transactionJson);
        JsonReader reader = new JsonReader(new StringReader(transactionJson));
        reader.setLenient(true);
        DatosDto datosDto = gson.fromJson(reader, DatosDto.class);
        datosDto.setTransaction_id(UUID.randomUUID().toString());
        datosDto.setUser_id(Integer.valueOf(userId));
        String json = gson.toJson(datosDto);
        FileOperations.createArchivo(json);
        LOG.info(json);
    }

    @Override
    public String show(String userId, String transactionId) {
        LOG.info("Show data:");
        return FileOperations.show(userId, transactionId);
    }

    @Override
    public String list(String userId) {
        return gson.toJson(FileOperations.getAll(userId));
    }

    @Override
    public String sum(String userId) {
        return FileOperations.sum(userId);
    }
}
