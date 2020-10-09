import { Fragment } from "react";


const NameField = ({    
    name,
    type,
    placeholder,
    onChange,
    className,
    value,
    error,
    children,
    label,
    ...props
}) => {
    return ( 
        <Fragment>
        <label htmlFor={name}>{label}</label>
        <input
            id={name}
            name={name}
            placeholder={placeholder}
            value={value}
            className={className}
            type={type}
        />
        { error && <p>{ error }</p>}

        </Fragment>
    
     );
}
 
export default NameField;