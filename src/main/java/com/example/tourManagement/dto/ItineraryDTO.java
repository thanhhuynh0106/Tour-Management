package com.example.tourManagement.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItineraryDTO {
    private Integer itineraryId;
    private Integer tourId;
    private Integer dayNumber;
    private String title;
    private String descriptions;
}