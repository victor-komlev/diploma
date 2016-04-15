package com.vkomlev.diploma.stats.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "singleAction")
public class SingleAction {

    public enum ActionType {
        WORK, NEGOTIATE, MEETING
    }

    private String id;
    private String performerEmployeeId;
    private Long timeTaken;
    private String parentTaskId;
    private ActionType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerformerEmployeeId() {
        return performerEmployeeId;
    }

    public void setPerformerEmployeeId(String performerEmployeeId) {
        this.performerEmployeeId = performerEmployeeId;
    }

    public Long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((parentTaskId == null) ? 0 : parentTaskId.hashCode());
        result = prime * result + ((performerEmployeeId == null) ? 0 : performerEmployeeId.hashCode());
        result = prime * result + ((timeTaken == null) ? 0 : timeTaken.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        SingleAction other = (SingleAction) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (parentTaskId == null) {
            if (other.parentTaskId != null)
                return false;
        } else if (!parentTaskId.equals(other.parentTaskId))
            return false;
        if (performerEmployeeId == null) {
            if (other.performerEmployeeId != null)
                return false;
        } else if (!performerEmployeeId.equals(other.performerEmployeeId))
            return false;
        if (timeTaken == null) {
            if (other.timeTaken != null)
                return false;
        } else if (!timeTaken.equals(other.timeTaken))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SingleAction [id=" + id + ", performerEmployeeId=" + performerEmployeeId + ", timeTaken=" + timeTaken
                + ", parentTaskId=" + parentTaskId + ", type=" + type + "]";
    }

}
