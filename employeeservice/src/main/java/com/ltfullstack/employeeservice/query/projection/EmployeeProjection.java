package com.ltfullstack.employeeservice.query.projection;

import com.ltfullstack.employeeservice.command.data.Employee;
import com.ltfullstack.employeeservice.command.data.EmployeeRespository;
import com.ltfullstack.employeeservice.query.model.EmpolyeeResponseModel;
import com.ltfullstack.employeeservice.query.queries.GetAllEmployeeQuery;
import com.ltfullstack.employeeservice.query.queries.GetDetailEmployeeQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeProjection {
    @Autowired
    private EmployeeRespository employeeRespository;

    @QueryHandler
    public List<EmpolyeeResponseModel> handle(GetAllEmployeeQuery query){
        List<Employee> employeeList = employeeRespository.findAllByIsDisciplined(query.getIsDisciplined());

        return employeeList.stream()
                .map(employee -> {
                    EmpolyeeResponseModel model = new EmpolyeeResponseModel();
                    BeanUtils.copyProperties(employee, model); // Ensure BeanUtils is used only for necessary fields
                    return model;
                })
                .collect(Collectors.toList());
    }

    @QueryHandler
    public EmpolyeeResponseModel handle(GetDetailEmployeeQuery query) throws Exception{
        Employee employee = employeeRespository.findById(query.getId()).orElseThrow(() -> new Exception("Employee not found"));
        EmpolyeeResponseModel model = new EmpolyeeResponseModel();
        BeanUtils.copyProperties(employee,model);
        return model;
    }
}
