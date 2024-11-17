//package com.poo.parking_api.service;
//
//import com.poo.parking_api.domain.reservation.Reservation;
//import com.poo.parking_api.domain.ticket.Ticket;
//import com.poo.parking_api.domain.user.User;
//import com.poo.parking_api.domain.vehicle.Vehicle;
//import com.poo.parking_api.repository.ReservationRepository;
//import com.poo.parking_api.service.UserService;
//import com.poo.parking_api.service.VehicleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ReservationService {
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private VehicleService vehicleService;
//
//    // Método para criar uma nova reserva
//    public Reservation createReservation(Reservation reservation, String vehicleSelection, String vehiclePlate) throws Exception {
//        // Se for uma reserva de um novo veículo
//        if ("new".equals(vehicleSelection)) {
//            Vehicle newVehicle = new Vehicle();
//            newVehicle.setPlate(vehiclePlate);
//            // Salvar o novo veículo
//            newVehicle = vehicleService.create(newVehicle);
//            reservation.setVehicle(newVehicle);
//        }
//        // Se for para um veículo existente
//        else if ("existing".equals(vehicleSelection)) {
//            Vehicle existingVehicle = vehicleService.findByPlate(vehiclePlate); // Encontrar o veículo pelo número da placa
//            if (existingVehicle == null) {
//                throw new Exception("Veículo não encontrado!");
//            }
//            reservation.setVehicle(existingVehicle);
//        } else {
//            throw new Exception("Seleção de veículo inválida!");
//        }
//
//        // Associar o usuário que está criando a reserva
//        User user = userService.findByName(reservation.getCustomerName());
//        if (user == null) {
//            throw new Exception("Usuário não encontrado!");
//        }
//        reservation.setUser(user);
//        reservation.setParking(user.getParking());
//
//        // Definir os horários de início e término
//        reservation.setDateStart(LocalDateTime.now());
//        reservation.setDateEnd(LocalDateTime.now().plusHours(5)); // Por exemplo, 5 horas de reserva
//
//        // Status inicial da reserva
//        reservation.setStatus("PENDING");
//
//        // Salvar a reserva no banco de dados
//        return reservationRepository.save(reservation);
//    }
//
//    // Método para completar a reserva
//    public String completeReservation(String id) {
//        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
//
//        if (optionalReservation.isPresent()) {
//            Reservation reservation = optionalReservation.get();
//            reservation.setStatus("COMPLETED");
//            reservationRepository.save(reservation);
//            return "Reservation completed successfully!";
//        } else {
//            return "Reservation not found!";
//        }
//    }
//
//    // Listar todas as reservas
//    public List<Reservation> getAllReservations() {
//        return reservationRepository.findAll();
//    }
//
//    // Buscar reserva por ID
//    public Reservation getReservationById(String id) {
//        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found!"));
//    }
//}
