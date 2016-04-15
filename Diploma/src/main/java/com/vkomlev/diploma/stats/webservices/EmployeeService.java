package com.vkomlev.diploma.stats.webservices;

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
import com.vkomlev.diploma.stats.exception.DuplicatedResultException;
import com.vkomlev.diploma.stats.exception.IncorrectTaskException;
import com.vkomlev.diploma.stats.exception.UserNotFoundException;

import javax.jws.WebService;

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
    Employee saveEmployee(Employee employee) throws DuplicatedResultException;

    @PUT
    @Path("/task")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Task createTask(Task task) throws IncorrectTaskException, UserNotFoundException;

    @POST
    @Path("/task")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Task closeTask(Task task) throws IncorrectTaskException;

    @PUT
    @Path("/logWork")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    SingleAction logWork(SingleAction action) throws IncorrectTaskException, UserNotFoundException;

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
