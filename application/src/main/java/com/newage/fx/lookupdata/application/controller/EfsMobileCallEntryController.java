package com.newage.fx.lookupdata.application.controller;

import com.newage.fx.lookupdata.application.ResponseDTO;
import com.newage.fx.lookupdata.application.dto.mapper.CallEntryNxtToEfsMapper;
import com.newage.fx.lookupdata.service.ServiceLayer;
import com.newage.fx.lookupdata.service.client.MasterDataServiceClient;
import com.newage.fx.lookupdata.service.client.dto.request.CallEntryNxtRequestDTO;
import com.newage.fx.lookupdata.service.client.dto.response.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping(value = "/api/v1/efs-il/efs-mobile-callEntries")
public class EfsMobileCallEntryController {

    @Autowired
    private CallEntryNxtToEfsMapper nxtToEfsMapper;

    @Autowired
    private MasterDataServiceClient masterDataServiceClient;
    private final ServiceLayer serviceLayer;

    @Autowired
    public EfsMobileCallEntryController(ServiceLayer serviceLayer){
        this.serviceLayer=serviceLayer;
    }

    @PostMapping(value = "/create-call ",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createCallEntry(@RequestBody CallEntryNxtRequestDTO callEntryNxtRequestDTO,
                                                       @RequestParam String username,@RequestParam(required = false) String user_token) {

        log.info("Called: /api/v1/sales/efs-mobile-callEntries method createCallEntry ");
        ClientResponseDTO<CustomerResponseDTO> responseDTO= masterDataServiceClient.getCustomerById(callEntryNxtRequestDTO.getCustomerId());
        ClientResponseDTO<FollowUpResponseDTO> responseDTO1=masterDataServiceClient.getFollowupActionctionById(callEntryNxtRequestDTO.getFollowUpActionId());
        String result=serviceLayer.newCall(username,user_token,responseDTO.getResult().getCode(),"NEW","OTHERS",callEntryNxtRequestDTO.getMode().toString(), callEntryNxtRequestDTO.getNote(), callEntryNxtRequestDTO.getFollowupDate().toString(),responseDTO1.getResult().getName(),callEntryNxtRequestDTO.getDate().toString(),null,callEntryNxtRequestDTO.getLatitude().toString(),callEntryNxtRequestDTO.getLongitude().toString());
        ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE,result, null);
        return ResponseEntity.ok(response);




    }


    @GetMapping(value ="/get-call-list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getCallList(@RequestParam String username,@RequestParam(required = false) String user_token,
                                                   @RequestParam(required = false) String Salesmancode){
        EfsCallResponseDTO result = serviceLayer.getCallList(username,user_token,Salesmancode);
        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.OK.value(),Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);

    }

    @PutMapping(value = "/update-call",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateCall(@RequestParam String username,@RequestParam(required = false) String user_token,@RequestParam String call_id,
                                                  @RequestParam(required = false) String call_type,@RequestParam(required = false) String sub_type,@RequestParam(required = false) String desc,
                                                  @RequestParam(required = false) String call_mode,@RequestParam String run_date){

        String result = serviceLayer.updateCall(username,user_token,call_id,call_type,sub_type,desc,call_mode,run_date);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);

    }


    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> login(@RequestParam String username, @RequestParam String password) {

        EfsLoginResponseDTO result= serviceLayer.efsLoginAPI(username,password);

        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "/forgot-password",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> forgotPassword(@RequestParam String username){

        String result= serviceLayer.forgotPassword(username);
        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "/logout",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> logout(@RequestParam String username, @RequestParam String user_token){
           String result= serviceLayer.logout(username,user_token);
           ResponseDTO responseDTO= new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE,result,null);
           return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "/change-password",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> changePassword(@RequestParam String username,@RequestParam(required = false) String user_token,
                                                      @RequestParam(required = false) String new_password ){

        String result= serviceLayer.changePassword(username,user_token,new_password);
        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/customer-list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getEfsCustomerList(@RequestParam String username , @RequestParam(required = false) String user_token,
                                                          @RequestParam(required = false) String updateddata, @RequestParam(required = false) String lastmodifieddate){

        EfsCustomerListResponseDTO result=serviceLayer.customerList(username,user_token,updateddata,lastmodifieddate);
        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.OK.value(),Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/customer-by-searchkey",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getCustomerBySearchKey(@RequestParam String username,@RequestParam(required = false) String user_token,
                                                   @RequestParam(required = false) String searchkey){

        EfsCustomerListResponseDTO result= serviceLayer.getCustomerBySearchKey(username,user_token,searchkey);
        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping(value = "/employee-detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getEmployeeDetail(@RequestParam String username,@RequestParam(required = false) String user_token){
        EfsEmployeeDetailResponseDTO result = serviceLayer.employeeDetail(username,user_token);
        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.OK.value(),Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/get-salesman",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getSalesman(@RequestParam String username,@RequestParam(required = false) String user_token){
        EfsSalesmanResponseDTO result = serviceLayer.getSalesman(username,user_token);
        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.OK.value(),Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "/create-enquiry",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createEnquiry(@RequestParam String username,@RequestParam(required = false) String user_token,@RequestParam(required = false) String Salesmancode,
                                                     @RequestParam(required = false) String sCustcode,@RequestParam(required = false) String sSegmentcode,@RequestParam(required = false) String sPOR,
                                                     @RequestParam(required = false) String sPOL,@RequestParam(required = false) String sPOD,@RequestParam(required = false) String sFDC,@RequestParam(required = false) String sTerms,
                                                     @RequestParam(required = false)String sType,@RequestParam(required = false) String sRouted,@RequestParam(required = false) String sNvocc){
        String result = serviceLayer.newEnquiry(username,user_token,Salesmancode,sCustcode,sSegmentcode,sPOR,sPOL,sPOD,sFDC,sTerms,sType,sRouted,sNvocc);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value() ,Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/get-enquiry-list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getEnquiryList(@RequestParam String username,@RequestParam(required = false) String user_token,
                                                      @RequestParam(required = false) String Salesmancode){
        EfsEnquiryResponseDTO result = serviceLayer.getEnquiryList(username,user_token,Salesmancode);
        ResponseDTO responseDTO =new ResponseDTO(HttpStatus.OK.value(), Boolean.TRUE,result,null);
        return ResponseEntity.ok(responseDTO);
    }

}
