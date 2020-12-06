import { Button, Grid, Typography } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import { createZone, fetchZones, monitorTemperature, turnOffHAVC, turnOnHAVC } from '../../modules/SHH/SHHService'
import Zone from '../Zone/Zone'
import AddIcon from '@material-ui/icons/Add';
import {useOutputData} from '../../context/OutputData'
const SHHPanel = () => {
    const [zoneList, setZoneList] = useState([])
    const houseId = localStorage.getItem("houseID");
    const {setOutputData} = useOutputData();

    useEffect(() => {
        fetchZones(houseId).then(data => {
            setZoneList(data)
            console.log(data)
        })
            .catch(error => {
                console.log(error)
                //handle error
            })
    }, [houseId])
;

    const handleCreateZone = () => {
        createZone(houseId).then(data => {
            setZoneList(oldList => [...oldList, data])
        }).catch(error => {
            console.log(error)
        })
    }

    const handleTurnOnHAVC = () => {
        turnOnHAVC(houseId)
    }

    const handleTurnOffHAVC = () => {
        turnOffHAVC(houseId)
    }

    const handleMonitorTemperature = ()=>{
        setInterval(monitorTemperature(houseId).then(data=>{
            //console.log(data)
            setOutputData(outputData => [...outputData, {id:outputData.length +1, date:new Date(), data:`${data.alertMessage}`}])
        }), 5000)
    }
    return (
        <div>
            <Grid container direction="row" >
                <Grid item xs={12} lg={6}>
                    <Typography>Add Zone</Typography>
                </Grid>
                <Grid item xs={12} lg={6}>
                    <Button onClick={handleCreateZone} ><AddIcon></AddIcon></Button>
                </Grid>
                <Grid item xs={12}>
                    <Typography>Turn HAVC</Typography>
                    <Button onClick={handleTurnOnHAVC}>On</Button>
                    <Button onClick={handleTurnOffHAVC}>Off</Button>
                </Grid>
                <Grid item xs={12}>
                    <Button onClick={handleMonitorTemperature}>Monitor Temperature</Button>
                </Grid>
                {
                    zoneList.map(zone => (
                        <Grid key={zone.id} item xs={12} lg={6}>
                            <Zone zone={zone} />
                        </Grid>
                    )
                    )
                }
            </Grid>
        </div>
    )
}

export default SHHPanel
