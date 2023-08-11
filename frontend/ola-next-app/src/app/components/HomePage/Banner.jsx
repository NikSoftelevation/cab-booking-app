"use client";

import React from "react";
import "./Banner.css";
import CircleIcon from "@mui/icons-material/Circle";
import ArrowRightAltIcon from "@mui/icons-material/ArrowRightAlt";
import { useRouter } from "next/navigation";

const Banner = () => {
  const router = useRouter();

  return (
    <div className="h-[90vh]">
      <div className="cropped-image"></div>
      <div className="bannerMiniContainer px-36">
        <p className="text text-5xl font-bold text-white w-[25rem] mb-5">
          Moving people,and the world
        </p>

        <div className="py-5 bg-white w-[25rem] flex space-x-7 justify-around">
          <p>Daily</p>
          <p>Rental</p>
          <p>Outstation</p>
        </div>

        <div className="bg-white pt-5">
          <div className="flex items-center justify-around">
            <div className="flex items-center py-2 px-14  border border-slate-900">
              <CircleIcon
                className="pr-8 text-green-800"
                style={{ fontSize: 40 }}
              />
              <p>Current Location</p>
            </div>
            <div className="flex items-center py-2 px-14  border border-slate-900">
              <CircleIcon
                className="pr-8 text-red-700"
                style={{ fontSize: 40 }}
              />
              <p>Enter Destination</p>
            </div>

            <div
              onClick={() => router.push("/")}
              className="searchOlaCabButton cursor-pointer flex items-center py-4 px-14 border border-slate-900 justify-around"
            >
              <p className="text-white font-semibold">
                Search <span className="text-green-400">Ola Cab</span>
              </p>

              <ArrowRightAltIcon className="text-green-400" />
            </div>
          </div>

          <div className="mt-10 border">
            <img
              className="w-full"
              src="https://s3-ap-southeast-1.amazonaws.com/ola-prod-website/banner-green-desktop.png"
              alt=""
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Banner;
