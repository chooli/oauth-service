import React, {useEffect, useState} from 'react';
import {List, ListItem, ListItemText} from '@material-ui/core';

const ListItems = ({ids}) => {
    return ids.map(id => <ListItem key={id}>
            <ListItemText
                primary={id}
            />
        </ListItem>)

}

const ClientDetailsList = () => {
    const [cidList, updateCIDList] = useState([]);
    const fetchData = () => {
        fetch("http://localhost:10090/clientdetails/allClientIds", {
            method: 'get',
            headers: {
                'Authorization': 'Basic',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            // body: JSON.stringify({var1: 1, var2: 2})
        }).then( resp => resp.json() )
            .then(data => updateCIDList(data.clientIds))
            .catch(err => console.log(err));
    }

    useEffect(() => {
        fetchData();
    }, []);

    return <List>
        <ListItems ids={cidList} />
    </List>
}

export default ClientDetailsList;
