"use client"
import React from 'react'
import { store } from './store'

const Providers = ({ Children }) => {
    return (
        <Providers store={store} >{Children}</Providers>

    )
}

export default Providers