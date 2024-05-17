package com.backend.repository.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

public class OdontologoDao implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDao.class);
    List<Odontologo> odontologos = new ArrayList<>();
    @Override
    public Odontologo registrar(Odontologo odontologo) {

        Odontologo odontologoAgregado = null;

        try {
            odontologo.setId((long) odontologos.size() + 1);
            odontologos.add(odontologo);
            odontologoAgregado = odontologos.get(odontologos.size()-1);
            LOGGER.info("Odontologo agregado: " + odontologo);

        } catch (Exception e) {
            LOGGER.error("Error al guardar odontologo",e);
        } 
        
        return odontologoAgregado;
    }

    @Override
    public List<Odontologo> listarTodos() {

        List<Odontologo> odontologosAgregados = new ArrayList<>();

        try {
            odontologosAgregados.addAll(odontologos);
            LOGGER.info("Odontologos consultados... ");

        } catch (Exception e) {
            LOGGER.error("Ocurrio un errror al listar los odontologos: " + e.getMessage());
            e.printStackTrace();
        }

        LOGGER.info("Listado de todos los odontologos: " + odontologos);
        return odontologosAgregados;

    }
}
