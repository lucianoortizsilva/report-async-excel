package controllers

import (
	"api/src/config"
	"api/src/modelos"
	"api/src/respostas"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"

	"github.com/streadway/amqp"
)

func GenerateReportAsync(w http.ResponseWriter, r *http.Request) {

	fmt.Println("1º Convertendo requisicao em []bytes")
	corpoRequest, erro := ioutil.ReadAll(r.Body)
	if erro != nil {
		respostas.Erro(w, http.StatusUnprocessableEntity, erro)
		return
	}

	fmt.Println("2º Convertendo []bytes to struct")
	var report modelos.Report
	if erro = json.Unmarshal(corpoRequest, &report); erro != nil {
		respostas.Erro(w, http.StatusBadRequest, erro)
		return
	}

	fmt.Println("3º Validando campos obrigatorios")
	if erro = report.Preparar(); erro != nil {
		respostas.Erro(w, http.StatusBadRequest, erro)
		return
	}

	fmt.Println("4º Criando conexao com RabbitMQ")
	fmt.Println(config.RabbitMQUrl)
	connectRabbitMQ, erro := amqp.Dial(config.RabbitMQUrl)
	if erro != nil {
		panic(erro)
	}
	defer connectRabbitMQ.Close()

	fmt.Println("5º Criando instancia channel RabbitMQ")
	channelRabbitMQ, erro := connectRabbitMQ.Channel()
	if erro != nil {
		panic(erro)
	}
	defer channelRabbitMQ.Close()

	//fmt.Println("6º Declarando QUEUE 'QUEUE-REPORT'")
	//_, erro = channelRabbitMQ.QueueDeclare(
	//"QUEUE-REPORT", // queue name
	//true,           // durable
	//false,          // auto delete
	//false,          // exclusive
	//false,          // no wait
	//nil,            // arguments
	//)

	//if erro != nil {
	//panic(erro)
	//}

	fmt.Println("6º Criando mensagem para publicar")
	message := amqp.Publishing{
		ContentType: "text/plain",
		Body:        []byte(corpoRequest),
	}

	fmt.Println("7º Publicando mensagem")
	if erro := channelRabbitMQ.Publish(
		"",             // exchange
		"QUEUE-REPORT", // queue name
		false,          // mandatory
		false,          // immediate
		message,        // message to publish
	); erro != nil {
		respostas.Erro(w, http.StatusBadRequest, erro)
		return
	}

	respostas.JSON(w, http.StatusOK, "Relatório está sendo processado. Aguarde via e-mail")

}
