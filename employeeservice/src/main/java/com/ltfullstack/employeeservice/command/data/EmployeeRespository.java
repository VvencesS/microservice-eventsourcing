package com.ltfullstack.employeeservice.command.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<Employee, String> {
}
