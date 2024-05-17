package com.backend;

import com.backend.dbconnection.H2Connection;
import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDao;

public class Application {

    public static void main(String[] args) {
        H2Connection.crearTablas();
    }

}
