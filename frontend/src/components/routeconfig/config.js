
const PrivateRoute = ({ component: Component, ...rest }) => {
    const {authTokens} = useAuth();

    return (
        <Route
          {...rest}
            render={props =>
                authTokens != "undefined" ? (
                    <Component {...props}/>
                ) : (
                    <Redirect to={{ pathname: "/login" }}/>
                )
            }
            />
    );
}
