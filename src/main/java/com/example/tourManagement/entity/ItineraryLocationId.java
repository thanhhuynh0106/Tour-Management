package com.example.tourManagement.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ItineraryLocationId implements Serializable {

    private Integer itineraryId;
    private Integer locationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItineraryLocationId)) return false;
        ItineraryLocationId that = (ItineraryLocationId) o;
        return Objects.equals(itineraryId, that.itineraryId) &&
                Objects.equals(locationId, that.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itineraryId, locationId);
    }
}
