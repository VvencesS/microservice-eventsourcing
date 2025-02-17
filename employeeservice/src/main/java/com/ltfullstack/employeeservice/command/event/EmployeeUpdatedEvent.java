package com.ltfullstack.employeeservice.command.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdatedEvent {
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
