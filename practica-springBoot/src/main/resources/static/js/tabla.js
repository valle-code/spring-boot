$(document).ready(function () {
    $('#notas').DataTable();
    cargarUsuarios();
});

async function cargarUsuarios() {
    const request = await fetch('notas', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });
    const alumnos = await request.json();
    let tabla = '';
    for (let alumno of alumnos) {
        tabla += `
             <tr>
                <td>${alumno.nombre}</td>
                <td>${alumno.apellido}</td>
                <td>${alumno.nota}</td>
                <td><button onclick="actualizarUsuario(${alumno.id})">Editar</button></td>
                <td><button onclick="eliminarUsuario(${alumno.id})">Eliminar</button></td>
             </tr>
        `;
    }
    document.querySelector('#notas-tabla').innerHTML = tabla;
}

async function insertarUsuarios() {
    event.preventDefault();
    if (document.querySelector('#nombre').value == '' || document.querySelector('#apellido').value == '' || document.querySelector('#nota').value == '') {
        alert("No se puede dejar campos vacios");
    } else {
        const nombre = document.querySelector('#nombre').value;
        const apellido = document.querySelector('#apellido').value;
        const nota = document.querySelector('#nota').value;

        const response = await fetch('insertar', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                nombre: nombre,
                apellido: apellido,
                nota: nota
            })
        });

        if (response.ok) {
            cargarUsuarios();
            document.querySelector('#nombre').value = '';
            document.querySelector('#apellido').value = '';
            document.querySelector('#nota').value = '';
        }
    }

}

async function eliminarUsuario(id) {
    const response = await fetch(`borrar/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });

    if (response.ok) {
        // Reload the table to show the new data
        cargarUsuarios();
    }

}

async function actualizarUsuario(id) {
    event.preventDefault();

    const nombre = document.querySelector('#nombre').value;
    const apellido = document.querySelector('#apellido').value;
    const nota = document.querySelector('#nota').value;

    if (nombre == '' || apellido == '' || nota == '') {
        alert('No se puede dejar campos vacios.');
    } else {
        const usuarioActualizado = {
            id: id,
            nombre: nombre,
            apellido: apellido,
            nota: nota
        };

        const response = await fetch(`actualizar/${id}`, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuarioActualizado)
        });

        if (response.ok) {
            cargarUsuarios();
            document.querySelector('#nombre').value = '';
            document.querySelector('#apellido').value = '';
            document.querySelector('#nota').value = '';
        } else {
            alert('Error al actualizar el usuario.');
        }
    }
}





