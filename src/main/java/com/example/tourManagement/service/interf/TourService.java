package com.example.tourManagement.service.interf;



import com.example.tourManagement.dto.ResponseDTO;
import com.example.tourManagement.dto.TourDTO;
import com.example.tourManagement.entity.Tour;


import java.util.List;


public interface TourService {
    ResponseDTO createTour(TourDTO TourRequest);
    ResponseDTO getTourById(Integer tourId);
    ResponseDTO getAllTour();
    ResponseDTO updateTour(Integer tourId, TourDTO TourDTO);
    ResponseDTO deleteTour(Integer tourId);
}