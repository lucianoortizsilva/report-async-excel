package rotas

import (
	"api/src/controllers"
	"net/http"
)

var rotasReports = []Rota{
	{
		URI:    "/catalogo-netflix/download/async/excel",
		Metodo: http.MethodPost,
		Funcao: controllers.GenerateReportAsync,
	},
}
