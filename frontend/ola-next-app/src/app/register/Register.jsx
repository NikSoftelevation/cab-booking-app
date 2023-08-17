"use client";
import React, { useEffect } from "react";
import { Button, TextField } from "@mui/material";
import WestIcon from "@mui/icons-material/West";
import { useRouter } from "next/navigation";
import { useDispatch, useSelector } from "react-redux";
import { getUser,registerUser } from "../Redux/Auth/Action"; 
import { useFormik } from "formik";
import * as yup from "yup";

const validationSchema = yup.object().shape({
  fullName: yup.string().required("Full name is required"),
  email: yup.string().email("Invalid Email").required("Email is required"),
  password: yup
    .string()
    .min(8, "Password should be atleast 8 characters long")
    .required("Password is required"),
  mobile: yup.string().required("Mobile number is required"),
});

const Register = () => {
  const router = useRouter();
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");

  return <div>Register</div>;
};

export default Register;
