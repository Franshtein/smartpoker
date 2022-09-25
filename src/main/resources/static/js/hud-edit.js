function save() {

    var d = document,
        stats = d.getElementsByClassName('choise')
    str = "";
    for (var i = 0; i < stats.length; i++) {

        str += stats[i].value;
        str+='/';
    }
    document.forms["sethud"].elements["allstatsname"].value = str;
    alert(str);
}
function rollback() {
    document.forms["createhud"].elements["numrows"].value = document.forms["createhud"].elements["numrowsOld"].value
    document.forms["createhud"].elements["numcols"].value = document.forms["createhud"].elements["numcolsOld"].value
    document.getElementById('createhud').submit();
}
