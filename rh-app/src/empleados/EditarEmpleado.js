import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'

export default function EditarEmpleado() {
    let navegacion= useNavigate();

    const{id}= useParams();

     const [empleado, setEmpleado]=useState({
        nombre:"",
        departamento:"",
        sueldo:""
     })

     const{nombre,departamento,sueldo}=empleado

     useEffect(()=>{
        cargarEmpleado();
     },[])

     const cargarEmpleado=async()=>{
            const resultado = await axios.get('${urlBase}/${id}')
                setEmpleado(resultado.data);  

     }

     const onInputChange=(e)=>{setEmpleado({...empleado,[e.target.name]:e.target.value})}
 
     const onSubmit= async (e)=>{
        e.preventDefault();
        const urlBase ="http://localhost:8080/rh-app/empleados";
        await axios.put("${urlBase}/${id}", empleado);
        navegacion("/");

     }
     return (
    <div className='container'>
        <div className='container text-center' style={{margin: "30px"}}>
            <h3>Editar empleado</h3>
        </div>

            <form onSubmit={(e)=>onSubmit(e)}>
                <div className="mb-3">
                    <label htmlFor="nombre" className="form-label">nombre</label>
                    <input type="text" className="form-control" id="nombre" name="nombre" required={true} value={nombre} onChange={(e)=> onInputChange(e)}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="departamento" className="form-label">departamento</label>
                    <input type="text" className="form-control" id="departamento"name="departamento" value={departamento} onChange={(e)=> onInputChange(e)}/>
                </div>
                <div className="mb-3 form-check">
                <label htmlFor="sueldo" className="form-label">sueldo</label>
                    <input type="number" step="any" className="form-control" id="sueldo"name="sueldo"value={sueldo} onChange={(e)=> onInputChange(e)}/>
                </div >
                <div className ="text-center">
                <button type="submit" className="btn btn-warning btn-sm me-3">guardar</button>
                <a href='/' className='btn btn-danger btn-sm'>regresar</a>
                </div>
            </form>
    </div>
  )
}
