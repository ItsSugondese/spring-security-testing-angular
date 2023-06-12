export interface signUpDetails{
    username : string,
    email : string,
    password : string
}

export interface loginDetails{
    email : string,
    password : string
}

interface userDetails{
    username : string,
    email : string,
    password : string,
    role : role[]
}

export interface role{
    role : string
}

export interface jwtResponse{
    user : userDetails,
    jwtToken : string
}

export interface loginFormHeader{
    status : string,
    color : string
}

export interface news{
    nid : Number,
    title : string,
    content : string,
    thumnailName : string,
    thumbnail : any,
    views : Number,
    likes : Number
}