import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {makeStyles, List, ListItem, ListItemText} from '@material-ui/core';
import {changeClientId} from "../App.redux-actions";
import Refetcher from "../App.refetcher";

const style = makeStyles(theme => ({

   listItem: {
       cursor: 'pointer'
   }

}))

const ListItems = ({ids, cid, showClientDetails}) => {
    return ids.map(id => <ListItem key={id}
                                   button
                                   selected={cid === id}
                                   onClick={() => showClientDetails(id)}
    >
            <ListItemText
                primary={id}
            />
        </ListItem>)

}

const ClientDetailsList = ({cid, changeClientId}) => {
    const [cidList, setCIDList] = useState([]);
    const fetchData = () => {
        fetch("/clientdetails/allClientIds", {
            method: 'get',
            headers: {
                'Authorization': 'Basic',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            // body: JSON.stringify({var1: 1, var2: 2})
        }).then( resp => resp.json() )
            .then(data => setCIDList(data.clientIds))
            .catch(err => console.log(err));
    }

    useEffect(() => {
        fetchData();
    }, []);

    Refetcher.add("ClientDetailsList", fetchData);

    const showClientDetails = (cid) => {
        console.log("edit client details", cid);
        changeClientId(cid);
    }

    return <List>
        <ListItems ids={cidList} cid={cid} showClientDetails={showClientDetails}/>
    </List>
}

const mapDispatchToProps = dispatch => {
    return {
        changeClientId: cid => dispatch(changeClientId(cid))
    }
}

const mapStateToProps = state => {
    return {
        cid: state.clientDetails.clientId
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(ClientDetailsList);
