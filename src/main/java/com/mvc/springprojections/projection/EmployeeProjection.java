package com.mvc.springprojections.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface EmployeeProjection {

    @JsonIgnore
    String getFirstName();

    @JsonIgnore
    String getLastName();

    String getPosition();

    String getDepartmentName();

    default String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
