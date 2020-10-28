import {useAuth} from "../../context/Auth";
import React from "react";
import {Redirect, Route} from "react-router-dom";

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
