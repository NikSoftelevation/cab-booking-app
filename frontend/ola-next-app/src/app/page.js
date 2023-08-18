"use client"
import { Provider } from "react-redux";
import Banner from "./components/user/HomePage/Banner";
import CardVideoSection from "./components/user/HomePage/CardVideoSection";
import Footer from "./components/user/HomePage/Footer";
import HomeNavbar from "./components/user/HomePage/HomeNavbar";
import { store } from "./Redux/store";

export default function Home() {
  return (

    <Provider store={store}>
      <main className="">
        <HomeNavbar />
        <Banner />
        <CardVideoSection />
        <Footer />
      </main >
    </Provider>
  )
}
