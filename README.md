### Tecnologias

- <img src="https://badges.aleen42.com/src/java.svg" alt="badge"/> 
- <img src="https://badges.aleen42.com/src/golang.svg" alt="badge"/> 
- <img src="https://badges.aleen42.com/src/docker.svg" alt="badge"/> 

### O que é ?
Uma aplicação que disponibiliza uma API para gerar um relatóro EXCEL de forma assíncrona, que exibe dados FAKE do catalogo Netflix.

### Arquitetura
1º O projeto report-api fornece uma API para gerar um relatório;\
2º Tem um container-rabbitmq, que irá guardar a mensagem na fila QUEUE-REPORT recebidas da API;\
3º O projeto report-consumer, consome mensagens da fila QUEUE-REPORT, gera o relatório e envia para o e-mail;

![](https://github.com/lucianoortizsilva/report-async-excel/blob/main/static/arquiteturaComFundo.png?raw=true)\

### Como rodar
- Execute na raiz do projeto o comando `docker-compose up`
- Acesse a API POST: `http://localhost:5000/catalogo-netflix/download/async/excel`
```javascript
{
	"releaseYear" : 2015,
	"emailTo": "mymail@mail.com"
}
``` 
