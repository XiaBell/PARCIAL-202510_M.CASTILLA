package co.edu.uniandes.dse.parcialprueba.repositories;
    
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;



public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
	
}
    
