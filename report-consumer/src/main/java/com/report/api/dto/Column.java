package com.report.api.dto;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Column {

	private Integer indice;
	private Integer sizeHorizontal;
	private String value = StringUtils.EMPTY;
	private Format format = Format.TEXT;
	private HorizontalAlignment horizontalAlignment;

}