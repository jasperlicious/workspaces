package com.accenture.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.hotel.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	//selecting the list of available rooms.
	@Query(value="SELECT * FROM rooms where (room_reserved is null or room_reserved = ?1) and room_type != 'Unavailable'", nativeQuery=true)
	public List<Room> findbyReservation(String none);
	
	@Modifying //required for update because you are modifying data in database.
	@Transactional //required for update/delete custom query.
	@Query(value="Update rooms set room_reserved = ?1 where room_id=?2", nativeQuery=true)
	public void updateReservation(String user, int id);
	
	//selecting the list of available rooms.
	@Query(value="SELECT * FROM rooms where room_reserved = ?1", nativeQuery=true)
	public List<Room> findReservation(String user);	
	
	@Query(value="SELECT * FROM rooms where room_reserved is null or room_reserved = ''", nativeQuery=true)
	public List<Room> findNoReservation();
	
	@Query(value="SELECT * FROM rooms where room_reserved != null or room_reserved != ''", nativeQuery=true)
	public List<Room> findAllReservation();	 
	
	@Query(value="SELECT * FROM rooms where room_number = ?1", nativeQuery=true)
	public Room findbyRoomNumber(int roomNumber);		
	
	@Query(value = "Select COUNT(room_id) From rooms", nativeQuery = true)
	public int countRoom();	
	
	@Query(value="SELECT * FROM rooms where room_id = ?1", nativeQuery=true)
	public Room findbyRoomId (int roomID);

}
