import React from 'react';
import product from '../../resources/product.svg';
import { useState, useEffect } from 'react';
import { useHistory } from 'react-router';
import itemsList from '../../data/itemsList.json';

function Homepage() {
    const [itemList, setItemList] = useState([]);
    const history = useHistory();

    useEffect(() => {
        const token = localStorage.getItem("key");
        if (token === null) {
            return history.push("/");
        }
        else {
            // axios.get("viewAllJobs").then
            //     ((response) => {
            //         getJobsList(response.data);
            //         console.log(response.data);
            //     });
            setItemList(itemsList);
        }
    }, [itemList, history]);


    return (


        <div className="ui five cards" onClick={() => console.log("sddf")}>
            {
                itemList.map((item) =>
                    <div className="ui raised card" key={item.id}>
                        <div className="ui card">
                            {/* <div className="image">
                                <img src={product} alt="product" />
                            </div> */}
                            <div className="content" >
                                <div className="header" > {item.itemName}</div>
                                <div className="meta">
                                    <span className="date">{item.itemPrice}</span>
                                </div>
                                <div className="description">{item.itemDescription}</div>
                            </div>
                            <div className="extra content">
                                <button className="ui button fluid primary">Add to Cart</button>
                            </div>
                        </div>
                    </div>
                )
            }
        </div>

    );
}


export default Homepage;
