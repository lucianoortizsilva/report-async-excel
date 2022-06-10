import RoutesApp from './routes';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <div>    
      <RoutesApp/>
      <ToastContainer autoClose={5000}/>
    </div>
  );
}

export default App;