"use client";
import React, { useEffect } from "react";
import WestIcon from "@mui/icons-material/West";
import { useRouter } from "next/navigation";
import { Avatar, Button } from "@mui/material";
import { deepOrange } from "@mui/material/colors";
import RideCar from "../components/user/RideCar/RideCar";
import AccountBalanceWalletIcon from "@mui/icons-material/AccountBalanceWallet";
import ContactPhoneIcon from "@mui/icons-material/ContactPhone";
import WifiIcon from "@mui/icons-material/Wifi";
import { useDispatch, useSelector } from "react-redux";
import { getUser, logout } from "../Redux/Auth/Action";

const Profile = () => {
  const router = useRouter();
  const jwt = localStorage.getItem("jwt");
  const dispatch = useDispatch();
  const { auth } = useSelector((state) => state);

  const goBack = () => {
    router.back();
  };

  const handleLogout = () => {
    dispatch(logout);
    router.push("/book-ride");
  };

  useEffect(() => {
    dispatch(getUser(jwt));
  }, []);

  console.log("Auth ", auth);

  return (
    <div className="px-2 lg:px-5">
      <div className="px-2 lg:px-5 py-2">
        <WestIcon onClick={goBack} className="cursor-pointer" />
      </div>

      <div className="flex flex-col items-center space-y-2">
        <Avatar sx={{ bgcolor: deepOrange[500] }} R></Avatar>
        <p>{auth.user?.fullName}</p>
        <p>{auth.user?.mobile}</p>
      </div>

      <div className="border rounded-sm mt-5 ">
        <RideCar />
        <div className="flex flex-col items-center p-3">
          <Button onClick={() => router.push("/your-rides")} variant="text">
            See all rides
          </Button>
        </div>
      </div>

      <div className="border mt-5">
        <div className="flex items-center p-3 border-b">
          <AccountBalanceWalletIcon className="text-green-600" />
          <p className="ml-4">Ola Money</p>
        </div>

        <div className="flex items-center p-3 border-b">
          <ContactPhoneIcon />
          <p className="ml-4">Emergency Contact</p>
        </div>
        <div className="flex items-center p-3">
          <WifiIcon />
          <p className="ml-4">Ola Wifi Crendentials</p>
        </div>
      </div>

      <div>
        <Button
          onClick={handleLogout}
          className="w-full bg-red-500 text-white"
          variant="contained"
          color="error"
        >
          Logout
        </Button>
      </div>
    </div>
  );
};

export default Profile;
