package com.accenture.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private int id;
	
	@Column(name = "room_number", nullable = false, unique = true, length = 45)
	private int number;
	
	@Column(name = "room_type", nullable = false, length = 64)
	private String type;
	
	@Column(name = "room_start")
	private String start;
	
	@Column(name = "room_end")
	private String end;		
	
	@Column(name = "room_reserved")
	private String reserved;
	
	public Room() {
		
	}
	
	public Room(int id, int number, String type, String start, String end, String reserved) {
		this.id = id;
		this.number = number;
		this.type = type;
		this.start = start;
		this.end = end;
		this.reserved = reserved;
	}
	
	public Room(int number, String type, String start, String end, String reserved) {
		this.number = number;
		this.type = type;
		this.start = start;
		this.end = end;
		this.reserved = reserved;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	
	
}
