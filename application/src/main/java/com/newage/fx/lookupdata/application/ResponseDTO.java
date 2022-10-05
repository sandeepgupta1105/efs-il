package com.newage.fx.lookupdata.application;

//import com.amazonaws.services.ec2.model.ResponseError;

import com.newage.fx.lookupdata.application.dto.ResponseError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private Integer httpStatus;

    private Boolean success;

    private Date timestamp;

    private Object result;

    private ResponseError error;

    public ResponseDTO(Integer httpStatus, Boolean success, Object result, ResponseError error) {

        this.httpStatus = httpStatus;
        this.success = success;
        this.error = error;
        this.result = result;
    }

    public Date getTimestamp() {

        return Calendar.getInstance().getTime();
    }


}
