package uz.itm.hotelproject.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.itm.hotelproject.hotel.HotelRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping
    public String addRoom(@RequestBody RoomLoader roomLoader) {
        Room room = new Room();
        room.setNumber(room.getNumber());
        room.setFloor(roomLoader.getFloor());
        room.setSize(roomLoader.getSize());
        room.setHotel(hotelRepository.findById(roomLoader.getHotelId()).get());
        roomRepository.save(room);
        return "added successfully";
    }

    @GetMapping("/{hotelId}")
    public Page<Room> getRoomsByHotelId(@PathVariable Integer hotelId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAllByHotel_Id(hotelId, pageable);
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        roomRepository.deleteById(id);
        return "deleted successfully";
    }

    @PutMapping("/{id}")
    public String updateRoom(@PathVariable Integer id, @RequestBody RoomLoader roomLoader) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        Room room = roomOptional.get();
        room.setNumber(roomLoader.getNumber());
        room.setFloor(roomLoader.getFloor());
        room.setSize(roomLoader.getSize());
        room.setHotel(hotelRepository.findById(roomLoader.getHotelId()).get());
        roomRepository.save(room);

        return "updated successfully";
    }


}
