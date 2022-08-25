package uz.itm.hotelproject.hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        Hotel addingHotel=new Hotel();
        addingHotel.setName(hotel.getName());
        hotelRepository.save(addingHotel);
        return "added successfully";
    }
    @GetMapping
    public List<Hotel>getHotels(){
        return hotelRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "deleted successfully";

    }
    @PutMapping("/{id}")
    public String updateHotel(@PathVariable Integer id,@RequestBody Hotel hotel){
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()){
            Hotel editingHotel = hotelOptional.get();
            editingHotel.setName(hotel.getName());
            return "updated successfully";
        }
        return "there is no hotel with this name";
    }
}
