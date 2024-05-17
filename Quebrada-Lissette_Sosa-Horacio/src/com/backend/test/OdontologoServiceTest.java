package com.backend.test;

import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDao;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.service.impl.OdontologoService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Test
    void deberiaRegistrarseUnOdontologoYObtenerElIdCorrespondienteEnH2(){

        //Arrange
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo(789,"LUISA", "FERNANDEZ");

        //Act
        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        //Assert
        assertNotNull(odontologoRegistrado.getId());

    }

    @Test
    void deberiaRetornarseUnaListaNoVaciaDeOdontologosEnH2() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }

    @Test
    void deberiaRegistrarseUnObjetoOdontologoYObtenerElIdCorrespondiente(){

        //Arrange
        odontologoService = new OdontologoService(new OdontologoDao());
        Odontologo odontologo = new Odontologo(789,"LAURA", "LOPEZ");

        //Act
        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        //Assert
        assertNotNull(odontologoRegistrado.getId());

    }

    @Test
    void deberiaRetornarseUnaListaVaciaDeArrayListDeOdontologos() {
        odontologoService = new OdontologoService(new OdontologoDao());
        assertTrue(odontologoService.listarOdontologos().isEmpty());
    }

}