package com.report.api;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.report.api.dto.Column;
import com.report.api.dto.Line;

import lombok.experimental.UtilityClass;

/**
 * 
 * API para gerar EXCEL
 * 
 * @author lucianoortizsilva@gmail.com
 * @since set/2020
 *
 */
@UtilityClass
public class ExcelApi {
	
	
	/**
	 * 
	 * @param lines list de objetos com dados de cada linha
	 * @param headers nomes para exibir no cabecalho, em ordem de exibicao
	 * @param format exemplo: '.xlsx' ou '.xls'
	 * @param abaName titulo que sera exibido na aba
	 * 
	 * @return {@link File} do arquivo gerado
	 * 
	 * @throws IOException
	 * 
	 */
	public File create(final List<Line> lines, final String[] headers, final String format, final String abaName) throws IOException  {
		final Workbook workbook = createWorkbook(format);
		final Sheet sheet = createSheet(workbook, abaName);
		createHeaders(sheet, headers);
		createData(sheet, lines);
		return writeFile(workbook, format);
	}
	
	
	
	/**
	 * 
	 * @param lines list de objetos com dados de cada linha
	 * @param headers nomes para exibir no cabecalho, em ordem de exibicao
	 * @param format exemplo: '.xlsx' ou '.xls'
	 * @param abaName titulo que sera exibido na aba
	 * 
	 * @return {@link String} base64 do arquivo gerado
	 * 
	 * @throws IOException
	 * 
	 */
	public String createBase64(final List<Line> lines, final String[] headers, final String format, final String abaName) throws IOException  {
		final File file = create(lines, headers, format, abaName);
		String base64 = null;
		if(!Objects.isNull(file)) {
			final byte[] fileInBytes = FileUtils.readFileToByteArray(file);
			final byte[] bytesBase64 = Base64.encodeBase64(fileInBytes);
			base64 = StringUtils.newStringUtf8(bytesBase64);
		}
		return base64;
	}
	
	
	
	private static File writeFile(final Workbook workbook, final String format) throws IOException {
		final String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		final File file = File.createTempFile(filename, format);
		final FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();
		return file;
	}
	
	
	
	private static Workbook createWorkbook(final String format) {
		if (".xls".equalsIgnoreCase(format)) {
			return new HSSFWorkbook();
		} else {
			return new XSSFWorkbook();
		}
	}
	
	
	
	private static Sheet createSheet(final Workbook workbook, final String name) {
		final Sheet sheet = workbook.createSheet();
		final int indexAba = 0;
		workbook.setSheetName(indexAba, name);
		return sheet;
	}
	
	
	
	private static void createHeaders(final Sheet sheet, final String[] headers) {
		final int indiceRow = 0;
		final Row row = sheet.createRow(indiceRow);
		final CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		final Font font = sheet.getWorkbook().createFont();
		font.setBold(Boolean.TRUE);
		for (int i = 0; i < headers.length; i++) {
			final Cell cell = row.createCell(i);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(headers[i]);
			sheet.autoSizeColumn(i);
		}
	}
	
	
	
	private static void createData(final Sheet sheet, final List<Line> lines) {
		int countLine = 1;
		for (final Line line : lines) {
			final Row row = sheet.createRow(countLine);
			for (final Column column : line.getColumns()) {
				final Cell cell = row.createCell(column.getIndice());
				column.getFormat().formatCell(sheet, cell, column);
			}
			countLine++;
		}
	}
	
}