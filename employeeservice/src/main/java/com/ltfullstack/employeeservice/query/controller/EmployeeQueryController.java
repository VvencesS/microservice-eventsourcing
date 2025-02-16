package com.ltfullstack.employeeservice.query.controller;

import com.ltfullstack.employeeservice.query.model.EmpolyeeResponseModel;
import com.ltfullstack.employeeservice.query.queries.GetAllEmployeeQuery;
import com.ltfullstack.employeeservice.query.queries.GetDetailEmployeeQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<EmpolyeeResponseModel> getAllEmployee(@RequestParam(required = false, defaultValue = "false") Boolean isDisciplined){
        return queryGateway.query(new GetAllEmployeeQuery(isDisciplined),
                ResponseTypes.multipleInstancesOf(EmpolyeeResponseModel.class)).join();
    }

    @GetMapping("/{employeeId}")
    public EmpolyeeResponseModel getDetailEmployee(@PathVariable String employeeId){
        return queryGateway.query(new GetDetailEmployeeQuery(employeeId),ResponseTypes.instanceOf(EmpolyeeResponseModel.class)).join();
    }
}
