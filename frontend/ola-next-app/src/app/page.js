"use client"
import Banner from "./components/user/HomePage/Banner";
import CardVideoSection from "./components/user/HomePage/CardVideoSection";
import Footer from "./components/user/HomePage/Footer";
import HomeNavbar from "./components/user/HomePage/HomeNavbar";


export default function Home() {
  return (
    <main className="">
      <HomeNavbar />
      <Banner />
      <CardVideoSection />
      <Footer />

    </main>
  )
}
