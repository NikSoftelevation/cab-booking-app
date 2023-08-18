"use client"
import React from 'react'
import Register from './Register'
import { Layout } from '../components/user/Loyout/Layout'

const page = () => {
    return (
        <div><Layout Children={<Register />}></Layout></div>)
}

export default page