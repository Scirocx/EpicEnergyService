package it.epicode.beservice.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.epicode.beservice.security.StringAttributeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String ragioneSociale;
	@Column(unique=true)
	private String partitaIva;
	@Column(name="tipo_cliente")
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	@Convert(converter = StringAttributeConverter.class)
	@Column(unique=true)
	private String email;
	@Column(unique=true)
	@Convert(converter = StringAttributeConverter.class)
	private String pec;
	private String telefono;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	@Convert(converter = StringAttributeConverter.class)
	private String emailContatto;
	@OneToOne
	private Indirizzo indirizzoSedeOperativa;
	@OneToOne
	private Indirizzo indirizzoSedeLegale;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private Double fatturatoAnnuale;
}
