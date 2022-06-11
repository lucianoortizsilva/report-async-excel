import { Container, Navbar, Nav } from 'react-bootstrap';
import netflix from '../../assets/netflix.svg'
import '../Cabecalho/style.css'

function Cabecalho(){
        return (
            <div>
                <Navbar bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand>
                    <img src={netflix} className="d-inline-block align-top" height="50" width="100"/>
                    </Navbar.Brand>
                    <Nav className="me-auto">
                        <Navbar.Text className="mt-1">
                            <h5>Relatório de Séries & Filmes</h5>                            
                        </Navbar.Text>                    
                    </Nav>
                </Container>
                </Navbar>                
            </div>
        )
}

export default Cabecalho;
