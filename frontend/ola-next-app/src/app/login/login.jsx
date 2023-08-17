"use client";
import { getUser, login } from "@/app/Redux/Auth/Action";
import { store } from "@/app/Redux/store";
import WestIcon from "@mui/icons-material/West";
import { Button, TextField } from "@mui/material";
import { useFormik } from "formik";
import { useRouter, useSelectedLayoutSegment } from "next/navigation";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import * as yup from "yup";

const validationSchema = yup.object().shape({
  email: yup.string().email("Invalid Email").required("Email is required"),
  password: yup.string().required("Password is required"),
});

const Login = () => {
  const router = useRouter();

  const goBack = () => {
    router.back();
  };
  const jwt = localStorage.getItem("jwt");

  const { auth } = useSelector((store) => store);

  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      console.log("values : ", values);
      dispatch(login(values));
    },
  });

  useEffect(() => {
    console.log("jwt ", jwt);

    if (jwt) {
      dispatch(getUser(jwt));
    }
  }, [jwt]);

  useEffect(() => {
    if (auth.user?.fullName || auth.user?.name) {
      if (auth.user.role === "DRIVER") {
        router.push("/driver/dashboard");
      } else {
        router.push("/");
      }
    }
  }, [auth.user]);

  return (
    <div className="py-10">
      <div className="flex items-center px-2 lg:px-5 py-2">
        <WestIcon onClick={goBack} className="cursor-pointer" />

        <div className="w-full flex justify-center">
          <img
            className="h-10"
            src="https://accounts.olacabs.com/en-in/images/ola-logo.png"
            alt=""
          />
        </div>
      </div>

      <form onSubmit={formik.handleSubmit} className="z-50 h-full p-5">
        <TextField
          label="Email"
          type="email"
          variant="outlined"
          fullWidth
          margin="normal"
          name="email"
          value={formik.values.email}
          onChange={formik.handleChange}
          error={formik.touched.email && formik.errors.email}
          helperText={formik.touched.email && formik.errors.email}
        />

        <TextField
          label="Password"
          type="password"
          variant="outlined"
          fullWidth
          margin="normal"
          name="password"
          value={formik.values.password}
          onChange={formik.handleChange}
          error={formik.touched.password && formik.errors.password}
          helperText={formik.touched.password && formik.errors.password}
        />

        <Button
          sx={{ padding: ".9rem 0rem" }}
          variant="contained"
          type="submit"
          color="secondary"
          className="z-10 w-full"
        >
          Login
        </Button>
      </form>

      <div className="flex w-full justify-center">
        <p className="flex items-center mt-5 text-center">
          Don't HAve an Account ? Register
          <Button
            onClick={() => router.push("register")}
            className="ml-5"
            color="secondary"
          >
            Register
          </Button>
        </p>
      </div>
    </div>
  );
};

export default Login;
