export const register = async (values) => {
    try {
        const apiRes = await fetch("http://127.0.01:8080/api/register", {
            headers: {
                "Content-Type": "application/json"
            },
            method: "POST",
            body: JSON.stringify(values)
        });

        const resJSON = await apiRes.json();
        return true;
    } catch (error) {
        console.log(error);
    }
}

export const authenticate = async (values) => {
    try {
        const apiRes = await fetch("http://127.0.01:8080/api/login", {
            headers: {
                "Content-Type": "application/json"
            },
            method: "POST",
            body: JSON.stringify(values)
        });

        const resJSON = await apiRes.json();
        return apiRes.token
    } catch (error) {
        console.log(error);
    }
}