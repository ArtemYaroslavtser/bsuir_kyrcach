if (window) {
    let stats = document.getElementById("status");
    let orders = document.getElementById("order_balance");
    let ord_options = orders.children;
    stats.onselect = function (){
        for (let i of ord_options){
            i.disabled = false;
        }
        if (this.value === "Доходы"){
            ord_options[4].disabled = true;
            ord_options[5].disabled = true;
        }
        else if (this.value === "Расходы") {
            ord_options[1].disabled = true;
            ord_options[2].disabled = true;
            ord_options[3].disabled = true;
        };
    }
} else {
    let stats = document.getElementById("status");
    let orders = document.getElementById("order_balance");
    let ord_options = orders.children;
    stats.onselect = function (){
        for (let i of ord_options){
            i.disabled = false;
        }
        if (this.value === "Доходы"){
            ord_options[4].disabled = true;
            ord_options[5].disabled = true;
        }
        else if (this.value === "Расходы") {
            ord_options[1].disabled = true;
            ord_options[2].disabled = true;
            ord_options[3].disabled = true;
        };
    }
}

