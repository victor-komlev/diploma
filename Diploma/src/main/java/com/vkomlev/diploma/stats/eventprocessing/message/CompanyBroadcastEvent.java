/**
 * 
 */
package com.vkomlev.diploma.stats.eventprocessing.message;

import java.util.List;

import com.mera.cs.eventprocessing.notification.IEvent;
import com.vkomlev.diploma.stats.types.ActionAverageTime;
import com.vkomlev.diploma.stats.types.TaskAverageTime;

/**
 * @author vkomlev
 *
 */
public class CompanyBroadcastEvent implements IEvent {

    private List<ActionAverageTime> perActionAverageTimes;
    private TaskAverageTime TaskAverageTime;

    public List<ActionAverageTime> getPerActionAverageTimes() {
        return perActionAverageTimes;
    }

    public void setPerActionAverageTimes(List<ActionAverageTime> perActionAverageTimes) {
        this.perActionAverageTimes = perActionAverageTimes;
    }

    public TaskAverageTime getTaskAverageTime() {
        return TaskAverageTime;
    }

    public void setTaskAverageTime(TaskAverageTime taskAverageTime) {
        TaskAverageTime = taskAverageTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mera.cs.eventprocessing.notification.IEvent#getRoute()
     */
    @Override
    public String getRoute() {
        return "COMPANY";
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((TaskAverageTime == null) ? 0 : TaskAverageTime.hashCode());
        result = prime * result + ((perActionAverageTimes == null) ? 0 : perActionAverageTimes.hashCode());
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
        CompanyBroadcastEvent other = (CompanyBroadcastEvent) obj;
        if (TaskAverageTime == null) {
            if (other.TaskAverageTime != null)
                return false;
        } else if (!TaskAverageTime.equals(other.TaskAverageTime))
            return false;
        if (perActionAverageTimes == null) {
            if (other.perActionAverageTimes != null)
                return false;
        } else if (!perActionAverageTimes.equals(other.perActionAverageTimes))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CompanyBroadcastEvent [perActionAverageTimes=" + perActionAverageTimes + ", TaskAverageTime="
                + TaskAverageTime + "]";
    }

}
