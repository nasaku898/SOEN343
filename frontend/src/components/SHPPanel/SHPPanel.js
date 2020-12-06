import { Button, FormControl, FormControlLabel, FormGroup, Switch } from '@material-ui/core'
import React, { useState } from 'react'
import { useCurrentHouse } from '../../context/CurrentHouse'
import { createSecurity, setAwayMode as turnOnAway } from '../../modules/SHP/SHPService'

const SHPPanel = () => {
    const { house } = useCurrentHouse()
    const [awayMode, setAwayMode] = useState(false)

    const onChange = () => {
        turnOnAway(16, !awayMode)
        setAwayMode(!awayMode)
    }

    const handleCreateSecurity = () => {
        createSecurity(house.id)
    }

    return (
        <div>
            <Button onClick={handleCreateSecurity}>Turn On Security</Button>
            <FormControl>
                <FormGroup>
                    <FormControlLabel
                        control={<Switch
                            checked={awayMode}
                            name="awayMode"
                            inputProps={{ 'aria-label': 'secondary checkbox' }}
                            onChange={onChange}
                        />}
                        label="Set Away Mode"
                    >
                    </FormControlLabel>
                </FormGroup>
            </FormControl>
        </div>
    )
}

export default SHPPanel
