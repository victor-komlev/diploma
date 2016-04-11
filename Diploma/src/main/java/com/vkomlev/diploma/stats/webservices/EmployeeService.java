package com.vkomlev.diploma.stats.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vkomlev.diploma.stats.entities.Employee;
import com.vkomlev.diploma.stats.exception.DuplicatedResultException;

import javax.jws.WebService;

@Path("/EmployeeService")
@WebService
public interface EmployeeService {

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Employee getEmployee(@QueryParam("employeeId") String employeeId);

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Employee saveEmployee(Employee employee) throws DuplicatedResultException;
    
    @GET
    @Path("/getEmployeeTemplate")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Employee getEmployeeTemplate();
    
}
