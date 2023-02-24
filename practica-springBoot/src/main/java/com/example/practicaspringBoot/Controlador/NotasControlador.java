package com.example.practicaspringBoot.Controlador;

import com.example.practicaspringBoot.DAO.NotasDAO;
import com.example.practicaspringBoot.Modelo.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotasControlador {
    @Autowired
    private NotasDAO notasDAO;

    @GetMapping("/notas")
    public List<Alumno> getNotas(){
        return notasDAO.getNotas();
    }

    @PostMapping("/insertar")
    public void insertarNota(@RequestBody Alumno alumno){
        notasDAO.insertarNota(alumno);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrarNota(@PathVariable int id){
        notasDAO.borrarNota(id);
    }


    @PutMapping("/actualizar/{id}")
    public void actualizarNota(@PathVariable int id, @RequestBody Alumno alumno){
        Alumno alumnoExistente = notasDAO.getAlumnoPorId(id);
        if (alumnoExistente != null) {
            alumnoExistente.setNombre(alumno.getNombre());
            alumnoExistente.setApellido(alumno.getApellido());
            alumnoExistente.setNota(alumno.getNota());
            notasDAO.actualizarNota(id, alumnoExistente);
        }
    }

}

