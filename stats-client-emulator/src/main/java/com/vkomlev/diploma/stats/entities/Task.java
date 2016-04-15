package com.vkomlev.diploma.stats.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task")
public class Task {

    public enum State {
        ACTIVE, CLOSED
    }

    private String id;

    private Long createdAt;

    private String ownerId;

    private String taskLabel;

    private String taskDetails;

    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTaskLabel() {
        return taskLabel;
    }

    public void setTaskLabel(String taskLabel) {
        this.taskLabel = taskLabel;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((taskDetails == null) ? 0 : taskDetails.hashCode());
        result = prime * result + ((taskLabel == null) ? 0 : taskLabel.hashCode());
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
        Task other = (Task) obj;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (ownerId == null) {
            if (other.ownerId != null)
                return false;
        } else if (!ownerId.equals(other.ownerId))
            return false;
        if (state != other.state)
            return false;
        if (taskDetails == null) {
            if (other.taskDetails != null)
                return false;
        } else if (!taskDetails.equals(other.taskDetails))
            return false;
        if (taskLabel == null) {
            if (other.taskLabel != null)
                return false;
        } else if (!taskLabel.equals(other.taskLabel))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", createdAt=" + createdAt + ", ownerId=" + ownerId + ", taskLabel=" + taskLabel
                + ", taskDetails=" + taskDetails + ", state=" + state + "]";
    }

}
