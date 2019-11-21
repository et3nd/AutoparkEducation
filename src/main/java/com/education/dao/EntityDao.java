package com.education.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

abstract class EntityDao {
    private static final Logger log = LoggerFactory.getLogger(EntityDao.class);

    String getInitializationScript(InputStream stream) {
        StringBuilder sqlScript = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while (reader.ready()) {
                sqlScript.append(reader.readLine());
            }
            return sqlScript.toString();
        } catch (IOException e) {
            log.error("Error: ", e);
            return "";
        }
    }
}
