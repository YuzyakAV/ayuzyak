function addCountries() {
    var request = new XMLHttpRequest();
    request.open("GET", "countries", true);
    function process() {
        if (request.readyState === 4 && request.status === 200) {
            var countries = JSON.parse(request.responseText);
            var sel = document.getElementById("country");
            for (var i = 0; i < countries.length; i++) {
                var option = document.createElement("option");
                option.value = countries[i].id;
                option.text = countries[i].country;
                sel.add(option);
            }
        }
    }
    request.onreadystatechange = process;
    request.send(null);
}

window.onload = addCountries;

var countrySelect = document.getElementById("country");
countrySelect.onchange = handleSelect;

var logEnter = document.forms["createForm"]["login"];
logEnter.onchange = function () {
    loginValidate(logEnter);
};

var passwordEnter = document.forms["createForm"]["password"];
passwordEnter.onchange = passwordValidate;

var newLoginEnter = document.forms["createForm"]["newlogin"];
if (newLoginEnter !== undefined) {
    newLoginEnter.onchange = function () {
        loginValidate(newLoginEnter);
    }
}


function handleSelect() {
    var countryElem = countrySelect.options[countrySelect.selectedIndex];
    var countryID = countryElem.value;
    var req = new XMLHttpRequest();
    req.open("POST", "cities", true);
    var body = "cities=" + countryID;
    function proc() {
        if (req.readyState === 4 && req.status === 200) {
            var cities = JSON.parse(req.responseText);
            var sel = document.getElementById("city");
            while (sel.options.length > 0) {
                sel.options[sel.options.length - 1] = null;
            }
            var option = document.createElement("option");
            option.text = " -- select -- ";
            option.disabled = true;
            option.selected = true;
            sel.add(option);
            for (var i = 0; i < cities.length; i++) {
                option = document.createElement("option");
                option.value = cities[i].id;
                option.text = cities[i].city;
                sel.add(option);
            }
        }
    }
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(body);
    req.onreadystatechange = proc;
}

function validateForm() {
    var login = logEnter.value;
    var newLogin = "Zaglushka";
    if (newLoginEnter !== undefined) {
        newLogin = newLoginEnter.value;
    }
    var password = passwordEnter.value;
    if (!login.match(/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/)
        || !password.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/)
        || !newLogin.match(/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/)) {
        alert("Необходимо правильно ввести логин и пароль");
        return false;
    }
}

function loginValidate(loginEnter) {
    var login = loginEnter.value;
    if (!login.match(/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/)) {
        alert("Логин должен начинаться с буквы латинского алфавита и состоять как минимум из двух букв латинского" +
            " алфавита, цифр и символа нижниего подчеркивания");
        return false;
    }
    var req = new XMLHttpRequest();
    req.open("POST", "usercheck", true);
    var body = "checklogin=" + login;
    function proc() {
        if (req.readyState === 4 && req.status === 200) {
            var cheklogin = JSON.parse(req.responseText);
            if (cheklogin.exist) {
                alert("Логин занят, введите другой");
                loginEnter.value = "";
            }
        }
    }
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(body);
    req.onreadystatechange = proc;
}

function passwordValidate() {
    var password = passwordEnter.value;
    if (!password.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/)) {
        alert("Пароль должен состоять из букв латинского алфавита, и содержать как минимум одну строчную, " +
            "прописную букву и одну цифру");
    }
}
