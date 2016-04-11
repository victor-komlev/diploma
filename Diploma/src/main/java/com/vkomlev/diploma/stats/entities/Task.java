package com.vkomlev.diploma.stats.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task")
public class Task {

    @Id
    private Integer id;

    private Integer ownerId;

    private Integer closerId;

    private String taskLabel;

    private String taskDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getCloserId() {
        return closerId;
    }

    public void setCloserId(Integer closerId) {
        this.closerId = closerId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((closerId == null) ? 0 : closerId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
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
        if (closerId == null) {
            if (other.closerId != null)
                return false;
        } else if (!closerId.equals(other.closerId))
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
        return "Task [id=" + id + ", ownerId=" + ownerId + ", closerId=" + closerId + ", taskLabel=" + taskLabel
                + ", taskDetails=" + taskDetails + "]";
    }

}
