package com.ltfullstack.employeeservice.command.event;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreatedEvent {
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
