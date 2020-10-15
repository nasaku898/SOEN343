import React from "react";
import {Redirect} from "react-router-dom";

export const register = async (values) => {
 try {
  const apiRes = await fetch("http://localhost:8080/register", {
   headers: {
    "Content-Type": "application/json"
   },
   mode: "cors",
   method: "POST",
   body: JSON.stringify(values)
  });
  await apiRes.json();
  return <Redirect to="/login"/>
 } catch (error) {
  console.log(error);
 }
}

export const authenticate = async (values) => {

 try {
  const apiRes = await fetch("http://localhost:8080/login", {
   headers: {
    "Content-Type": "application/json"
   },
   mode: "cors",
   method: "POST",
   body: JSON.stringify(values)
  });
  return (await apiRes.json()).token
 } catch (error) {
  console.log(error);
  return false;
 }
}