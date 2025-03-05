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
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.dse.parcialprueba.services.HistoriaClinicaService;
import co.edu.uniandes.dse.parcialprueba.services.PacienteService;


@DataJpaTest
@Transactional
@Import({ HistoriaClinicaService.class, PacienteService.class })
public class HistoriaClinicaServiceTest {


    @Autowired
    private HistoriaClinicaService historiaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();


    /**
     * Configuración inicial de la prueba.
     */
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }


    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from PacienteEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from HistoriaClinicaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        HistoriaClinicaEntity historia = factory.manufacturePojo(HistoriaClinicaEntity.class);
        entityManager.persist(historia);

        PacienteEntity paciente = factory.manufacturePojo(PacienteEntity.class);
        entityManager.persist(paciente);

        historia.setPaciente(paciente);

    }

    @Test
    void testCrearHistoriaCorrectamente() throws IllegalOperationException  {
        
        HistoriaClinicaEntity historia = factory.manufacturePojo(HistoriaClinicaEntity.class);
        PacienteEntity paciente = factory.manufacturePojo(PacienteEntity.class);
        historia.setPaciente(paciente);

        historiaService.crearHistoria(paciente.getId(), historia);
        
        assertNotNull(historia.getId());
        assertEquals(paciente.getId(), historia.getPaciente().getId());
    }

    @Test
    void crearHistoriaNum(){
        HistoriaClinicaEntity historia = factory.manufacturePojo(HistoriaClinicaEntity.class);
        PacienteEntity paciente = factory.manufacturePojo(PacienteEntity.class);
        historia.setPaciente(paciente);

    }
}
