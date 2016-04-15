/**
 * 
 */
package com.vkomlev.diploma.stats.eventprocessing.message;

import java.util.List;

import com.mera.cs.eventprocessing.notification.IEvent;
import com.vkomlev.diploma.stats.types.EmployeesAverageActionTime;
import com.vkomlev.diploma.stats.types.EmployeesTaskTimeAverage;

/**
 * @author vkomlev
 *
 */
public class EmployeeBroadcastEvent implements IEvent {

    private List<EmployeesAverageActionTime> employeesAverageActionTime;
    private List<EmployeesTaskTimeAverage> employeesTaskTimeAverage;

    /*
     * (non-Javadoc)
     * 
     * @see com.mera.cs.eventprocessing.notification.IEvent#getRoute()
     */
    @Override
    public String getRoute() {
        return "EMPLOYEE";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mera.cs.eventprocessing.notification.IEvent#getTopic()
     */
    @Override
    public String getTopic() {
        return "ALL";
    }

    public List<EmployeesAverageActionTime> getEmployeesAverageActionTime() {
        return employeesAverageActionTime;
    }

    public void setEmployeesAverageActionTime(List<EmployeesAverageActionTime> employeesAverageActionTime) {
        this.employeesAverageActionTime = employeesAverageActionTime;
    }

    public List<EmployeesTaskTimeAverage> getEmployeesTaskTimeAverage() {
        return employeesTaskTimeAverage;
    }

    public void setEmployeesTaskTimeAverage(List<EmployeesTaskTimeAverage> employeesTaskTimeAverage) {
        this.employeesTaskTimeAverage = employeesTaskTimeAverage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employeesAverageActionTime == null) ? 0 : employeesAverageActionTime.hashCode());
        result = prime * result + ((employeesTaskTimeAverage == null) ? 0 : employeesTaskTimeAverage.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmployeeBroadcastEvent other = (EmployeeBroadcastEvent) obj;
        if (employeesAverageActionTime == null) {
            if (other.employeesAverageActionTime != null)
                return false;
        } else if (!employeesAverageActionTime.equals(other.employeesAverageActionTime))
            return false;
        if (employeesTaskTimeAverage == null) {
            if (other.employeesTaskTimeAverage != null)
                return false;
        } else if (!employeesTaskTimeAverage.equals(other.employeesTaskTimeAverage))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EmployeeBroadcastEvent [employeesAverageActionTime=" + employeesAverageActionTime
                + ", employeesTaskTimeAverage=" + employeesTaskTimeAverage + "]";
    }

}
