"use client"
import React from 'react'
import { Layout } from '../components/user/Loyout/Layout'
import Login from './login'

const page = () => {
    return (
        <div> <Layout Children={<Login />}></Layout></div>
    )
}

export default page;