package com.example.tourManagement.repository;

import com.example.tourManagement.entity.ItineraryLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryLocationRepository extends JpaRepository<ItineraryLocation, Integer> {
}
