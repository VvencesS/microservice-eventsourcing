package com.ltfullstack.employeeservice.command.event;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDeletedEvent {
    private String id;
}
