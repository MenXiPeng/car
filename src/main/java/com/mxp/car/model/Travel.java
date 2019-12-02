package com.mxp.car.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/26
 * TIME: 20:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Travel {
    private Integer id;
    private String carNum;
    private String carType;
    private String owner;
    private String address;
    private String useType;
    private String brandType;
    private String idenCode;
    private String engineNUm;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDate registeredDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDate issueDate;
    private String fileNum;
    private String loadNum;
    private Integer totalQuality;
    private Integer curbQuality;
    private Integer loadQuality;
    private Integer profileSize;
    private Integer tractionQuality;
    private String testRecord;
    private String barNum;
    private String remarks;
    private Long commissionId;
    private Long travelId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;
    private byte isMainTravel;
}
