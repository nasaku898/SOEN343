import { Switch } from '@material-ui/core'
import React from 'react'

const LightSwitch = () => {
    const [stayOn, setStayOn] = useState(false)
    const onChange = () => {
        setStayOn(!stayOn)
    }
    return (
        <div>
            <Switch
                checked={stayOn}
                name="stayOn"
                inputProps={{ 'aria-label': 'secondary checkbox' }}
                onChange={onChange}
            />
        </div>
    )
}
export default LightSwitch
