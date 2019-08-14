const actionTypes = {
    SHOW_CLIENT_DETAILS: 'SHOW_CLIENT_DETAILS',
    CHANGE_CLIENT_ID: 'CHANGE_CLIENT_ID'
}

const changeClientId = (cid) => ({
    type: actionTypes.CHANGE_CLIENT_ID,
    clientId: cid
})

export {
    actionTypes,
    changeClientId
}
