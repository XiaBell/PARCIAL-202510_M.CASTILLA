package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import java.util.List;
import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;

public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
	public PacienteEntity crearPaciente(PacienteEntity paciente) throws IllegalOperationException {

        String telefono = paciente.getTelefono();

        String primerosTresDigitos = telefono.substring(0, 3);

        if (telefono == null || telefono.isEmpty()) {
            throw new IllegalOperationException("El telefono del paciente no puede ser nulo o vacío");
        }

        if (telefono.length() < 11) {
            throw new IllegalOperationException("El telefono del paciente debe tener 11 dígitos");
        }

        if (primerosTresDigitos != "311" || primerosTresDigitos != "601"){
            throw new IllegalOperationException("El telefono del paciente debe empezar por 311 o 601");
        }

        

        return pacienteRepository.save(paciente);
    
}

    @Transactional  
    public void AsociarPacienteAcudiente (Long idPaciente, Long IdAcudiente) throws IllegalOperationException {
        PacienteEntity paciente = pacienteRepository.findById(idPaciente).get();
        PacienteEntity acudiente = pacienteRepository.findById(IdAcudiente).get();

        List<HistoriaClinicaEntity>historias = acudiente.getHistorias();

        if (historias.isEmpty()) {
            throw new IllegalOperationException("El acudiente debe tener al menos una historia clínica asociada"); 
        }

        if(paciente.getAcudiente() != null){ 
            throw new IllegalOperationException("El paciente ya tiene un acudiente asociado");
        }

        if (acudiente.getAcudiente() != null) {
            throw new IllegalOperationException("El acudiente no puede tener ya un acudiente asociado");
        }


        paciente.getAcudiente().add(paciente);
        
    }

}
