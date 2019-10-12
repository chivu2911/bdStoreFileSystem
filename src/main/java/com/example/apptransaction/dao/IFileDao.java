package com.example.apptransaction.dao;

public interface IFileDao {

    void add(String userId, String transactionJson);
    String show(String userId, String transactionId);
    String list(String userId);
    String sum(String userId);
}
