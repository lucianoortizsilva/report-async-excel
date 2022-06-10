import React, {useState} from 'react';
import { Button, Container, Form, Spinner } from "react-bootstrap";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Home() {

    const anos = ['2022', '2021', '2020', '2019', '2018', '2017', '2016', '2015'];
    const [isLoading, setLoading] = useState(false);
    const [ano, setAno] = useState('');
    const [email, setEmail] = useState('');    
    const [labelBotao, setLabelBotao] = useState('Enviar');
    const [spinnerBotao, setSpinnerBotao] = useState('visually-hidden');
    
    return(
        <div className="mt-5">
            <Container>
                <Form className="mb-8" onSubmit={enviar}>
                    
                    <Form.Group className="mb-3" controlId="email">
                        <Form.Label className="fw-bold">E-mail</Form.Label>
                        <Form.Control name="email" 
                                        type="email" 
                                        value={email}
                                        onChange={(e)=> setEmail(e.target.value)}
                                        placeholder="Informe o e-mail para enviar o relatório" 
                                        required/>
                    </Form.Group>
                    
                    <Form.Group className="mb-3">
                        <Form.Label className="fw-bold" htmlFor="ano">Ano de Lançamento</Form.Label>
                        <Form.Select name="ano" 
                                        value={ano}
                                        onChange={(e)=> setAno(e.target.value)}
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

    function enviar(){
        setLoading(true);
        setSpinnerBotao('');
        setLabelBotao('Enviando');
        console.log('Email...: ' + email);
        console.log('Ano.....: ' + ano);        
        toast.success("Feitooooooooo!");
    }

}
export default Home;
