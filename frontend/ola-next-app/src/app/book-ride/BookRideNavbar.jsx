"use client";
import {
  AppBar,
  Avatar,
  Box,
  Drawer,
  IconButton,
  Toolbar,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import MenuIcon from "@mui/icons-material/Menu";
import { useRouter } from "next/navigation";
import { deepOrange } from "@mui/material/colors";
import { Button } from "bootstrap";
import { drawerList } from "./DrawerList";
import { useDispatch, useSelector } from "react-redux";

const BookRideNavbar = () => {
  const dispatch = useDispatch();

  const jwt = localStorage.getItem("jwt");

  const { auth } = useSelector((state) => state);

  const [sidebarOpen, setSideBarOpen] = useState(false);
  const handleSidebarClose = () => {
    setSideBarOpen(false);
  };
  const handlesidebarOpen = () => {
    setSideBarOpen(true);
  };
  const router = useRouter();

  useEffect(() => {
    dispatch(getUser(jwt));
  }, []);

  return (
    <Box classname="">
      <AppBar
        sx={{ backgroundColor: "#120E43" }}
        className=""
        position="static"
      >
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            onClick={handlesidebarOpen}
          >
            <MenuIcon />
          </IconButton>

          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Zosh Cab
          </Typography>
          {auth.user?.fullName ? (
            <Avatar
              className="cursor-pointer"
              sx={{ bgcolor: deepOrange[500] }}
              onClick={() => router.push("/profile")}
            >
              {auth.user?.fullName[0]}
            </Avatar>
          ) : (
            <Button onClick={() => router.push("/login")} color="inherit">
              Login
            </Button>
          )}
        </Toolbar>
      </AppBar>
      <Drawer anchor={"left"} open={sidebarOpen} onClose={handleSidebarClose}>
        {drawerList("list")}
      </Drawer>
    </Box>
  );
};
export default BookRideNavbar;
