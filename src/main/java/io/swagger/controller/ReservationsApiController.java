package io.swagger.controller;

import io.swagger.api.ReservationsApi;
import io.swagger.model.NewReservation;
import io.swagger.model.Reservation;
import io.swagger.model.ReservationUpdate;
import io.swagger.entity.ReservationEntity;
import io.swagger.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationsApiController implements ReservationsApi {

    @Autowired
    private ReservationService reservationService;

    @Override
    public ResponseEntity<List<Reservation>> reservationsGet() {
        List<ReservationEntity> entities = reservationService.getAllReservations();

        List<Reservation> dtos = entities.stream().map(entity -> {
            Reservation dto = new Reservation();
            dto.setId(entity.getId());
            dto.setCustomerName(entity.getCustomerName());
            dto.setDate(LocalDate.parse(entity.getDate()));
            dto.setTime(entity.getTime());
            dto.setGuests(entity.getGuests());
            dto.setStatus(entity.getStatus());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<Void> reservationsPost(NewReservation newReservation) {
        ReservationEntity entity = new ReservationEntity();
        entity.setCustomerName(newReservation.getCustomerName());
        entity.setDate(String.valueOf(newReservation.getDate()));
        entity.setTime(newReservation.getTime());
        entity.setGuests(newReservation.getGuests());

        reservationService.createReservation(entity);
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<Void> reservationsReservationIdPatch(Integer reservationId, ReservationUpdate update) {
        ReservationEntity updated = new ReservationEntity();
        updated.setDate(String.valueOf(update.getDate()));
        updated.setTime(update.getTime());
        updated.setGuests(update.getGuests());
        updated.setStatus(update.getStatus());

        if (reservationService.updateReservation(reservationId, updated).isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
