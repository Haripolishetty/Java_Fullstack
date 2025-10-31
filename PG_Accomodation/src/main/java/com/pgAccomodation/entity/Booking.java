package com.pgAccomodation.entity;

import java.time.*;

import jakarta.persistence.*;


@Entity
@Table(name = "booking")
public class Booking {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public Booking() {}
    
	public Booking(Long id, Tenant tenant, Accommodation accommodation, LocalDate startDate, LocalDate endDate,
			String status, LocalDateTime createdAt) {
		this.id = id;
		this.tenant = tenant;
		this.accommodation = accommodation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "Booking [id=" + id + ", tenant=" + tenant + ", accommodation=" + accommodation + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status + ", createdAt=" + createdAt + "]";
	}
    
}
