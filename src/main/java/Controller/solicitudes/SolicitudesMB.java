/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.solicitudes;

import Controller.login.LoginMB;
import DAO.laboratorios.LaboratorioDAO;
import DAO.solicitudes.solicitudesDAO;
import Model.administracion.Encargado;
import Model.administracion.Persona;
import Model.equipos.Equipo;
import Model.laboratorios.Laboratorio;
import Model.solicitudes.Asignatura;
import Model.solicitudes.Carrera;
import Model.solicitudes.Curso;
import Model.solicitudes.Facultad;
import Model.solicitudes.PeriodoAcademico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ebert
 */
@ManagedBean
@ViewScoped
@Getter
@Setter
public class SolicitudesMB extends global.Mensajes implements Serializable {

    private boolean verifica;
    private LaboratorioDAO laboratorioDAO;
    private List<Facultad> listafacultades;
    private List<Carrera> listaCarrera;
    private List<Asignatura> listAsignatura;
    private List<Laboratorio> listaLaboratorio;
    private List<Curso> listaCurso;
    private List<Persona> listaPersona;
    private List<Persona> listaPersonaConfirmada;
    private List<Persona> listaPersonaAleterna;
    private List<PeriodoAcademico> listaPeriodoAcademico;

    private List<Equipo> listaEquipos;

    private Facultad facultad;
    private Asignatura asignatura;
    private PeriodoAcademico periodoAcademico;
    private Carrera carrera;
    private Curso curso;
    private Encargado encargado;
    private Persona persona;
    private Laboratorio laboratorio;
    private solicitudesDAO dao;
    int idFacultad = 0;
    int idLaboratorio = 0;
    LoginMB l;

    @PostConstruct
    public void init() {
        asignatura = new Asignatura();
        listaPersonaAleterna = new ArrayList<>();
        periodoAcademico = new PeriodoAcademico();
        listaPersonaConfirmada = new ArrayList<>();
        persona = new Persona();
        curso = new Curso();
        listaEquipos = new ArrayList<>();
        listarPeriodoAcademico();
    }

    public void listarFacultades() {
        try {
            dao = new solicitudesDAO();
            listafacultades = dao.getfacultades();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarPeriodoAcademico() {
        try {
            dao = new solicitudesDAO();
            listaPeriodoAcademico = new ArrayList<>();

            listaPeriodoAcademico = dao.listPeriodoAcademico();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarCareraByFacultadId() throws Exception {
        if (idFacultad == 0) {
            listaCarrera = null;
        }

        try {
            dao = new solicitudesDAO();
            listaCarrera = dao.listCarreraByFacultad(idFacultad);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarCursoByPeriodo() throws Exception {
        if (periodoAcademico.getIdPeriodoAcademico() == 0) {
            listaCarrera = null;
        }

        try {
            dao = new solicitudesDAO();
            listaCurso = new ArrayList<>();
            listaCurso = dao.listCursoByPeriodo(periodoAcademico.getIdPeriodoAcademico());
        } catch (Exception e) {
            throw e;
        }
    }

    public void generarCodLab() throws Exception {

        try {
            dao = new solicitudesDAO();
            listaLaboratorio = dao.getCodigoLab(idLaboratorio);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listaAsignatura(int post) throws Exception {

        try {
            dao = new solicitudesDAO();
            listAsignatura = dao.listMateriaDocente(post);
            System.out.println(asignatura);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listaAlumnoByProfesor(int idAsignatura, int idProfesor) throws Exception {

        try {
            dao = new solicitudesDAO();
            listaPersona = new ArrayList<>();
            if (idAsignatura == 0 || idProfesor == 0) {
                listaPersona = null;

            }
            listaPersona = dao.listaAlumnoByAsignaturaByProfesor(idAsignatura, idProfesor);
        } catch (Exception e) {
            throw e;
        }
    }

    public void addPersonaEstudiante(Persona persona) {

        if (persona.isVerifica() == true) {
            listaPersonaAleterna.add(new Persona(
                    persona.getIdPersona(),
                    persona.getNombre(),
                    persona.getApellido(),
                    persona.getDni(),
                    persona.getGenero()));

        } else {
            for (Persona lista : listaPersonaAleterna) {
                if (lista.getIdPersona() == persona.getIdPersona()) {
                    listaPersonaAleterna.remove(lista);
                }
            }
        }
        System.out.println(listaPersonaAleterna+"addPersonaEstudiante");
    }

    public void llenaProductoConfirmado() {
        for (Persona lista : listaPersonaAleterna) {
            if (duplicidadDatos(lista)) {
                mensajeDeAdvertencia("El estudiante ya se encuentra agregado");
            } else {
                listaPersonaConfirmada.add(lista);
            }
        }
        System.out.println(listaPersonaConfirmada+"llena producto confirmado");
    }

    public void deleteFila(Persona persona) {
        listaPersonaConfirmada.remove(persona);
    }

    public boolean duplicidadDatos(Persona producto) {
        boolean confirmacion = false;
        for (Persona lista : listaPersonaConfirmada) {
            if (lista.getIdPersona() == producto.getIdPersona()) {
                confirmacion = true;
            }
        }
        return confirmacion;
    }

}
