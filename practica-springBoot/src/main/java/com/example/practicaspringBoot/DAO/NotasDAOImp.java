package com.example.practicaspringBoot.DAO;

import com.example.practicaspringBoot.Modelo.Alumno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class NotasDAOImp implements NotasDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Alumno> getNotas() {
        String quer = "FROM Alumno";
        List<Alumno> listaNotas = entityManager.createQuery(quer).getResultList();
        return listaNotas;
    }

    @Override
    public void insertarNota(Alumno alumno) {
        entityManager.persist(alumno);
    }

    @Override
    public void borrarNota(int id) {
        Alumno alumno = entityManager.find(Alumno.class, id);
        if (alumno != null) {
            entityManager.remove(alumno);
        }
    }

    @Override
    public void actualizarNota(int id, Alumno alumnoActualizado) {
        Alumno alumno = entityManager.find(Alumno.class, id);
        if (alumno != null) {
            alumno.setNombre(alumnoActualizado.getNombre());
            alumno.setApellido(alumnoActualizado.getApellido());
            alumno.setNota(alumnoActualizado.getNota());
            entityManager.flush();
        }
    }

    @Override
    public Alumno getAlumnoPorId(int id) {
        Alumno alumno = entityManager.find(Alumno.class, id);
        return alumno;
    }

}


