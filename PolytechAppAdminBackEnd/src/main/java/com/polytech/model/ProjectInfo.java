
package com.polytech.model;

/**
 * This class contains the information about the project.
 * @author Florian.Courtial
 */
public class ProjectInfo {
    
    private String version;
    private String name;

    public ProjectInfo(String version, String name) {
        this.version = version;
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
