/**
 * 
 */
package com.vkomlev.diploma.stats.types;

/**
 * @author vkomlev
 *
 */
public class EmployeesTaskTimeAverage {

    private String _id; // Emoloyee Id
    private String employeeName;
    private Long avgTime;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Long avgTime) {
        this.avgTime = avgTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_id == null) ? 0 : _id.hashCode());
        result = prime * result + ((avgTime == null) ? 0 : avgTime.hashCode());
        result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
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
        EmployeesTaskTimeAverage other = (EmployeesTaskTimeAverage) obj;
        if (_id == null) {
            if (other._id != null)
                return false;
        } else if (!_id.equals(other._id))
            return false;
        if (avgTime == null) {
            if (other.avgTime != null)
                return false;
        } else if (!avgTime.equals(other.avgTime))
            return false;
        if (employeeName == null) {
            if (other.employeeName != null)
                return false;
        } else if (!employeeName.equals(other.employeeName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EmployeesTaskTimeAverage [_id=" + _id + ", employeeName=" + employeeName + ", avgTime=" + avgTime + "]";
    }

}
