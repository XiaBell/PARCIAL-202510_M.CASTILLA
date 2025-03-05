package co.edu.uniandes.dse.parcialprueba.services;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.dse.parcialprueba.services.HistoriaClinicaService;
import co.edu.uniandes.dse.parcialprueba.services.PacienteService;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;

@DataJpaTest
@Transactional
@Import({ HistoriaClinicaService.class, PacienteService.class })
public class PacienteServiceTest {


    @Autowired
    private HistoriaClinicaService historiaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TestEntityManager entityManager;


    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }


    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from PacienteEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from HistoriaClinicaEntity").executeUpdate();
    }

    private void insertData(){

        PacienteEntity paciente = factory.manufacturePojo(PacienteEntity.class);
        entityManager.persist(paciente);

        HistoriaClinicaEntity historia = factory.manufacturePojo(HistoriaClinicaEntity.class);
        List historias = new ArrayList<HistoriaClinicaEntity>();
        historias.add(historia);
        entityManager.persist(historia);
   
		for (int i = 0; i < 3; i++) {
			PacienteEntity entity = factory.manufacturePojo(PacienteEntity.class);
			entity.setHistorias(historias);

		}
	}

    @Test
    public void crearPacienteTest() throws IllegalOperationException {
        
        PacienteEntity newpaciente = factory.manufacturePojo(PacienteEntity.class);
		newpaciente.setHistoria(historiaService);
		pacienteService.crearPaciente(newpaciente);

    }

    public void AsociarPacienteAcudienteTest() throws IllegalOperationException {
        
    }





}
