export const register = async () => { 
    try {
        const apiRes = await fetch("http://127.0.01:8080/api/register", {
        headers: {
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify(values)
    });

    const resJSON = await apiRes.json();
    const authTokens = apiRes.token;
    setLoggedIn(authTokens);
    return true;
    } catch (error) {
        console.log(error);
    }
}

export const authenticate = async () => { 
    try {
        const apiRes = await fetch("http://127.0.01:8080/api/login", {
        headers: {
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify(values)
    });

    const resJSON = await apiRes.json();
    const authTokens = apiRes.token;
    setLoggedIn(authTokens);

    } catch (error) {
        console.log(error);
    }
}