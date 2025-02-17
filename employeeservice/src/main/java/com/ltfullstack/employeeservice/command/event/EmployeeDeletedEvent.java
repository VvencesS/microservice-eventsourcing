package com.ltfullstack.employeeservice.command.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDeletedEvent {
    private String id;
}
