package elsanti.rh.controlador;


import elsanti.rh.modelo.Empleado;
import elsanti.rh.servicio.EmpleadoServicio;
import elsanti.rh.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.slf4j.Logger.*;

@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        var empleados= empleadoServicio.ListarEmpleados();
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;

    }
    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar"+empleado);
        return empleadoServicio.guardarEmpleado(empleado);
    }
}
