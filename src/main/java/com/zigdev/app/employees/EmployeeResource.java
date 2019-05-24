package com.zigdev.app.employees;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path( "/employees" )
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    @Inject
    EmployeeRepository employeeRepository;

    @POST
    public Response createEmployee(Employee employee) {
        employeeRepository.create( employee );
        return Response.ok(employee).build();
    }

    @PUT
    @Path( "/{id}" )
    public Response updateEmployee(@PathParam("id") long id, Employee employee){
        Employee updatedEmployee = employeeRepository.find( id );
        updatedEmployee.setName( employee.getName() );
        updatedEmployee.setSalary( employee.getSalary() );
        return Response.ok(employeeRepository.edit( updatedEmployee )).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Long id){
        Employee employee = employeeRepository.find( id );
        employeeRepository.remove( employee );
        return Response.ok(employee).build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Long id) {
        return Response.ok(employeeRepository.find( id )).build();
    }

    @GET
    public Response getAllEmployees() {
        return Response.ok(employeeRepository.findAll( )).build();
    }

}
