let btn1=document.getElementById("btn1");

btn1.onclick=()=>{
    let name=document.getElementById("usname").value;
    let pswd=document.getElementById("pswd").value;
    if(name=="admin" && pswd=="admin"){
        window.location.href="Lab6.html"

    }
    else{
        alert("Incorrect username and password")
    }
}