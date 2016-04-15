/**
 * 
 */
package com.vkomlev.diploma.stats.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vkomlev.diploma.stats.exception.IncorrectTaskException;
import com.vkomlev.diploma.stats.exception.UserNotFoundException;
import com.vkomlev.diploma.stats.services.MainStatsProcessingService;
import com.vkomlev.diploma.stats.types.ActionAverageTime;
import com.vkomlev.diploma.stats.types.EmployeesAverageActionTime;
import com.vkomlev.diploma.stats.types.EmployeesTaskTimeAverage;
import com.vkomlev.diploma.stats.types.TaskAverageTime;
import com.vkomlev.diploma.stats.types.TimePerTask;

public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger LOG = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    @Autowired
    private MainStatsProcessingService stats;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vkomlev.diploma.stats.webservices.StatisticsService#getTimeForTask(
     * java.lang.String)
     */
    @Override
    public TimePerTask getTimeForTask(String taskId) throws IncorrectTaskException, UserNotFoundException {
        LOG.info("getTimeForTask({})", taskId);
        return stats.getTimeForTask(taskId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vkomlev.diploma.stats.webservices.StatisticsService#
     * getEmployeesAverageTimeForActionType(java.lang.String, java.lang.String)
     */
    @Override
    public EmployeesAverageActionTime getEmployeesAverageTimeForActionType(String employeeId, String actionType) {
        LOG.info("getEmployeesAverageTimeForActionType({}, {})", employeeId, actionType);
        return stats.getEmployeesAverageTimeForActionType(employeeId, actionType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vkomlev.diploma.stats.webservices.StatisticsService#
     * getAverageTimeForActionType(java.lang.String)
     */
    @Override
    public ActionAverageTime getAverageTimeForActionType(String actionType) {
        LOG.info("getAverageTimeForActionType({})", actionType);
        return stats.getAverageTimeForActionType(actionType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vkomlev.diploma.stats.webservices.StatisticsService#
     * getEmployeesTaskAverageTime(java.lang.String)
     */
    @Override
    public EmployeesTaskTimeAverage getEmployeesTaskAverageTime(String employeeId) {
        LOG.info("getEmployeesTaskAverageTime({})", employeeId);
        return stats.getEmployeesTaskAverageTime(employeeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vkomlev.diploma.stats.webservices.StatisticsService#
     * getAverageTimeForTask(java.lang.String)
     */
    @Override
    public TaskAverageTime getAverageTimeForTask() {
        LOG.info("getAverageTimeForTask()");
        return stats.getAverageTimeForTask();
    }

}
