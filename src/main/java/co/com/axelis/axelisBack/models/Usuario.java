package co.com.axelis.axelisBack.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede ir vacío")
    private String nombre;

    @Column(unique = true)
    @NotEmpty(message = "El correo no puede ir vacío")
    private String correo;

    @NotEmpty(message = "La contraseña no puede ir vacía")
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Rol> roles = new ArrayList<>();

}
