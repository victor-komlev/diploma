package com.vkomlev.diploma.stats.webservices;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vkomlev.diploma.stats.exception.IncorrectTaskException;
import com.vkomlev.diploma.stats.exception.UserNotFoundException;
import com.vkomlev.diploma.stats.types.ActionAverageTime;
import com.vkomlev.diploma.stats.types.EmployeesAverageActionTime;
import com.vkomlev.diploma.stats.types.EmployeesTaskTimeAverage;
import com.vkomlev.diploma.stats.types.TaskAverageTime;
import com.vkomlev.diploma.stats.types.TimePerTask;

@Path("/StatisticsService")
@WebService
public interface StatisticsService {

    @GET
    @Path("/task/{taskId}/totalTime")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    TimePerTask getTimeForTask(@PathParam("taskId") String taskId) throws IncorrectTaskException, UserNotFoundException;

    @GET
    @Path("/employee/{employeeId}/action/{actionType}/avgTime")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    EmployeesAverageActionTime  getEmployeesAverageTimeForActionType(@PathParam("employeeId") String employeeId,
            @PathParam("actionType") String actionType);

    @GET
    @Path("/action/{actionType}/avgTime")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    ActionAverageTime getAverageTimeForActionType(@PathParam("actionType") String actionType);

    @GET
    @Path("/employee/{employeeId}/task/avgtime")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    EmployeesTaskTimeAverage getEmployeesTaskAverageTime(@PathParam("employeeId") String employeeId);
    
    @GET
    @Path("/task/avgTime")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    TaskAverageTime getAverageTimeForTask();
}
