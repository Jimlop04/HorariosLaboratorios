/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.solicitudes;

import Controller.login.LoginMB;
import Model.administracion.Encargado;
import Model.administracion.Persona;
import Model.equipos.Equipo;
import Model.laboratorios.Laboratorio;
import Model.solicitudes.Alumno;
import Model.solicitudes.Asignatura;
import Model.solicitudes.Carrera;
import Model.solicitudes.Curso;
import Model.solicitudes.Facultad;
import Model.solicitudes.HorarioLaboratorio;
import Model.solicitudes.PeriodoAcademico;
import Model.solicitudes.Practica;
import global.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ebert
 */
public class solicitudesDAO extends Conexion {

    public List<Facultad> getfacultades() {
        List<Facultad> facultades = new ArrayList<>();
        ResultSet resultSet;
        String sql = String.format("SELECT * FROM laboratorio.facultad");
        try {
            conectar();
            resultSet = ejecutarSql(sql);

            while (resultSet.next()) {
                facultades.add(new Facultad(
                        resultSet.getInt("id_facultad"),
                        resultSet.getString("nombre_facultad")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
        return facultades;
    }

    public List<Curso> listCursoByPeriodo(int idPeriodo) throws Exception {
        List<Curso> lista;
        ResultSet rs;
        try {
            conectar();
            String sql = "select c.id_curso, c.nombre_curso from laboratorio.periodo_academico pa inner join\n"
                    + "    laboratorio.periodo_curso pc\n"
                    + "        on pa.id_periodo_academico = pc.periodo_academico_id_periodo_academico\n"
                    + "inner join laboratorio.curso c on c.id_curso = pc.curso_id_curso\n"
                    + "where pa.id_periodo_academico =" + idPeriodo;
            PreparedStatement st = this.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Curso obj = new Curso();
                obj.setIdCurso(rs.getInt("id_curso"));
                obj.setNombreCurso(rs.getString("nombre_curso"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            desconectar();
        }
        return lista;
    }

    public List<Persona> listaAlumnoByAsignaturaByProfesor(int idAsignatura, int idPersona) throws Exception {
        List<Persona> lista;
        ResultSet rs;
        try {
            conectar();
            String sql = "select alno.id_alumno, per.nombre_persona,per.apellido_persona,per.dni_persona,per.genero_persona from  (Select ap.id_asignatura_profesor from laboratorio.persona per\n"
                    + "inner join laboratorio.profesor pro\n"
                    + "on pro.persona_id_persona = per.id_persona\n"
                    + "inner join laboratorio.asignatura_profesor ap\n"
                    + "    on ap.profesor_id_profesor = pro.id_profesor\n"
                    + "inner join laboratorio.asignatura asig\n"
                    + "on ap.asignatura_id_asignatura = asig.id_asignatura\n"
                    + "where per.id_persona ='" + idPersona + "' and asig.id_asignatura='" + idAsignatura + "')as X\n"
                    + "      inner join laboratorio.asignatura_profesor_alumno apa\n"
                    + "on apa.asignatura_profesor_id_asignatura_profesor = X.id_asignatura_profesor\n"
                    + "inner join laboratorio.alumno alno\n"
                    + "on apa.alumno_id_alumno = alno.id_alumno\n"
                    + "      inner join laboratorio.persona per on alno.persona_id_persona=per.id_persona;";
            PreparedStatement st = this.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setIdPersona(rs.getInt("id_alumno"));
                persona.setNombre(rs.getString("nombre_persona"));
                persona.setApellido(rs.getString("apellido_persona"));
                persona.setDni(rs.getString("dni_persona"));
                persona.setGenero(rs.getString("genero_persona"));
                lista.add(persona);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            desconectar();
        }
        return lista;
    }

    public List<Carrera> listCarreraByFacultad(int idFacultad) throws Exception {
        List<Carrera> lista;
        ResultSet rs;
        try {
            conectar();
            String sql = "select id_carrera,nombre_carrera from laboratorio.carrera where facultad_id_facultad =" + idFacultad;
            PreparedStatement st = this.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Carrera obj = new Carrera();
                obj.setIdCarrera(rs.getInt("id_carrera"));
                obj.setNombreCarrera(rs.getString("nombre_carrera"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            desconectar();
        }
        return lista;
    }

    public List<Laboratorio> getCodigoLab(int idLab) throws Exception {
        List<Laboratorio> lista;
        ResultSet rs;
        try {
            String sql = "select*from laboratorio.laboratorio where id_laboratorio=" + idLab;
            conectar();
            PreparedStatement st = getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Laboratorio obj = new Laboratorio();
                obj.setCodigo_laboratorio(rs.getNString("codigo_laboratorio"));

                lista.add(obj);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return lista;
    }

    public List<Asignatura> listMateriaDocente(int idPersona) throws Exception {
        List<Asignatura> lista;
        LoginMB login = new LoginMB();
        ResultSet rs;
        try {
            String sql = "select nombre_asignatura, id_asignatura from laboratorio.profesor p inner join\n"
                    + "    laboratorio.asignatura_profesor ap\n"
                    + "        on p.id_profesor = ap.profesor_id_profesor\n"
                    + "inner join laboratorio.asignatura a\n"
                    + "    on a.id_asignatura = ap.asignatura_id_asignatura\n"
                    + "inner join laboratorio.persona p2 on p.persona_id_persona = p2.id_persona\n"
                    + "where id_persona='" + idPersona + "'";
            conectar();
            PreparedStatement st = getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Asignatura obj = new Asignatura();
                obj.setNombreAsignatura(rs.getString("nombre_asignatura"));
                obj.setIdAsignatura(rs.getInt("id_asignatura"));

                lista.add(obj);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return lista;
    }

    public List<PeriodoAcademico> listPeriodoAcademico() {
        List<PeriodoAcademico> periodoAcademicos = new ArrayList<>();
        ResultSet resultSet;
        String sql = String.format("select * from laboratorio.periodo_academico");
        try {
            conectar();
            resultSet = ejecutarSql(sql);

            while (resultSet.next()) {
                periodoAcademicos.add(new PeriodoAcademico(
                        resultSet.getInt("id_periodo_academico"),
                        resultSet.getString("periodo_academico")));
            }
            System.out.println(periodoAcademicos);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
        return periodoAcademicos;
    }

    public HorarioLaboratorio listHorasLaboratorio(int horLab) throws Exception {
        ResultSet rs;
        HorarioLaboratorio obj = new HorarioLaboratorio();
        try {
            String sql = "select hora_apertura_horario_laboratorio, hora_cierre_horario_laboratorio\n"
                    + "from laboratorio.horario_laboratorio where laboratorio_id_laboratorio =" + horLab;
            conectar();
            PreparedStatement st = getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                obj.setHoraAperturaLaboratorio(rs.getObject("hora_apertura_horario_laboratorio", Calendar.class));
                obj.setHoraCierreLaboratorio(rs.getObject("hora_cierre_horario_laboratorio", Calendar.class));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
        return obj;

    }

    public List<Date> listHoras(HorarioLaboratorio holab) {
        Calendar myCalendario = Calendar.getInstance();
        myCalendario.set(Calendar.HOUR, holab.getHorarioLaboratorio());

        return null;
    }

    public int registrarHorario(Practica practica, List<Equipo> listEquipo, List<Persona> listEstudiante) {
        int aux = 0;
        try {
            ResultSet rs;
            String sentencia;
            String jsonEquipo = "[";
            String jsonEstudiante = "[";
            for (Equipo eqObj : listEquipo) {
                jsonEquipo += "{\n"
                        + "  \"idEquipo\": " + eqObj.getCodigo() + ",\n"
                        + "},";
            }
            jsonEquipo = jsonEquipo.substring(0, jsonEquipo.length() - 1);
            jsonEquipo += "]";
            for (Persona alObj : listEstudiante) {
                jsonEstudiante += "{\n"
                        + "  \"idEstudiante\": " + alObj.getIdPersona() + ",\n"
                        + "},";
            }
            jsonEstudiante = jsonEstudiante.substring(0, jsonEstudiante.length() - 1);
            jsonEstudiante += "]";
            sentencia = "SELECT laboratorio.\"insertPractica\"(\n"
                    + "	" + practica.getPeriodoAcademico().getIdPeriodoAcademico() + ", \n"
                    + "	" + practica.getProfesor().getIdPersona() + ", \n"
                    + "	" + practica.getIdTipoSolicitud().getTipoSolicitud() + ", \n"
                    + "	" + practica.getNumero_practica() + ", \n"
                    + "	'" + practica.getFecha_reserva() + "', \n"
                    + "	'" + practica.getHora_inicio() + "', \n"
                    + "	'" + practica.getHora_fin() + "', \n"
                    + "	" + practica.getNumero_practica() + ", \n"
                    + "	'" + practica.getTema_practica() + "', \n"
                    + "	'" + practica.getObjetivo_practica() + "', \n"
                    + "	'" + practica.getFecha_solicitud() + "', \n"
                    + "	'" + practica.getEncargado().getIdEncargado() + "', \n"
                    + "	'" + practica.getEstado() + "', \n"
                    + "	'" + jsonEstudiante + "', \n"
                    + "'" + jsonEquipo + "'\n"
                    + ")";

            conectar();
            rs = ejecutarSql(sentencia);
            while (rs.next()) {
                aux = Integer.parseInt(rs.getString("insertPractica"));
            }
            return aux;

        } catch (SQLException e) {
            return aux;
        } finally {
            desconectar();
        }

    }

    public List<Encargado> listarEncargadoBYlaboratorio(int encargadobylabo) throws Exception {
        List<Encargado> lista = new ArrayList<>();
        ResultSet resultSet;
        try {
            conectar();
            String sql = "select id_persona, nombre_persona, apellido_persona\n"
                    + "	FROM laboratorio.persona p \n"
                    + "	inner join laboratorio.encargado en on en.\"persona_id_persona\"=p.\"id_persona\"\n"
                    + "	inner join laboratorio.encargado_laboratorio enl on enl.\"encargado_id_encargado\"  = en.\"id_encargado\"\n"
                    + "	where laboratorio_id_laboratorio =" + encargadobylabo;
            PreparedStatement st = this.getConnection().prepareStatement(sql);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                Encargado obj = new Encargado();
                obj.setIdPersona(resultSet.getInt("id_persona"));
                obj.setNombre_persona(resultSet.getString("nombre_persona"));
                obj.setApellido_persona(resultSet.getString("apellido_persona"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("x Aqui salio error linea 332 solicitudDAO");
        } finally {
            desconectar();
        }
        return lista;
    }


}
