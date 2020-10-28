import {useAuth} from "../../context/Auth";
import Route from "react-router-dom";
import Redirect from "react-router-dom";
import React from "react";

export const PrivateRoute = ({ component: Component, ...rest }) => {
    const {authTokens} = useAuth();
    return (
        <Route
            {...rest}
            render={props =>
                authTokens !== "undefined"
                    ? (<Component {...props}/>)
                    : (<Redirect to={{ pathname: "/login" }}/>)
            }
        />
    )
}
