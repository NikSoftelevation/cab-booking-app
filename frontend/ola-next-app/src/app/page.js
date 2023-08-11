"use client"
import Banner from "./components/HomePage/Banner";
import CardVideoSection from "./components/HomePage/CardVideoSection";
import Footer from "./components/HomePage/Footer";
import HomeNavbar from "./components/HomePage/HomeNavbar";


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
