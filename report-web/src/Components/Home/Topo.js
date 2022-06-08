import React,{Component} from "react";
import { Container, Navbar } from 'react-bootstrap';
import logo from '../../assets/logo.svg'

class Topo extends Component{
    render(){
        return (
            <div>
                <Navbar bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand>
                    <img src={logo} className="d-inline-block align-top"/>
                    </Navbar.Brand>
                </Container>
                </Navbar>                
            </div>
        )
    }
}

export default Topo;