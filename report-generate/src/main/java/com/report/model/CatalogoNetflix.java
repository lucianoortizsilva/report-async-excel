package com.report.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATALOGO_NETFLIX")
public class CatalogoNetflix implements Serializable {

	private static final long serialVersionUID = 1634330088123787362L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "show_id")
	private Long id;
	private String type;
	private String title;
	private String director;
	private String cast;
	private String country;
	private String dateAdded;
	private Integer releaseYear;
	private String rating;
	private String duration;
	private String listedIn;
	private String description;

}