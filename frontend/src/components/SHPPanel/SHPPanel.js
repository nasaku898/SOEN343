import { FormControl, FormControlLabel, FormGroup, FormLabel, Switch } from '@material-ui/core'
import { light } from '@material-ui/core/styles/createPalette'
import React, { useEffect, useState } from 'react'
import { useCurrentHouse } from '../../context/CurrentHouse'
import LightSwitch from '../SHCPanel/LightSwitch'

const SHPPanel = () => {
    const { house } = useCurrentHouse()
    const [awayMode, setAwayMode] = useState(false)
    const onChange = () => {
        setAwayMode(!awayMode)
    }
    
    return (
        <div>
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
