package com.esfocar.backend.service;

import com.esfocar.backend.model.CriterioRubrica;
import com.esfocar.backend.model.Rubrica;
import com.esfocar.backend.repository.RubricaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RubricaService {

    private final RubricaRepository rubricaRepository;

    public RubricaService(RubricaRepository rubricaRepository) {
        this.rubricaRepository = rubricaRepository;
    }

    public List<Rubrica> listarTodas() {
        return rubricaRepository.findAll();
    }

    @Transactional
    public Rubrica guardar(Rubrica rubrica) {
        prepararYValidarRubrica(rubrica, false);
        return rubricaRepository.save(rubrica);
    }

    @Transactional
    public List<Rubrica> sincronizar(List<Rubrica> rubricas) {
        for (Rubrica rubrica : rubricas) {
            prepararYValidarRubrica(rubrica, true);
        }
        return rubricaRepository.saveAll(rubricas);
    }

    public boolean eliminar(Long id) {
        if (!rubricaRepository.existsById(id)) {
            return false;
        }
        rubricaRepository.deleteById(id);
        return true;
    }

    private void prepararYValidarRubrica(Rubrica rubrica, boolean limpiarIdTemporal) {
        if (limpiarIdTemporal && rubrica.getId() != null && rubrica.getId() <= 0) {
            rubrica.setId(null);
        }
        if (rubrica.getEscalaMaxima() == null) {
            rubrica.setEscalaMaxima(7.00);
        }

        List<CriterioRubrica> criterios = rubrica.getCriterios();
        if (criterios == null || criterios.isEmpty()) {
            throw new IllegalArgumentException("La rúbrica debe tener al menos un criterio.");
        }

        int sumaPorcentajes = 0;
        for (CriterioRubrica criterio : criterios) {
            if (limpiarIdTemporal && criterio.getId() != null && criterio.getId() <= 0) {
                criterio.setId(null);
            }
            if (criterio.getPorcentaje() == null || criterio.getPorcentaje() <= 0) {
                throw new IllegalArgumentException("Cada criterio debe tener un porcentaje mayor a 0.");
            }
            sumaPorcentajes += criterio.getPorcentaje();
            criterio.setRubrica(rubrica);
        }

        if (sumaPorcentajes != 100) {
            throw new IllegalArgumentException("La suma de los porcentajes de los criterios debe ser exactamente 100 (actual: " + sumaPorcentajes + ").");
        }
    }
}
