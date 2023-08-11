import React from 'react'
import { Layout } from '../components/user/Loyout/Layout'
import BookRide from './BookRide'

export const page = () => {
    return (
        <div>
            <Layout Children={<BookRide />}> </Layout>
        </div>
    )
}
