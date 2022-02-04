package co.com.axelis.axelisBack.models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import co.com.axelis.axelisBack.enumeration.Seccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publicaciones")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private Seccion seccion;

    @NotEmpty(message = "El título no puede ir vacío")
    private String titulo;

    @NotEmpty(message = "La descripción no puede ir vacía")
    private String descripcion;

    @Temporal(TemporalType.DATE)
    private Calendar fecha;
    
    private Long id_autor;
}