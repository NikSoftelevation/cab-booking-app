"use client";
import React from "react";
import { Layout } from "../components/user/Loyout/Layout";
import Rides from "./Rides";

const page = () => {
  return <Layout Children={<Rides />} />;
};

export default page;
