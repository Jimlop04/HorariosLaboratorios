/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.solicitudes;

import Controller.login.LoginMB;
import Model.laboratorios.Laboratorio;
import Model.solicitudes.Asignatura;
import Model.solicitudes.Carrera;
import Model.solicitudes.Curso;
import Model.solicitudes.Facultad;
import Model.solicitudes.HorarioLaboratorio;
import Model.solicitudes.PeriodoAcademico;
import global.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
