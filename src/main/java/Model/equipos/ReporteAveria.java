/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.equipos;

import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author jonathan
 */
@Data
@NoArgsConstructor
public class ReporteAveria {

    private String facultad;
    private int idAveria;
    private Date fechaRegistro;
    private String prioridad;
    private String tecnico;
    private String areaEquipo;
    private String codigoEquipo;
    private String categoriaEquipo;
    private String descripcionEquipo;
    private String marcaEquipo;
    private String modeloEquipo;
    private String serieEquipo;
    private String estadoEquipo;
    private String tipoDanio;
    private String descriocionAveria;
    private String laboratorio;

}
