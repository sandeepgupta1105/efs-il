package com.newage.fx.lookupdata.application.dto.mapper;

import com.newage.fx.lookupdata.service.client.EfsMobileServiceClient;
import com.newage.fx.lookupdata.service.client.MasterDataServiceClient;
import com.newage.fx.lookupdata.service.client.dto.request.CallEntryEfsMobileRequestDTO;
import com.newage.fx.lookupdata.service.client.dto.request.CallEntryNxtRequestDTO;
import com.newage.fx.lookupdata.service.client.dto.response.ClientResponseDTO;
import com.newage.fx.lookupdata.service.client.dto.response.CustomerResponseDTO;
import com.newage.fx.lookupdata.service.client.dto.response.FollowUpResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallEntryNxtToEfsMapper  {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MasterDataServiceClient masterDataServiceClient;

    @Autowired
    EfsMobileServiceClient efsMobileServiceClient;


    public CallEntryEfsMobileRequestDTO convertNxtRequestToEfsRequest(CallEntryNxtRequestDTO callEntryNxtRequestDTO){

        CallEntryEfsMobileRequestDTO callEntryEfsMobileRequestDTO=modelMapper.map(callEntryNxtRequestDTO, CallEntryEfsMobileRequestDTO.class);

       // callEntryEfsMobileRequestDTO.setUserToken();
        callEntryEfsMobileRequestDTO.setCallMode(callEntryNxtRequestDTO.getMode().toString());

       callEntryEfsMobileRequestDTO.setCallType(null);

        callEntryEfsMobileRequestDTO.setSubType(null);

        ClientResponseDTO<CustomerResponseDTO> responseDTO1= masterDataServiceClient.getCustomerById(callEntryNxtRequestDTO.getCustomerId());
        callEntryEfsMobileRequestDTO.setCusCode(responseDTO1.getResult().getCode());

        ClientResponseDTO<FollowUpResponseDTO> responseDTO2= masterDataServiceClient.getFollowupActionctionById(callEntryNxtRequestDTO.getFollowUpActionId());
        callEntryEfsMobileRequestDTO.setFollowAction(responseDTO2.getResult().getName());

        callEntryEfsMobileRequestDTO.setDesc(callEntryNxtRequestDTO.getNote());

        callEntryEfsMobileRequestDTO.setFollowDate(callEntryNxtRequestDTO.getFollowupDate().toString());

        callEntryEfsMobileRequestDTO.setCreatedDate(callEntryNxtRequestDTO.getDate().toString());



        return callEntryEfsMobileRequestDTO;
    }

}
