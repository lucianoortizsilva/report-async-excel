import { Button, Container, Navbar } from 'react-bootstrap';
import './App.css';
import logo from './assets/logo.svg'

function App() {
  return (
    <div>
      <Topo/>
    </div>
  );
}

const Topo = () =>{
  return(
    <Navbar bg="dark" variant="dark">
      <Container>
        <Navbar.Brand>
          <img src={logo} className="d-inline-block align-top"/>
        </Navbar.Brand>
      </Container>
    </Navbar>
  )
}

export default App;