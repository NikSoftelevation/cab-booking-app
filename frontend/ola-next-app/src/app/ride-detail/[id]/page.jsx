"use client";
import { Layout } from "@/app/components/user/Loyout/Layout";
import React, { useEffect } from "react";
import RideDetail from "./RideDetail";

const page = (props) => {
  useEffect(() => {
    console.log("props", props);
  }, []);

  return <Layout Children={<RideDetail />} />;
};

export default page;
