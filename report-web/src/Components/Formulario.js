import React, {Component} from "react";
import { Button, Container, Form } from "react-bootstrap";

class Formulario extends Component{

    constructor(props){
        super(props)
        this.state = {
            formulario: {
                email: '',
                ano: ''
            },
            anos: ['2022', '2021', '2020', '2019', '2018', '2017', '2016', '2015']
        }
        this.enviar = this.enviar.bind(this)
    }

    render(){
        return(
            <div className="mt-5">
                <Container>
                    <Form className="mb-8" onSubmit={this.enviar}>
                        
                        <Form.Group className="mb-3" controlId="email">
                            <Form.Label className="fw-bold">E-mail</Form.Label>
                            <Form.Control name="email" 
                                          type="email" 
                                          value={this.state.formulario.email}
                                          onChange={this.enviar} 
                                          placeholder="Informe o e-mail para enviar o relatório" 
                                          required/>
                        </Form.Group>
                        
                        <Form.Group className="mb-3">
                            <Form.Label className="fw-bold" htmlFor="ano">Ano de Lançamento</Form.Label>
                            <Form.Select name="ano" 
                                         value={this.state.formulario.ano}
                                         onChange={this.enviar}                                          
                                         placeholder="Selecione ..."
                                         required>

                                <option value="" disabled>Selecione ...</option>
                                {
                                    this.state.anos.map((obj) => {
                                        return(
                                            <option key={obj}>
                                                {obj}
                                            </option>
                                        )
                                    })
                                }                               
                            </Form.Select>
                        </Form.Group>

                        <Button type="submit">Enviar</Button>
                    </Form>
                </Container>
            </div>
        )
    }

    enviar(event){
        let form = this.state.formulario;                
        form[event.target.name] = event.target.value;        
        this.setState({formulario:form})
        if(event.type === 'submit'){
            console.log('Email...: ' + form.email);        
            console.log('Ano.....: ' + form.ano);
            alert('Dados enviados com sucesso!');
        }
    }

}

export default Formulario;
