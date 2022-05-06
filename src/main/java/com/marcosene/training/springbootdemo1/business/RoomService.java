package com.marcosene.training.springbootdemo1.business;

import com.marcosene.training.springbootdemo1.data.Room;
import com.marcosene.training.springbootdemo1.data.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        Iterable<Room> roomsIt = roomRepository.findAll();
        List<Room> rooms = new ArrayList<>();
        roomsIt.forEach(rooms::add);
        rooms.sort(Comparator.comparing(Room::getRoomNumber));
        return rooms;
    }

    public Room getRoom(long roomId) {
        Optional<Room> optRoom = roomRepository.findById(roomId);
        return optRoom.isEmpty() ? null : optRoom.get();
    }
}
