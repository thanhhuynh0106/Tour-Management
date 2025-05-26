package com.example.tourManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "tour")
@Data
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Integer tourId;

    @Column(name = "tour_title")
    private String tourTitle;

    @Column(name = "tour_description")
    private String tourDescription;

    @Column(name = "tour_price", precision = 12, scale = 2)
    private BigDecimal tourPrice;

    @Column(name = "tour_duration")
    private Integer tourDuration;

    @Column(name = "tour_capacity")
    private Integer tourCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "tour_status", columnDefinition = "ENUM('active', 'inactive', 'draft')")
    private TourStatus tourStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_created_by", referencedColumnName = "user_id")
    private User tourCreatedBy;

    @ManyToMany
    @JoinTable(
            name = "tour_location",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> locations;

    public enum TourStatus {
        active,
        inactive,
        draft
    }
}