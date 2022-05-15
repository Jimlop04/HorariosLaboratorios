package Controller.equipos;

import DAO.equipos.AveriaEquipoDAO;
import Model.equipos.AveriaEquipo;
import Model.equipos.ReporteAveria;
import java.io.File;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class AveriaEquipoMB implements Serializable {

    private AveriaEquipoDAO dao;
    int idEncargadoLaboratorio = 0;

    private List<AveriaEquipo> listaAverias;

    private AveriaEquipo averiaEquipo;
    private List<ReporteAveria> datosAveria;

//    public void registrar() throws Exception {
//        try {
//            dao = new AveriaEquipoDAO();
//            System.out.println(averiaEquipo);
//            dao.resgistrar(averiaEquipo);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
//        } catch (Exception e) {
//            System.out.println(averiaEquipo);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo" + e));
//        }
//    }
    public void registrar() {

        try {
            dao = new AveriaEquipoDAO();

            dao.resgistrar(averiaEquipo, idEncargadoLaboratorio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al registrar, vuelva a intentarlo" + e));
        }

    }

    public void listar(int idPersona) throws Exception {

        try {
            dao = new AveriaEquipoDAO();
            listaAverias = dao.listar(idPersona);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostConstruct
    public void init() {
        this.averiaEquipo = new AveriaEquipo();
        this.listaAverias = new ArrayList<>();
        this.datosAveria = new ArrayList<>();

    }

    public void asignarIdEncargadoLaboratorio(int id) {
        this.idEncargadoLaboratorio = id;
    }

    
    public void printPDF(AveriaEquipo averiaEquipo) throws Exception{
        datosAveria = dao.listarAveriaReporteById(averiaEquipo);
        
        String fileName = "averia.pdf";
        String jasperPath = "/resources/reportes/reporteAveriaA4.jasper";
        this.PDF(null, jasperPath, datosAveria, fileName);
    }

    public void PDF(Map<String, Object> parametros, String jasperPath, List<?> datasoruce, String fileNamae) throws  JRException, IOException {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(datasoruce);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), parametros, source);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("content-disposition","attachment;filename=" + fileNamae);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();

    }
}
