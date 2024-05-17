package com.backend.repository.impl;

import com.backend.dbconnection.H2Connection;
import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
    @Override
    public Odontologo registrar(Odontologo odontologo) {

        Connection connection = null;
        Odontologo odontologoRegistrado = null;

        try{

            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            String queryInsert = "INSERT INTO ODONTOLOGO (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.execute();

            odontologoRegistrado = new Odontologo(odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()) {
                odontologoRegistrado.setId(resultSet.getLong("id"));
            }

            connection.commit();
            LOGGER.info("Se ha registrado el odontologo: " + odontologoRegistrado);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Ha ocurrido un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }


        return odontologoRegistrado;
    }

    @Override
    public List<Odontologo> listarTodos() {

        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = H2Connection.getConnection();
            String query = "SELECT * FROM ODONTOLOGO";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                Odontologo odontologo = new Odontologo(
                                    resultSet.getLong(1),
                                    resultSet.getInt(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4));
                odontologos.add(odontologo);
            }


        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {

            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        LOGGER.info("Listado de todos los odontologos: " + odontologos);
        return odontologos;
    }
}
