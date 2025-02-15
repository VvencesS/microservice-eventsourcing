package com.ltfullstack.employeeservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeCommand {
    @TargetAggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
