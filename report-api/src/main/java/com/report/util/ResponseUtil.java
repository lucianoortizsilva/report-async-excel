package com.report.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ResponseUtil {

	public static ResponseEntity<String> ok(final String message) {
		return ResponseEntity.ok("Relatório sendo processado. Aguarde via e-mail");
	}

	public ResponseEntity<String> cathException(final Exception e) {
		log.error(e.getMessage(), e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}