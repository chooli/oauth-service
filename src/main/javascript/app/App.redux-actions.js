const actionTypes = {
    CHANGE_USERNAME: 'CHANGE_USERNAME',
    CHANGE_CLIENT_ID: 'CHANGE_CLIENT_ID'
}

const changeClientId = (cid) => ({
    type: actionTypes.CHANGE_CLIENT_ID,
    clientId: cid
})

const changeUsername = (username) => ({
    type: actionTypes.CHANGE_USERNAME,
    username: username
})

export {
    actionTypes,
    changeClientId,
    changeUsername
}
