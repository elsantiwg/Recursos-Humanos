package elsanti.rh.servicio;

import elsanti.rh.modelo.Empleado;
import java.util.List;

public interface IEmpleadoServicio {
    public List<Empleado> ListarEmpleados();

    public Empleado buscarEmpleadoPorId(Integer idEmpleado);

    public Empleado guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);
}
