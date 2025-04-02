package io.swagger.service;

import io.swagger.entity.ReservationEntity;
import io.swagger.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

    public ReservationEntity createReservation(ReservationEntity reservation) {
        reservation.setStatus("Confirmed");
        return reservationRepository.save(reservation);
    }

    public Optional<ReservationEntity> updateReservation(Integer id, ReservationEntity updateData) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        optionalReservation.ifPresent(res -> {
            res.setDate(updateData.getDate());
            res.setTime(updateData.getTime());
            res.setGuests(updateData.getGuests());
            res.setStatus(updateData.getStatus());
            reservationRepository.save(res);
        });
        return optionalReservation;
    }
}
