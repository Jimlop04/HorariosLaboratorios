package Controller.equipos;

import Model.equipos.DetalleMantenimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ManagedBean
@SessionScoped
public class DetalleMantenimientoMB {
    private DetalleMantenimiento detalleMantenimiento;

}
