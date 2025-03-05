package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;

public class HistoriaClinicaService {



    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private HistoriaClinicaRepository historiaRepository;

    @Transactional
	public HistoriaClinicaEntity crearHistoria(Long idpaciente, HistoriaClinicaEntity historia) throws IllegalOperationException {

        PacienteEntity paciente = pacienteRepository.findById(idpaciente).get();

        if (paciente.getAcudiente() != null){

            String historia_original = historia.getDiagnostico();
            String historia_nueva = "HistoriaCompartida-"+ historia_original;
            historia.setDiagnostico(historia_nueva);

        }

        paciente.getHistorias().add(historia);
        return historiaRepository.save(historia);
    
}
}
