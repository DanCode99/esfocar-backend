package com.esfocar.backend.repository;

import com.esfocar.backend.model.Rubrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricaRepository extends JpaRepository<Rubrica, Long> {
}
