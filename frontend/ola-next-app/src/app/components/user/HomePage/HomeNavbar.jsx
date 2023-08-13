"use client";
import React from "react";
import MenuIcon from "@mui/icons-material/Menu";

const HomeNavbar = () => {
  return (
    <nav className="bg-black py-4">
      <div className="container mx-auto px-4">
        <div className="flex justify-between items-center">
          <div className="flex items-center">
            <div className="flex items-center mx-5">
              <img
                className="h-12"
                src="https://media-s3-us-east-1.ceros.com/mastercard-labs/images/2022/04/14/e783c0db9c7e873f525aed90be3fc7d2/ola-logo-white.png" //"https://s3-ap-southeast-1.amazonaws.com/ola-prod-website/ola-white-logo.svg"
                alt="Logo"
              />
            </div>

            <div>
              <ul className="flex items-center">
                <li className="mr-6">
                  <a href="#" className="text-white hover:text-blue-200">
                    Ola S1
                  </a>
                </li>
                <li className="mr-6">
                  <a href="#" className="text-white hover:text-blue-200">
                    Ola Electric
                  </a>
                </li>{" "}
                <li className="mr-6">
                  <a href="#" className="text-white hover:text-blue-200">
                    Ola Future Factory
                  </a>
                </li>
              </ul>
            </div>
          </div>

          <div>
            <button className="bg-gray-700 text-white hover:bg-gray-900 text-sm font-semibold px-4 py-3">
              Book An Ola Cab
            </button>
            <button className="bg-white text-black hover:bg-gray-300 text-sm font-semibold px-4 py-3 ml-5">
              Free S1 Test Ride{" "}
            </button>

            <MenuIcon className="ml-5 text-white text-3xl" fontS />
          </div>
        </div>
      </div>
    </nav>
  );
};

export default HomeNavbar;
