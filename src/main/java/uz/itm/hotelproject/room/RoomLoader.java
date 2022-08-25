package uz.itm.hotelproject.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomLoader {
    private Integer id;

    private Integer number;

    private String floor;

    private String size;

    private Integer hotelId;
}
