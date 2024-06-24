package elsanti.rh.repositorio;


import elsanti.rh.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
}
