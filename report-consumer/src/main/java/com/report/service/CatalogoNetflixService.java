package com.report.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.stereotype.Service;

import com.report.api.ExcelApi;
import com.report.api.dto.Column;
import com.report.api.dto.Format;
import com.report.api.dto.Line;
import com.report.model.CatalogoNetflix;
import com.report.repository.CatalogoNetflixRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatalogoNetflixService {

	private static final String[] HEADERS = { "Id", "Type", "Title", "Director", "Cast", "Country", "Date Added", "Release Year", "Rating", "Duration", "Listed In", "Description" };
	private static final String ABA_NAME = "Catálogo NETFLIX";
	private static final String FORMAT = ".xlsx";

	private CatalogoNetflixRepository repository;

	public File generateFile(final Integer releaseYear) throws IOException {
		File file = null;
		final List<Line> lines = this.generateLines(releaseYear);
		if (CollectionUtils.isNotEmpty(lines)) {
			file = ExcelApi.create(lines, HEADERS, FORMAT, ABA_NAME);
		}
		return file;
	}

	private List<Line> generateLines(final Integer releaseYear) {
		final List<CatalogoNetflix> catalogos = this.repository.findByReleaseYear(releaseYear);
		final List<Line> lines = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(catalogos)) {
			for (final CatalogoNetflix catalogoNetflix : catalogos) {
				final Column c0 = new Column(0, 7000, catalogoNetflix.getId().toString(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c1 = new Column(0, 7000, catalogoNetflix.getType(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c2 = new Column(0, 7000, catalogoNetflix.getTitle(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c3 = new Column(0, 7000, catalogoNetflix.getDirector(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c4 = new Column(0, 7000, catalogoNetflix.getCast(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c5 = new Column(0, 7000, catalogoNetflix.getCountry(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c6 = new Column(0, 7000, catalogoNetflix.getDateAdded(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c7 = new Column(0, 7000, catalogoNetflix.getReleaseYear().toString(),  Format.TEXT, HorizontalAlignment.CENTER);
				final Column c8 = new Column(0, 7000, catalogoNetflix.getRating(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c9 = new Column(0, 7000, catalogoNetflix.getDuration(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c10 = new Column(0, 7000, catalogoNetflix.getListedIn(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Column c11 = new Column(0, 7000, catalogoNetflix.getDescription(),  Format.TEXT, HorizontalAlignment.LEFT);
				final Line line = new Line(c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11);
				lines.add(line);
			}
		}
		return lines;
	}
	
}