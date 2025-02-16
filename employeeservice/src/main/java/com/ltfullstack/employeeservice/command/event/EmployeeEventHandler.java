package com.ltfullstack.employeeservice.command.event;

import com.ltfullstack.employeeservice.command.data.Employee;
import com.ltfullstack.employeeservice.command.data.EmployeeRespository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Slf4j
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
    @DisallowReplay
    public void on(EmployeeUpdatedEvent event){
        Employee employee = employeeRespository.findById(event.getId())
                .orElseThrow(() -> new NoSuchElementException("Not found Employee with id="+ event.getId()));

        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employee.setKin(event.getKin());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRespository.save(employee);
    }

    @EventHandler
    @DisallowReplay
    public void on(EmployeeDeletedEvent event){
        try{
            Employee employee = employeeRespository.findById(event.getId())
                    .orElseThrow(() -> new NoSuchElementException("Not found Employee with id="+ event.getId()));
            employeeRespository.delete(employee);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

}
