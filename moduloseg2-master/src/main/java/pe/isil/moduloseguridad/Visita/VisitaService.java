package pe.isil.moduloseguridad.Visita;

import pe.isil.moduloseguridad.shared.BasicRespone;

import java.util.List;

public interface VisitaService {

    BasicRespone update(Visita visita, Long id);

    void delete (Long id);

    void save(Visita visita);

    Visita getVisitaById(Long id);

    List<Visita> findAllVisitas();


}
