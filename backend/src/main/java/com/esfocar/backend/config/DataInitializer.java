package com.esfocar.backend.config;

import com.esfocar.backend.model.CriterioRubrica;
import com.esfocar.backend.model.Documento;
import com.esfocar.backend.model.Rubrica;
import com.esfocar.backend.model.Usuario;
import com.esfocar.backend.repository.DocumentoRepository;
import com.esfocar.backend.repository.RubricaRepository;
import com.esfocar.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final DocumentoRepository documentoRepository;
    private final RubricaRepository rubricaRepository;

    public DataInitializer(UsuarioRepository usuarioRepository,
                           DocumentoRepository documentoRepository,
                           RubricaRepository rubricaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.documentoRepository = documentoRepository;
        this.rubricaRepository = rubricaRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            Usuario admin1 = new Usuario("11.111.111-1", "Administrador Central", "admin1@gmail.com", "admin123", "ADMIN", true);
            Usuario admin2 = new Usuario("22.222.222-2", "Administrador Academico", "admin2@gmail.com", "Admin1234", "ADMIN", false);
            Usuario admin3 = new Usuario("33.333.333-3", "Administrador Soporte", "admin3@gmail.com", "Admin1234", "ADMIN", false);

            Usuario profesor = new Usuario("12.345.678-5", "Instructor Mayor Carlos Ruiz", "cruiz@gmail.com", "Profe1234", "PROFESOR", false);

            Usuario estudiante1 = new Usuario("20.123.456-7", "Aspirante Juan Perez", "jperez@gmail.com", "Alumno1234", "ESTUDIANTE", false);
            Usuario estudiante2 = new Usuario("21.987.654-3", "Aspirante Maria Morales", "mmorales@gmail.com", "Alumno1234", "ESTUDIANTE", false);

            usuarioRepository.saveAll(Arrays.asList(admin1, admin2, admin3, profesor, estudiante1, estudiante2));

            Documento documento = new Documento(
                    "Manual de Instruccion Policial",
                    "Guia basica operativa de formacion policial",
                    "PDF",
                    "/archivos/manual_instruccion.pdf",
                    "2026-03-01",
                    profesor.getId()
            );
            documentoRepository.save(documento);

            Rubrica rubrica = new Rubrica("Evaluacion de Tiro Policial", "Instruccion y Armamento", 7.00, profesor.getId());
            CriterioRubrica c1 = new CriterioRubrica("Medidas de Seguridad y Manejo de Arma", 40, 7.0, rubrica);
            CriterioRubrica c2 = new CriterioRubrica("Precision y Agrupacion en Poligono", 60, 7.0, rubrica);
            rubrica.addCriterio(c1);
            rubrica.addCriterio(c2);

            rubricaRepository.save(rubrica);
        } else {
            // Actualizar registros existentes en la BD a @gmail.com y ajustar admin1
            List<Usuario> usuarios = usuarioRepository.findAll();
            for (Usuario u : usuarios) {
                if (u.getCorreo() != null && u.getCorreo().contains("@esfocar.cl")) {
                    u.setCorreo(u.getCorreo().replace("@esfocar.cl", "@gmail.com"));
                }
                if ("admin1@gmail.com".equalsIgnoreCase(u.getCorreo()) || "11.111.111-1".equals(u.getRut())) {
                    u.setPassword("admin123");
                    u.setPrimerIngreso(true);
                }
            }
            usuarioRepository.saveAll(usuarios);
        }
    }
}
