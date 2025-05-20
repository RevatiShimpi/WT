
let btn1 = document.getElementById("btnn1");
let len;
let btn2=document.getElementById("btn2");



btn1.addEventListener("click", function(e) {
    e.preventDefault(); 
    len = document.getElementById("len1").value;        
    if(len === "") {
        console.log("Please enter a valid number.");
    } else {
        console.log("Length entered:", len);
    }

});

let a=[];
let n=document.getElementById("length2");
btn2.onclick=(e)=>{
    e.preventDefault();
    let num2=document.getElementById("length2").value;
    a.push(num2);
    n.value="";
    
}

let btn3=document.getElementById("btn3");
let listt=document.getElementById('listt');
let list2=document.getElementById('list2');
btn3.onclick=(e)=>{
    e.preventDefault();
    for(i=0;i<a.length;i++){
        let n1=document.createElement('li2');
        n1.innerText=a[i] + " ";
        listt.appendChild(n1);
    }

    //Bubble Sort
    let flag=1;
    do{
        flag=1;
       for(j=0;j<a.length;j++){
            if(a[j]>a[j+1]){
                let temp=a[j];
                a[j]=a[j+1];
                a[j+1]=temp;
                flag=0;
            } 
        }
    }while(flag==0);

    for(i=0;i<a.length;i++){
        let n1=document.createElement('li1');
        n1.innerText=a[i]+" ";
        list2.appendChild(n1);
    }

}

// for text

let btn4=document.getElementById("btnn2");
let btn5=document.getElementById("btnn3");
let arr2=[];
btn4.onclick=(e)=>{
    let t=document.getElementById("text3").value;
    arr2.push(t);
}
btn5.onclick=(e)=>{
    e.preventDefault();
    for(i=0;i<arr2.length;i++){
        let n2=document.createElement('li3');
        n2.innerText=arr2[i] + " ";
        listt.appendChild(n2);
    }
}