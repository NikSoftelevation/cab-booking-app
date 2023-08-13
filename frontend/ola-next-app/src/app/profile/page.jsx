"use client";
import React from "react";
import { Layout } from "../components/user/Loyout/Layout";
import Profile from "./Profile";

const page = () => {
  return (
    <div>
      <Layout Children={<Profile />} />
    </div>
  );
};

export default page;
