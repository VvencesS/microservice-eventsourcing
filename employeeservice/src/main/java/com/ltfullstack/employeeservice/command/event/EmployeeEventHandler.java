package com.ltfullstack.employeeservice.command.event;

import com.ltfullstack.employeeservice.command.data.Employee;
import com.ltfullstack.employeeservice.command.data.EmployeeRespository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class EmployeeEventHandler {
    @Autowired
    private EmployeeRespository employeeRespository;

    @EventHandler
    public void on(EmployeeCreatedEvent event){
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeRespository.save(employee);
    }

    @EventHandler
    public void on(EmployeeUpdatedEvent event){
        Employee employee = employeeRespository.findById(event.getId())
                .orElseThrow(() -> new NoSuchElementException("Not found Employee with id="+ event.getId()));

        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employee.setKin(event.getKin());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRespository.save(employee);
    }
}
