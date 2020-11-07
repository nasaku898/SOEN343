import { Button, Input } from '@material-ui/core'
import React, { useEffect, useRef, useState } from 'react'
import { useCurrentDate } from '../../context/DateContext'
import { setNewDate } from '../../modules/HouseOverview/SimulationService'

const Time = () => {
    const { currentDate } = useCurrentDate()
    const date = useRef(currentDate)
    const [displayDate, setDisplayDate] = useState(new Date(date))
    const [edit, setEdit] = useState(false)
    const [selectedDate, setSelectedDate] = useState()

    useEffect(() => {
        setSelectedDate(new Date(date.current))

        setInterval(() => {
            date.current = date.current + 1000
            setDisplayDate(new Date(date.current))
        }, 1000)

        return () => {
            clearInterval(date.current)
        }
    }, [])

    const handleChange = () => {
        setEdit(!edit)
    }

    const updateDate = (event) => {
        setSelectedDate(event.target.value)
    }

    const handleChangeDate = (event) => {
        event.preventDefault()
        setNewDate(new Date(selectedDate).getTime()).then(data => {
            setDisplayDate(new Date(data))
            date.current = data
            setSelectedDate(new Date(data))
        })
    }

    return (
        <div>
            {
                edit ? (<div><Input type="datetime-local" value={selectedDate} onChange={updateDate}></Input><Button onClick={handleChangeDate}>Submit</Button></div>) : displayDate.toString()
            }
            <Button onClick={handleChange}>Edit Date</Button>
        </div>
    )
}

export default Time