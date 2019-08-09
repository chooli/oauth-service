import React from 'react';
import {makeStyles, ExpansionPanel, ExpansionPanelDetails, ExpansionPanelSummary, TextField, Typography} from '@material-ui/core';
import {ExpandMore, Edit} from '@material-ui/icons';
import * as _ from 'lodash';
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";

const useStyle = makeStyles( theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200
    },
    singleTextField: {
        flexWrap: 'wrap',
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 640
    },
    icon: {
        width: '0.7em',
        height: '0.7em',
        marginRight: theme.spacing(1)
    }
}))

const ClientDetailsMaster = ({clientDetails}) => {
    const classes = useStyle();

    const handleChange = (target) => {
        switch(target) {
            case 'CLIENT_ID':
            default: return;
        }
    }

    return (<ExpansionPanel>
        <ExpansionPanelSummary
            expandIcon={<ExpandMore />}
            aria-controls="panel1a-content"
            id="panel1a-header"
        >
            <Typography><Edit className={classes.icon}/>add/edit client details</Typography>
        </ExpansionPanelSummary>
        <ExpansionPanelDetails className={classes.container}>
            <TextField
                label="Client ID"
                className={classes.textField}
                value={!_.isNil(clientDetails) ? clientDetails.clientId : null}
                onChange={handleChange('CLIENT_ID')}
                margin="normal"
            />
            <TextField
                label="Client Secret"
                className={classes.textField}
                value={!_.isNil(clientDetails) ? clientDetails.clientSecret : null}
                onChange={handleChange('CLIENT_SECRET')}
                margin="normal"
            />
            <TextField
                label="Resource IDs"
                className={classes.textField}
                value={!_.isNil(clientDetails) ? clientDetails.resourceIds : null}
                onChange={handleChange('RESOURCE_IDS')}
                margin="normal"
            />
            <TextField
                label="Scope"
                className={classes.textField}
                value={!_.isNil(clientDetails) ? clientDetails.scope : null}
                onChange={handleChange('SCOPE')}
                margin="normal"
            />
            <TextField
                label="Authorized Grant Types"
                className={classes.textField}
                value={!_.isNil(clientDetails) ? clientDetails.authorizedGrantTypes : null}
                onChange={handleChange('AUTHORIZED_GRANT_TYPES')}
                margin="normal"
            />
            <TextField
                label="Authorities"
                className={classes.textField}
                value={!_.isNil(clientDetails) ? clientDetails.authorities : null}
                onChange={handleChange('AUTHORITIES')}
                margin="normal"
            />
            <TextField
                label="Access Token Validity"
                className={classes.textField}
                value={!_.isNil(clientDetails) ? clientDetails.accessTokenValidity : null}
                onChange={handleChange('ACCESS_TOKEN_VALIDITY')}
                margin="normal"
            />
            <FormControlLabel
                control={
                    <Checkbox
                        checked={!_.isNil(clientDetails) ? clientDetails.autoapprove : false}
                        onChange={handleChange('AUTOAPPROVE')}
                        value="autoapprove"
                    />
                }
                label="Autoapprove"
            />
            <TextField
                label="Web Server Redirect Uri"
                className={classes.singleTextField}
                value={!_.isNil(clientDetails) ? clientDetails.webServerRedirectUri : null}
                onChange={handleChange('WEB_SERVER_REDIRECT_URI')}
                margin="normal"
            />
            <TextField
                label="Additional Information"
                className={classes.singleTextField}
                value={!_.isNil(clientDetails) ? clientDetails.additionalInformation : null}
                onChange={handleChange('ADDITIONAL_INFORMATION')}
                margin="normal"
            />
        </ExpansionPanelDetails>
    </ExpansionPanel>)
}

export default ClientDetailsMaster;
