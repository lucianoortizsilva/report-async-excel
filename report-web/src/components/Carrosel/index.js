import React, {useState} from 'react';
import { Carousel } from 'react-bootstrap';
import IMG_1 from '../../assets/1.jpg';
import IMG_2 from '../../assets/2.jpg';
import IMG_3 from '../../assets/3.jpg';
import IMG_4 from '../../assets/4.jpg';
import IMG_5 from '../../assets/5.jpg';
import IMG_6 from '../../assets/6.jpg';
import IMG_7 from '../../assets/7.jpg';
import IMG_8 from '../../assets/8.jpg';
import IMG_9 from '../../assets/9.jpg';
import IMG_10 from '../../assets/10.jpg';
import './style.css'

function Carrosel() {

    const [index, setIndex] = useState(0);
    
    const selecionado = (selectedIndex, e) => {
      setIndex(selectedIndex);
    };

    return (
        <div className="carrosel">
			<Carousel activeIndex={index} onSelect={selecionado} interval={3000}>
			  <Carousel.Item>
				<img src={`${IMG_1}`} className="d-block w-100"/>
			  </Carousel.Item>
			  <Carousel.Item>
				<img src={`${IMG_2}`} className="d-block w-100"/>
			  </Carousel.Item>
			  <Carousel.Item>
				<img src={`${IMG_3}`} className="d-block w-100"/>
			  </Carousel.Item>
			  <Carousel.Item>
				<img src={`${IMG_4}`} className="d-block w-100"/>
			  </Carousel.Item>
			  <Carousel.Item>
				<img src={`${IMG_5}`} className="d-block w-100"/>
			  </Carousel.Item>
              <Carousel.Item>
				<img src={`${IMG_6}`} className="d-block w-100"/>
			  </Carousel.Item>
              <Carousel.Item>
				<img src={`${IMG_7}`} className="d-block w-100"/>
			  </Carousel.Item>
              <Carousel.Item>
				<img src={`${IMG_8}`} className="d-block w-100"/>
			  </Carousel.Item>
              <Carousel.Item>
				<img src={`${IMG_9}`} className="d-block w-100"/>
			  </Carousel.Item>
              <Carousel.Item>
				<img src={`${IMG_10}`} className="d-block w-100"/>
			  </Carousel.Item>
		  </Carousel>
        </div>
    )
}

export default Carrosel;
