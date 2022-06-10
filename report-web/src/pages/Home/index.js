import React, {useState} from 'react';
import { Button, Container, Form, Spinner } from "react-bootstrap";
import { toast } from 'react-toastify';
import api from '../../services/api';


function Home() {
    
    const anos = ['2020', '2019', '2018', '2017', '2016', '2015', '2014', '2013', '2012', '2011', '2010'];
    const [isLoading, setLoading] = useState(false);
    const [ano, setAno] = useState('');
    const [email, setEmail] = useState('');    
    const [labelBotao, setLabelBotao] = useState('Enviar');
    const [spinnerBotao, setSpinnerBotao] = useState('visually-hidden');
    const [requestBody, setRequestBody] = useState({releaseYear: 0, emailTo: ''});

    const enviar = (event) => {
        event.preventDefault();
        setLoading(true);
        setSpinnerBotao('');
        setLabelBotao('Enviando');        
        requestBody.emailTo = email;
        requestBody.releaseYear = parseInt(ano, 10);
        const body = JSON.stringify(requestBody);
        const headers = [
            {"Access-Control-Allow-Origin": "*"}, 
            {"Access-Control-Allow-Headers": "access-control-allow-origin, access-control-allow-headers"}
        ]
        api.post(`/download/async/excel`, body, headers)
        .then(response => {
            console.log(response);
            toast.success(response.data);
            setRequestBody({})
            setEmail('');
            setAno('');
        })
        .catch(error =>{
            console.log(error);
            if(error.code = 'ERR_NETWORK'){
                toast.error("Serviço temporariamente indisponível");
            } else{
                toast.error(error.response.data.erro);
            }
        }).finally(()=>{
            setLoading(false);
            setSpinnerBotao('visually-hidden');
            setLabelBotao('Enviar');
        });
    }



    return(
        <div className="mt-5">
            <Container>
                <Form className="mb-8" onSubmit={enviar}>
                    
                    <Form.Group className="mb-3" controlId="email">
                        <Form.Label className="fw-bold">E-mail</Form.Label>
                        <Form.Control name="email" 
                                        type="email" 
                                        value={email}
                                        disabled={isLoading}
                                        readOnly={isLoading}
                                        onChange={event => setEmail(event.target.value)}
                                        placeholder="Informe o e-mail para enviar o relatório" 
                                        required/>
                    </Form.Group>
                    
                    <Form.Group className="mb-3">
                        <Form.Label className="fw-bold" htmlFor="ano">Ano de Lançamento</Form.Label>
                        <Form.Select name="ano" 
                                        value={ano}
                                        disabled={isLoading}
                                        onChange={event => setAno(event.target.value)}
                                        placeholder="Selecione ..."
                                        required>

                            <option value="" disabled>Selecione ...</option>
                            {
                                anos.map((obj) => {
                                    return(
                                        <option key={obj}>
                                            {obj}
                                        </option>
                                    )
                                })
                            }                               
                        </Form.Select>
                    </Form.Group>
                    <Button variant="primary" type='submit' disabled={isLoading}>
                        <Spinner as="span" 
                                variant='danger' 
                                animation="grow" 
                                size="sm" 
                                role="status"
                                aria-hidden="false"
                                className={spinnerBotao}
                                disabled={isLoading}/> {labelBotao}
                    </Button>
                </Form>
            </Container>
        </div>
    )

}

export default Home;
