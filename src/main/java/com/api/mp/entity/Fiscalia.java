package com.api.mp.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data @Entity
@Table(name="fiscalia")
public class Fiscalia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String agency;
	private String address;
	private String phone;
	private String department;
	private String town;
}