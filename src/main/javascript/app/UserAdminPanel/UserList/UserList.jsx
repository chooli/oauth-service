import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {makeStyles, List, ListItem, ListItemText} from '@material-ui/core';
import {changeUsername} from "../../App.redux-actions";
import Refetcher from "../../App.refetcher";

const style = makeStyles(theme => ({

   listItem: {
       cursor: 'pointer'
   }

}))

const ListItems = ({ids, cid, showUser}) => {
    return ids.map(id => <ListItem key={id}
                                   button
                                   selected={cid === id}
                                   onClick={() => showUser(id)}
    >
            <ListItemText
                primary={id}
            />
        </ListItem>)

}

const UserList = ({username, changeUsername}) => {
    const [usernameList, setUsernameList] = useState([]);
    const fetchData = () => {
        fetch("/user/allUsernames", {
            method: 'get',
            headers: {
                'Authorization': 'Basic',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            // body: JSON.stringify({var1: 1, var2: 2})
        }).then( resp => resp.json() )
            .then(data => setUsernameList(data.usernames))
            .catch(err => console.log(err));
    }

    useEffect(() => {
        fetchData();
    }, []);

    Refetcher.add("UserList", fetchData);

    const showUser = (username) => {
        console.log("edit user", username);
        changeUsername(username);
    }

    return <List>
        <ListItems ids={usernameList} username={username} showUser={showUser}/>
    </List>
}

const mapDispatchToProps = dispatch => {
    return {
        changeUsername: username => dispatch(changeUsername(username))
    }
}

const mapStateToProps = state => {
    return {
        username: state.user.username
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(UserList);
