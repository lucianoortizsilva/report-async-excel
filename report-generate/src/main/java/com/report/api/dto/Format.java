package com.report.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;

public enum Format {

	NUMERIC {
		@Override
		public void formatCell(final Sheet sheet, final Cell cell, final Column column) {
			final Integer cellValue = Integer.valueOf(column.getValue());
			cell.setCellValue(cellValue);
			cell.setCellStyle(creatCellStayleBasic(sheet));
			cell.getCellStyle().setAlignment(column.getHorizontalAlignment());
			sheet.setColumnWidth(column.getIndice(), column.getSizeHorizontal());
		}
	},
	DATE {
		@Override
		public void formatCell(final Sheet sheet, final Cell cell, final Column column) {
			final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			final LocalDate localDate = LocalDate.parse(column.getValue(), df);
			final DataFormat dataFormat = sheet.getWorkbook().createDataFormat();
			cell.setCellStyle(creatCellStayleBasic(sheet));
			cell.getCellStyle().setAlignment(column.getHorizontalAlignment());
			cell.getCellStyle().setDataFormat(dataFormat.getFormat("dd/MM/yyyy"));
			cell.setCellValue(localDate);
			sheet.setColumnWidth(column.getIndice(), column.getSizeHorizontal());
		}
	},
	TEXT {
		@Override
		public void formatCell(final Sheet sheet, final Cell cell, final Column column) {
			cell.setCellStyle(creatCellStayleBasic(sheet));
			cell.getCellStyle().setAlignment(column.getHorizontalAlignment());
			cell.setCellValue(column.getValue());
			sheet.setColumnWidth(column.getIndice(), column.getSizeHorizontal());
		}
	},
	MONETARY {
		@Override
		public void formatCell(final Sheet sheet, final Cell cell, final Column column) {
			final BigDecimal cellValue = new BigDecimal(column.getValue());
			final DataFormat dataFormat = sheet.getWorkbook().createDataFormat();
			cell.setCellStyle(creatCellStayleBasic(sheet));
			cell.getCellStyle().setDataFormat(dataFormat.getFormat("#,##0.00;-#,##0.00"));
			cell.getCellStyle().setAlignment(column.getHorizontalAlignment());
			cell.setCellValue(cellValue.doubleValue());
			sheet.setColumnWidth(column.getIndice(), column.getSizeHorizontal());
		}
	};

	public abstract void formatCell(final Sheet sheet, final Cell cell, final Column column);

	private static CellStyle creatCellStayleBasic(final Sheet sheet) {
		final CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		return cellStyle;
	}

}