package com.example.practicaspringBoot.DAO;

import com.example.practicaspringBoot.Modelo.Alumno;

import java.util.List;

public interface NotasDAO {

    public List<Alumno> getNotas();

    public void insertarNota(Alumno alumno);

    public void actualizarNota(int id, Alumno alumno);

    public void borrarNota(int id);

    Alumno getAlumnoPorId(int id);
}

