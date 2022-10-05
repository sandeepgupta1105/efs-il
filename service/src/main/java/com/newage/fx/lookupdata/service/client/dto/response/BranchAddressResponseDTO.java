package com.newage.fx.lookupdata.service.client.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchAddressResponseDTO {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String zipCode;

}
