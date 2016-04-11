package com.vkomlev.diploma.stats.types;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vkomlev on 4/10/2016.
 */
@XmlRootElement
public class BasicHelloType {
    private String helloTitle;
    private String helloMessage;

    public String getHelloTitle() {
        return helloTitle;
    }

    public void setHelloTitle(String helloTitle) {
        this.helloTitle = helloTitle;
    }

    public String getHelloMessage() {
        return helloMessage;
    }

    public void setHelloMessage(String helloMessage) {
        this.helloMessage = helloMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicHelloType that = (BasicHelloType) o;

        if (helloTitle != null ? !helloTitle.equals(that.helloTitle) : that.helloTitle != null) return false;
        return helloMessage != null ? helloMessage.equals(that.helloMessage) : that.helloMessage == null;

    }

    @Override
    public int hashCode() {
        int result = helloTitle != null ? helloTitle.hashCode() : 0;
        result = 31 * result + (helloMessage != null ? helloMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BasicHelloType{" +
                "helloTitle='" + helloTitle + '\'' +
                ", helloMessage='" + helloMessage + '\'' +
                '}';
    }

}
