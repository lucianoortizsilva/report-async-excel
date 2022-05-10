package com.report.queue;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Report implements Serializable {

	private static final long serialVersionUID = 6653157440518279481L;
	private Integer releaseYear;
	private String emailTo;

}