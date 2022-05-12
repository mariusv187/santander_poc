package models;

import java.util.List;

public class Instances {

    private String url;
    private List<Actions> instances;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Actions> getInstances() {
        return instances;
    }

    public void setInstances(List<Actions> instances) {
        this.instances = instances;
    }
}
