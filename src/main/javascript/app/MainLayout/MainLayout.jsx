import React from  'react';
import {makeStyles, Grid, Paper} from '@material-ui/core';
import ClientDetailsList from "../ClientDetailsList";
import ClientDetailsMaster from "../ClientDetailsMaster";

//custom hooks
const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
        padding: '40px'
    },
    item: {
        flexGrow: 1,
        margin: 'auto'
    },
    paper: {
        padding: theme.spacing(1),
        textAlign: 'center',
        color: theme.palette.text.primary
    }
}))

const MainLayout = () => {
    const classes = useStyles();

    return <Grid container className={classes.root} spacing={1}>

        <Grid item className={classes.item} xs={9}>
            <ClientDetailsMaster/>
        </Grid>

        <Grid item className={classes.item} xs={9}>
            <Paper className={classes.paper}>
                <ClientDetailsList />
            </Paper>
        </Grid>

    </Grid>
}

export default MainLayout;
