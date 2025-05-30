package com.example.tourManagement.dto;


import lombok.Data;

@Data
public class ItineraryLocationDTO {
    private Integer itineraryId;
    private Integer locationId;
    private Integer sequenceOrder;

    private String locationName;
    private String locationDescription;
    private Double latitude;
    private Double longitude;
}