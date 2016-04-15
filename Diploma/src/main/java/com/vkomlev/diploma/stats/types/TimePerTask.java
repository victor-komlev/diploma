package com.vkomlev.diploma.stats.types;

public class TimePerTask {

    private String _id;
    private Long totalTime;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_id == null) ? 0 : _id.hashCode());
        result = prime * result + ((totalTime == null) ? 0 : totalTime.hashCode());
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
        TimePerTask other = (TimePerTask) obj;
        if (_id == null) {
            if (other._id != null)
                return false;
        } else if (!_id.equals(other._id))
            return false;
        if (totalTime == null) {
            if (other.totalTime != null)
                return false;
        } else if (!totalTime.equals(other.totalTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TimePerTask [_id=" + _id + ", totalTime=" + totalTime + "]";
    }

}
