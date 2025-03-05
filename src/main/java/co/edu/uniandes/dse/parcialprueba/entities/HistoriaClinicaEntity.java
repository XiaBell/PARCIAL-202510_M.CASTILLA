package co.edu.uniandes.dse.parcialprueba.entities;
import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;



@Data
@Entity
public class HistoriaClinicaEntity extends BaseEntity {

	private String diagnostico;
	private String tratamiento;
	private Date fechaDeCreacion;


	@PodamExclude
	@ManyToOne
	private PacienteEntity paciente;
    
}

