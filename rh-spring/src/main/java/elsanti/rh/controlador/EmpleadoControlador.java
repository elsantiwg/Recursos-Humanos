package elsanti.rh.controlador;


import elsanti.rh.excepcion.RecursoNoEncontradoExcepcion;
import elsanti.rh.modelo.Empleado;
import elsanti.rh.servicio.EmpleadoServicio;
import elsanti.rh.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id){
        Empleado empleado =empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado ==null)
            throw new RecursoNoEncontradoExcepcion(("no se encontro el empleado id:"+ id));
        return  ResponseEntity.ok(empleado);
    }

    @PutMapping("/e,pleado/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoRecibido){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado==null)
            throw new RecursoNoEncontradoExcepcion("el id recibido no existe "+ id);
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setDepartamento(empleadoRecibido.getDepartamento());
        empleado.setSueldo(empleadoRecibido.getSueldo());
        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }
}
