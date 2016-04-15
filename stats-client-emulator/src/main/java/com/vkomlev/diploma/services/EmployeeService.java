package com.vkomlev.diploma.services;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vkomlev.diploma.stats.entities.Employee;
import com.vkomlev.diploma.stats.entities.SingleAction;
import com.vkomlev.diploma.stats.entities.Task;

@Path("/EmployeeService")
@WebService
public interface EmployeeService {

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Employee getEmployee(@QueryParam("employeeName") String employeeName);

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Employee saveEmployee(Employee employee);

    @PUT
    @Path("/task")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Task createTask(Task task);

    @POST
    @Path("/task")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Task closeTask(Task task);

    @PUT
    @Path("/logWork")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    SingleAction logWork(SingleAction action);

    @GET
    @Path("/getTaskTemplate")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Task getTaskTemplate();

    @GET
    @Path("/getEmployeeTemplate")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Employee getEmployeeTemplate();

    @GET
    @Path("/getSingleActionTemplate")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    SingleAction getSingleActionTemplate();
}
